/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.owner.assertsparam.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.launcher.ARouter
import com.owner.assertsparam.Interface.QueryAssertsInfo
import com.owner.assertsparam.R
import com.owner.assertsparam.databinding.FragmentManagerBinding
import com.owner.assertsparam.utils.TitleItemDecoration
import com.owner.assertsparam.view.adapter.ManagerAdapter
import com.owner.assertsparam.viewmodel.ArgumentViewModel
import com.owner.assertsparam.viewmodel.ManagerViewModel
import com.owner.assertsparam.viewmodel.ManagerViewModelFactory
import com.owner.baselibrary.view.fragment.BaseFragment
import com.owner.provideslib.router.RouterPath
import kotlinx.android.synthetic.main.fragment_manager.*

/**
 *
 * Created by Liuyong on 2018-10-30.It's AMSystem
 *@description:
 */
class ManagerFragment : BaseFragment<FragmentManagerBinding, ManagerViewModel>() {

    lateinit var queryInterface: QueryAssertsInfo
    lateinit var mAdapter: ManagerAdapter
    private lateinit var managerLL: LinearLayoutManager
    private lateinit var mDecoration: TitleItemDecoration

    private lateinit var sharedViewModel: ArgumentViewModel

    private var isEdited = false
    private var isQuery = false
    companion object {
        fun newInstance(isEdited: Boolean,isQuery:Boolean): ManagerFragment {
            val bundle = Bundle()
            bundle.putBoolean("isEdited",isEdited)
            bundle.putBoolean("isQuery",isQuery)
            val fragment = ManagerFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = arguments!!
        isEdited = bundle.getBoolean("isEdited")
        isQuery = bundle.getBoolean("isQuery")

        initViewModel()
    }

    private fun initViewModel() {

        sharedViewModel = ViewModelProviders.of(activity!!).get(ArgumentViewModel::class.java)

        viewModel = ViewModelProviders.of(this, ManagerViewModelFactory(isEdited, isQuery))
                .get(ManagerViewModel::class.java)

        viewModel.refresh.observe(this, Observer {
            mAdapter.updateList(viewModel.getSortList())
        })

        viewModel.gotoQueryAsserts.observe(this, Observer {
            queryInterface.queryAssert("Manager", it!!)
        })

        viewModel.selectedManager.observe(this, Observer {
            sharedViewModel.selectedArgumentMap["Manager"] = it!!
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding = DataBindingUtil.inflate<FragmentManagerBinding>(
                inflater, R.layout.fragment_manager, container, false)
        binding.managerVm = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mHeaderBar.getRightView().visibility = View.VISIBLE
        if (!isEdited) {
            mHeaderBar.getRightView().text="完成"
        }
        if (isQuery) {
            mHeaderBar.getRightView().visibility = View.GONE
        }
        mHeaderBar.getRightView().setOnClickListener {
            if (isEdited) {
                //通过ARouter启动UserCenter模块中的RegisterActivity,并要求返回值
                ARouter.getInstance().build(RouterPath.UserCenter.PATH_USER_REGISTER).navigation(activity, 1)
            } else {
                //返回选择结果
            }
        }

        loadManagerList()

        //设置右侧SideBar触摸监听
        mSideBar.setOnTouchLetterChangeListener {
            //该字母首次出现的位置
            val position = mAdapter.getPositionForSection(it.toCharArray()[0].toInt())
            if (position != -1) {
                managerLL.scrollToPositionWithOffset(position, 0)
            }
        }
        mFilterEt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.filterData(p0.toString())
                mAdapter.notifyDataSetChanged()
            }

        })
    }

    /**
     * 加载管理员列表
     */
    private fun loadManagerList() {
        mAdapter = ManagerAdapter(viewModel)
        mManagerRcv.adapter = mAdapter

        managerLL = LinearLayoutManager(context)
        managerLL.orientation = LinearLayoutManager.VERTICAL
        mManagerRcv.layoutManager = managerLL

        mDecoration = TitleItemDecoration(context!!, viewModel.getSortList())
        mManagerRcv.addItemDecoration(mDecoration)
        mManagerRcv.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == 2) {
            val userId = data?.getStringExtra("userID")
            viewModel.getManager(userId?:"")
        }
    }
}