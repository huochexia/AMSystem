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
import com.alibaba.android.arouter.launcher.ARouter
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.orhanobut.logger.Logger
import com.owner.assertsparam.Interface.QueryAssertsInfo
import com.owner.assertsparam.R
import com.owner.assertsparam.data.CategoryInfo
import com.owner.assertsparam.databinding.FragementCategoryBinding
import com.owner.assertsparam.view.adapter.SecondCgAdapter
import com.owner.assertsparam.view.adapter.TopCgAdapter
import com.owner.assertsparam.viewmodel.ArgumentViewModel
import com.owner.assertsparam.viewmodel.CategoryFgViewModel
import com.owner.assertsparam.viewmodel.CategoryViewModelFactory
import com.owner.baselibrary.utils.hideSoftInput
import com.owner.provideslib.router.RouterPath
import kotlinx.android.synthetic.main.fragement_category.*

/**
 *
 * Created by Liuyong on 2018-10-20.It's AMSystem
 *@description:
 */

class CategoryFragment : ImageCategoryFragment<FragementCategoryBinding, CategoryFgViewModel>() {

    private lateinit var sharedViewModel: ArgumentViewModel//用于保存每个参数的选择结果
    private var tableName: String = ""
    private var isEdited: Boolean = true//当前界面是否用于编辑
    private var isQuery: Boolean = false // 当前界面是否用于查询

    lateinit var queryInterface: QueryAssertsInfo

    private lateinit var topAdapter: TopCgAdapter
    private lateinit var secondAdapter: SecondCgAdapter

    //定义总分类对象
    private val category = CategoryInfo("0", "")
    //当前选择的一级分类
    private var currentTopCategory = CategoryInfo("", "")

    /*
      获得外部传入的分类名称
     */
    companion object {
        fun newInstance(tableName: String, isEdited: Boolean, isQuery: Boolean)
                = CategoryFragment().apply {
            val bundle = Bundle()
            bundle.putString("tableName", tableName)
            bundle.putBoolean("isEdited", isEdited)
            bundle.putBoolean("isQuery", isQuery)
            arguments = bundle
            return this
        }

        const val SELECT_ITEM_REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = arguments!!
        tableName = bundle.getString("tableName")!!
        isEdited = bundle.getBoolean("isEdited")
        isQuery = bundle.getBoolean("isQuery")

        sharedViewModel = ViewModelProviders.of(activity!!).get(ArgumentViewModel::class.java)

        initViewModel()


    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding = DataBindingUtil.inflate<FragementCategoryBinding>(
                inflater, R.layout.fragement_category, container, false)
        binding.categoryVM = viewModel
        //绑定总资产对象
        binding.category = category
        binding.mHeaderBar.getTitleView().text = viewModel.categoryName
        if (!isEdited && !isQuery) { //选择状态
            initRightView(binding)
        }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadTopCgList()
        mSecondCategoryRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    /**
     * 初始化HeadBar的右标题
     */
    private fun initRightView(binding: FragementCategoryBinding) {
        binding.mHeaderBar.getRightView().text = "完成"
        binding.mHeaderBar.getRightView().visibility = View.VISIBLE
        binding.mHeaderBar.getRightView().setOnClickListener {
            Logger.d(sharedViewModel.selectedArgumentMap.toString())
        }
    }

    /**
     * 初始化ViewModel
     */
    private fun initViewModel() {
        // 通过工厂方法将分类名称传入ViewModel中
        viewModel = ViewModelProviders.of(this, CategoryViewModelFactory(tableName, isEdited, isQuery))
                .get(CategoryFgViewModel::class.java)

        //观察行为信息的变化，做出相应的响应
        viewModel.action.observe(this, Observer {
            //如果parentId为空，则是一级分类
            executeAction(it!!)
        })
        //观察刷新列表请求
        viewModel.refreshList.observe(this, Observer {
            when (it?.second) {
                0 -> topAdapter.notifyDataSetChanged()
                1 -> {
                    secondAdapter.updateList()
                    secondAdapter.notifyDataSetChanged()
                }
            }
        })
        //观察是否展开显示更多三级分类状态
        viewModel.expandList.observe(this, Observer {
            secondAdapter.notifyDataSetChanged()
        })
        //得到所选择的分类,返回值
        viewModel.getCategoryInfo.observe(this, Observer {
            sharedViewModel.selectedArgumentMap[it!!.first] = it.second
        })
        //观察是否要查询
        viewModel.gotoQueryAsserts.observe(this, Observer {
            queryInterface.queryAssert(it!!.first, it.second)
        })
        //启动四级分类明细
        viewModel.selectedFourthCg.observe(this, Observer {
            ARouter.getInstance().build(RouterPath.AssertsParam.PATH_ASSERTSPARAM_FOUR)
                    .withString("tableName", tableName)
                    .withBoolean("isEdited", isEdited)
                    .withBoolean("isQuery", isQuery)
                    .withParcelable("thirdCg", it)
                    .navigation(activity, SELECT_ITEM_REQUEST_CODE)
        })
    }


    /**
     *对由ViewModel发生的事件进行筛分，对应处理
     */
    private fun executeAction(it: Pair<String, CategoryInfo>) {
        when (it.first) {
            CategoryFgViewModel.KEY_SELECTED_ACTION -> selectCategory(it.second)
            CategoryFgViewModel.KEY_UPDATE_ACTION -> updateCategory(it.second)
            CategoryFgViewModel.KEY_DELETE_ACTION -> deleteCategory(it.second)
            CategoryFgViewModel.KEY_ADD_ACTION -> addCategory(it.second)
            CategoryFgViewModel.KEY_ADD_THIRD_ACTION -> addThirdCategory(it.second)
            CategoryFgViewModel.KEY_UPDATE_THIRD_ACTION -> updateThirdCategory(it.second)
        }
    }


    /**
     * 加载一级列表
     */
    private fun loadTopCgList() {
        mTopCategoryRv.layoutManager = LinearLayoutManager(context)
        topAdapter = TopCgAdapter(viewModel)
        mTopCategoryRv.adapter = topAdapter
    }

    /**
     * 加载二级分类列表
     */
    private fun loadSecondCgList(category: CategoryInfo) {
        secondAdapter = SecondCgAdapter(category, viewModel)
        mSecondCategoryRv.adapter = secondAdapter
        secondAdapter.notifyDataSetChanged()
    }

    /**
     * 点击分类，显示列表
     */
    private fun selectCategory(category: CategoryInfo) {
        //判断是否是一级分类
        if (category.parentId == "0") {
            topAdapter.notifyDataSetChanged()
            //避免选择当前已选择一级分类时重新获取二级分类。
            if (currentTopCategory.objectId != category.objectId) {
                loadSecondCgList(category)//加载二级分类
                currentTopCategory = category//指定当前一级分类
            }
        } else {
            secondAdapter.notifyDataSetChanged()
        }
    }

    /**
     * 增加类别，在这里生成一个新子类。这里形成比在ViewModel中放便些
     * @parent:父类
     *
     */
    private fun addCategory(parent: CategoryInfo) {
        val extView = LayoutInflater.from(context).inflate(R.layout.edit_category_name, null)
        val editV = extView.findViewById<EditText>(R.id.mCgNameEt)
        val alertView = AlertView("增加类别", null, null, null,
                arrayOf("取消", "完成"), context, AlertView.Style.Alert,
                OnItemClickListener { _, position ->
                    activity?.hideSoftInput()
                    when (position) {
                        1 -> {
                            if (editV.text.isNullOrEmpty().not()) {
                                val name = editV.text.toString().trim()
                                val newCg = CategoryInfo("", name, parent.objectId)
                                viewModel.addCategory(newCg)
                                //设置父类的hasChild为true
                                parent.hasChild = true
                                viewModel.restoreState(parent)
                                viewModel.updateCategory(parent)
                            }
                        }
                    }
                })

        alertView.addExtView(extView).show()
    }

    /**
     * 增加三级分类,有图片
     */
    private fun addThirdCategory(parent: CategoryInfo) {
        //刷新列表，目的是取消之前做过的长按状态
        secondAdapter.notifyDataSetChanged()
        popupAddImageDialog("增加", parent) {
            viewModel.addCategory(it)
        }
    }


    /**
     * 修改三级分类，有图片
     */
    private fun updateThirdCategory(category: CategoryInfo) {

        popupUpdateImageDialog("修改", category) {
            viewModel.updateCategory(category)
            secondAdapter.notifyDataSetChanged()
        }
        viewModel.restoreState(category)
        //刷新列表，目的是取消之前做过的长按状态
        secondAdapter.notifyDataSetChanged()

    }

    /**
     * 显示修改类别对话框
     */
    private fun updateCategory(category: CategoryInfo) {
        val extView = LayoutInflater.from(context).inflate(R.layout.edit_category_name, null)
        val editV = extView.findViewById<EditText>(R.id.mCgNameEt)
        editV.setText(category.name)
        val alertView = AlertView("修改类别", null, null, null,
                arrayOf("取消", "完成"), context, AlertView.Style.Alert,
                OnItemClickListener { o, position ->
                    activity?.hideSoftInput()
                    when (position) {
                        1 -> {
                            category.name = editV.text.toString().trim()
                            viewModel.updateCategory(category)
                            if (category.parentId == "0") {
                                mAddSecondCgTv.text = category.name
                                category.isLongOnClick = false
                                category.isSelected = false
                                topAdapter.notifyDataSetChanged()
                            } else {
                                secondAdapter.notifyDataSetChanged()
                            }
                        }
                    }
                })
        alertView.addExtView(extView).show()
    }

    /**
     * 显示删除类别对话框
     */
    private fun deleteCategory(category: CategoryInfo) {
        popupDeleteDialog("删除操作",category){
            if (it.parentId == "0") {
                viewModel.topCgList.remove(it)
                viewModel.isVisibleTop = false
                topAdapter.notifyDataSetChanged()
            } else {
                viewModel.secondAndThirdCgList.remove(it)
                secondAdapter.updateList()
                secondAdapter.notifyDataSetChanged()
            }
            viewModel.deleteCategory(it)
        }
    }

}


