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
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.owner.assertsparam.R
import com.owner.assertsparam.data.CategoryInfo
import com.owner.assertsparam.databinding.FragementCategoryBinding
import com.owner.assertsparam.view.adapter.SecondCgAdapter
import com.owner.assertsparam.view.adapter.TopCgAdapter
import com.owner.assertsparam.viewmodel.CategoryFgViewModel
import com.owner.baselibrary.utils.hideSoftInput
import com.owner.baselibrary.view.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragement_category.*

/**
 *
 * Created by Liuyong on 2018-10-20.It's AMSystem
 *@description:
 */
class CategoryFragment : BaseFragment<FragementCategoryBinding, CategoryFgViewModel>() {


    private lateinit var topAdapter: TopCgAdapter
    private lateinit var secondAdapter: SecondCgAdapter
    private lateinit var alertView: AlertView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CategoryFgViewModel::class.java)

        //观察状态信息的变化，做出相应的响应
        viewModel.action.observe(this, Observer {
            topAdapter.notifyDataSetChanged()
            when (it?.first) {
                CategoryFgViewModel.KEY_SELECTED_ACTION -> setSecondCgList(it.second)
                CategoryFgViewModel.KEY_UPDATE_ACTION -> updateCategory(it.second)
                CategoryFgViewModel.KEY_DELETE_ACTION -> deleteCategory(it.second)
            }
        })

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding = DataBindingUtil.inflate<FragementCategoryBinding>(
                inflater, R.layout.fragement_category, container, false)
        binding.categoryVM = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTopCgList()
//        initSecondCgList()

    }


    /**
     * 初始化一级列表
     */
    private fun initTopCgList() {
        mTopCategoryRv.layoutManager = LinearLayoutManager(context)
        topAdapter = TopCgAdapter(viewModel)
        mTopCategoryRv.adapter = topAdapter
        mTopCategoryBtn.setOnClickListener {
            addCategory()
        }
    }

    /**
     * 设置二级列表
     */
    private fun setSecondCgList(category: CategoryInfo) {
        mSecondCategoryRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        secondAdapter = SecondCgAdapter(category,viewModel)
        mSecondCategoryRv.adapter = secondAdapter
    }

    /**
     * 增加类别
     */
    fun addCategory() {
        alertView = AlertView("增加类别", null, null, null,
                arrayOf("取消", "完成"), context, AlertView.Style.Alert, OnItemClickListener { o, position ->
            activity?.hideSoftInput()
            when (position) {

            }
        })
        val extView = LayoutInflater.from(context).inflate(R.layout.edit_category_name, null)
        val editV = extView.findViewById<EditText>(R.id.mCgNameEt)
        alertView.addExtView(extView).show()
    }

    /**
     * 显示修改类别对话框
     */
    fun updateCategory(category: CategoryInfo) {
        alertView = AlertView("修改类别", null, null, null,
                arrayOf("取消", "完成"), context, AlertView.Style.Alert, OnItemClickListener { o, position ->
            activity?.hideSoftInput()
            when (position) {
                1 -> viewModel.updateCategory(category)
            }
        })
        val extView = LayoutInflater.from(context).inflate(R.layout.edit_category_name, null)
        val editV = extView.findViewById<EditText>(R.id.mCgNameEt)
        editV.setText(category.name)
        alertView.addExtView(extView).show()
    }

    /**
     * 显示删除类别对话框
     */
    fun deleteCategory(category: CategoryInfo) {
        alertView = AlertView("删除类别", null, null, null,
                arrayOf("取消", "确定"), context, AlertView.Style.Alert, OnItemClickListener { o, position ->
            activity?.hideSoftInput()
            when (position) {
                0 -> viewModel.deleteCategory(category)
            }
        })
        alertView.show()
    }


}


