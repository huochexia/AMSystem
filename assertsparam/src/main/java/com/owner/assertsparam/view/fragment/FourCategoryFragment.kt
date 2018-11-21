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
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.orhanobut.logger.Logger
import com.owner.assertsparam.data.CategoryInfo
import com.owner.assertsparam.databinding.FragmentFourCategoryBinding
import com.owner.assertsparam.view.adapter.FourthCgAdapter
import com.owner.assertsparam.viewmodel.CategoryViewModelFactory
import com.owner.assertsparam.viewmodel.FourthCategoryViewModel
import com.owner.assertsparam.viewmodel.FourthCategoryViewModelFactory
import com.owner.baselibrary.view.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_four_category.*

/**
 *
 * Created by Liuyong on 2018-11-17.It's AMSystem
 *@description:
 */
class FourCategoryFragment : BaseFragment<FragmentFourCategoryBinding, FourthCategoryViewModel>() {

    private lateinit var fourAdapter: FourthCgAdapter
    private var tableName: String = ""
    private var isEdited: Boolean = false//当前界面是否用于编辑
    private var isQuery: Boolean = false // 当前界面是否用于查询
    private var thirdCg = CategoryInfo("", "")

    companion object {
        fun newInstance(tableName: String, isEdited: Boolean, isQuery: Boolean, thirdCg: CategoryInfo)
                : FourCategoryFragment = FourCategoryFragment().apply {
            val bundle = Bundle()
            bundle.putString("tablename", tableName)
            bundle.putBoolean("isEdited", isEdited)
            bundle.putBoolean("isQuery", isQuery)
            bundle.putParcelable("thirdCg", thirdCg)
            arguments = bundle
            return this
        }

        const val SELECT_CATEGORY_REQUEST_CODE=100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments!!
        tableName = bundle.getString("tablename")!!
        isEdited = bundle.getBoolean("isEdited")
        isQuery = bundle.getBoolean("isQuery")
        thirdCg = bundle.getParcelable("thirdCg")!!

        viewModel = ViewModelProviders.of(this,
                FourthCategoryViewModelFactory(tableName, isEdited, isQuery,thirdCg))
                .get(FourthCategoryViewModel::class.java)
        viewModel.refresh.observe(this, Observer {
            fourAdapter.updateList()
        })

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentFourCategoryBinding.inflate(inflater,
                container, false)

        binding.fourVM = viewModel
        if (!isEdited) {
            binding.mFourDetailHDB.getRightView().visibility = View.VISIBLE
            binding.mFourDetailHDB.getRightView().text ="完成"
            binding.mFourDetailHDB.getRightView().setOnClickListener {
                val bundle = Bundle()
                bundle.putParcelable("categoryInfo",viewModel.currentSelected)
                Logger.d(viewModel.currentSelected.name)
                bundle.putString("tableName",tableName)
                val result = Intent()
                result.putExtra("fourthCg",bundle)
                activity!!.setResult(SELECT_CATEGORY_REQUEST_CODE,result)
                activity!!.finish()
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fourAdapter = FourthCgAdapter(thirdCg, viewModel)

        val manager = GridLayoutManager(context, 3)
        mFourDetailRV.adapter = fourAdapter
        mFourDetailRV.layoutManager = manager

        mFourDetailHDB.getTitleView().text = "分类明细"
    }
}