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
import com.owner.assertsparam.view.adapter.TopCgAdapter
import com.owner.assertsparam.viewmodel.CategoryFgViewModel
import com.owner.baselibrary.view.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragement_category.*

/**
 *
 * Created by Liuyong on 2018-10-20.It's AMSystem
 *@description:
 */
class CategoryFragment : BaseFragment<FragementCategoryBinding, CategoryFgViewModel>() {


    lateinit var adapter: TopCgAdapter
    lateinit var alertView: AlertView
    //输入方法管理
//    lateinit var imm: InputMethodManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CategoryFgViewModel::class.java)

        //设置LiveData对象变化监听器
        viewModel.selectedCg.observe(this, Observer {
            adapter.notifyDataSetChanged()

        })
//        imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
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
        mTopCategoryRv.layoutManager = LinearLayoutManager(context)
        adapter = TopCgAdapter(viewModel)
        mTopCategoryRv.adapter = adapter

        mTopCategoryBtn.setOnClickListener {
            AlertView.Builder().setContext(context)
                    .setStyle(AlertView.Style.Alert)
                    .setDestructive("增加", "修改", "删除")
                    .setOnItemClickListener { _, position ->
                        when (position) {
                            0 -> addCategory()
                        }
                    }
                    .build().setCancelable(true).show()
//            addCategory()
        }
    }

    /**
     * 增加类别
     */
    fun addCategory() {
        alertView = AlertView("增加类别", null, "取消", null,
                arrayOf("完成"), context, AlertView.Style.Alert, OnItemClickListener { o, position -> })
        val extView = LayoutInflater.from(context).inflate(R.layout.edit_category_name, null)
        val editV = extView.findViewById<EditText>(R.id.mCgNameEt)
//        editV.setOnFocusChangeListener { view, b ->
//            val isOpen = imm.isActive
//            if (isOpen && b) {
//                alterView.setMarginBottom(120)
//            } else {
//                alterView.setMarginBottom(0)
//            }
//        }
        alertView.addExtView(extView).show()
    }

    /**
     * 修改类别
     */
    fun updateCategory(cateName: String) {
        alertView = AlertView("增加类别", null, "取消", null,
                arrayOf("完成"), context, AlertView.Style.Alert, OnItemClickListener { o, position -> })
        val extView = LayoutInflater.from(context).inflate(R.layout.edit_category_name, null)
        val editV = extView.findViewById<EditText>(R.id.mCgNameEt)
        editV.setText(cateName)
        alertView.addExtView(extView).show()
    }

    /**
     * 修改类别
     */
    fun deleteCategory(category: CategoryInfo) {
        alertView = AlertView("增加类别", null, "取消", null,
                arrayOf("确定"), context, AlertView.Style.Alert, OnItemClickListener { o, position -> })

        alertView.show()
    }


}


