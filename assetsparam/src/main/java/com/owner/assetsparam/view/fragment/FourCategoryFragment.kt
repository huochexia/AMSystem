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
package com.owner.assetsparam.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bigkoo.alertview.AlertView
import com.owner.assetsparam.data.CategoryInfo
import com.owner.assetsparam.databinding.FragmentFourCategoryBinding
import com.owner.assetsparam.view.adapter.FourthCgAdapter
import com.owner.assetsparam.viewmodel.FourthCategoryViewModel
import com.owner.assetsparam.viewmodel.FourthCategoryViewModelFactory
import kotlinx.android.synthetic.main.fragment_four_category.*

/**
 *
 * Created by Liuyong on 2018-11-17.It's AMSystem
 *@description:
 */
class FourCategoryFragment : CRUDDialogFragment<FragmentFourCategoryBinding, FourthCategoryViewModel>() {

    private lateinit var fourAdapter: FourthCgAdapter
    private var tableName: String = ""
    private var isEdited: Boolean = false//当前界面是否用于编辑
    private var isQuery: Boolean = false // 当前界面是否用于查询
    private var thirdCg = CategoryInfo("", "")

    private lateinit var alertView: AlertView

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

        const val SELECT_CATEGORY_REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments!!
        tableName = bundle.getString("tablename")!!
        isEdited = bundle.getBoolean("isEdited")
        isQuery = bundle.getBoolean("isQuery")
        thirdCg = bundle.getParcelable("thirdCg")!!

        initViewModel()

    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this,
                FourthCategoryViewModelFactory(tableName, isEdited, isQuery, thirdCg))
                .get(FourthCategoryViewModel::class.java)
        viewModel.refresh.observe(this, Observer {
            fourAdapter.updateList()
        })
        viewModel.action.observe(this, Observer {
            when (it?.first) {
                FourthCategoryViewModel.ACTION_SELECTED -> fourAdapter.notifyDataSetChanged()
                FourthCategoryViewModel.ACTION_QUERY -> {
                }
                FourthCategoryViewModel.ACTION_DELETE -> deleteFourth(it.second)
                FourthCategoryViewModel.ACTION_ADD -> addFourth(it.second)
                FourthCategoryViewModel.ACTION_UPDATE -> updateFourth(it.second)
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding = FragmentFourCategoryBinding.inflate(inflater,
                container, false)

        binding.fourVM = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fourAdapter = FourthCgAdapter(thirdCg, viewModel)

        val manager = GridLayoutManager(context, 3)
        mFourDetailRV.adapter = fourAdapter
        mFourDetailRV.layoutManager = manager


    }

    private fun deleteFourth(fourth: CategoryInfo) {
        popupDeleteDialog("删除操作", fourth) {
            viewModel.fourthList.remove(it)
            fourAdapter.updateList()
            viewModel.deleteData(it)
        }
    }

    private fun addFourth(third: CategoryInfo) {
        popupAddDialog( third,true) {
            viewModel.addData(it)
            viewModel.fourthList.add(it)
            fourAdapter.updateList()
        }
    }

    private fun updateFourth(category: CategoryInfo) {
        popupUpdateDialog( category,true) {
            viewModel.updateData(it)
            fourAdapter.notifyDataSetChanged()
        }
        viewModel.restore(category)
        fourAdapter.notifyDataSetChanged()
    }
}