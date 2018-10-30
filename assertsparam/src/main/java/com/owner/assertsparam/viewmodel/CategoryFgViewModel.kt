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
package com.owner.assertsparam.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.databinding.Bindable
import com.kennyc.view.MultiStateView
import com.orhanobut.logger.Logger
import com.owner.assertsparam.BR
import com.owner.assertsparam.data.CategoryInfo
import com.owner.assertsparam.data.Footer
import com.owner.assertsparam.model.repository.AssertsParamRepository
import com.owner.assertsparam.model.repository.impl.APRepositoryImpl
import com.owner.baselibrary.ext.execute
import com.owner.baselibrary.viewmodel.BaseViewModel
import retrofit2.HttpException
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import com.google.gson.JsonObject



/**
 *
 * Created by Liuyong on 2018-10-20.It's AMSystem
 *@description:
 */
class CategoryFgViewModel : BaseViewModel<AssertsParamRepository>() {

    companion object {
        const val KEY_SELECTED_ACTION = "select"
        const val KEY_UPDATE_ACTION = "update"
        const val KEY_DELETE_ACTION = "delete"
        const val KEY_ADD_ACTION = "add"
        const val KEY_ADD_THIRD_ACTION = "add_third"
        const val KEY_UPDATE_THIRD_ACTION = "update_third"
        const val KEY_REFRESH_LIST = "refresh"
    }

    //点击事件行为，选择、增加、修改、删除.
    var action = MutableLiveData<Pair<String, CategoryInfo>>()
    //是否展开三级分类列表的状态
    var expandList = MutableLiveData<Boolean>()
    //刷新列表
    var refreshList = MutableLiveData<Pair<String, Int>>()
    //一级分类数据列表
    var topCgList = mutableListOf<CategoryInfo>()
    //二级和三级分类数据表
    var secondAndThirdCgList = mutableListOf<CategoryInfo>()
    /*
        显示当前一级分类
     */
    @get:Bindable
    var currentTopCategory = CategoryInfo("", "")
        set(value) {
            field = value
            notifyPropertyChanged(BR.currentTopCategory)
        }
    /*
       是否显示当前一级分类
     */
    @get:Bindable
    var isVisibleTop = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.visibleTop)
        }
    /*
       多状态视图参数，此处只能用这种形式，因为布局中属性值是Int类。如果用observableInt或者是LiveData<Int>
       都会给该值增加封装，造成databinding失败
     */
    @get:Bindable
    var mSecondViewState = MultiStateView.VIEW_STATE_EMPTY
        set(value) {
            field = value
            notifyPropertyChanged(BR.mSecondViewState)
        }
    @get:Bindable
    var mTopViewState = MultiStateView.VIEW_STATE_EMPTY
        set(value) {
            field = value
            notifyPropertyChanged(BR.mTopViewState)
        }


    init {
        repo = APRepositoryImpl()
        initTopCategory()
    }

    /**
     * 初始化一级分类列表
     */
    fun initTopCategory() {
         val disposable = repo.getCategory("5bd6bac1ac502e0061f74acd").execute()
                 .subscribe({
                    topCgList.addAll(it.results)
                 },{
                     Logger.d(it.toString())
                 },{
                     mTopViewState = MultiStateView.VIEW_STATE_CONTENT
                 },{
                     mTopViewState = MultiStateView.VIEW_STATE_LOADING
                 })
        compositeDisposable.add(disposable)
    }
    /**
     * 列表项目点击事件
     */
    fun itemOnClick(item: CategoryInfo) {
        //如果是一级分类，设置一级分类项目状态
        if (item.parentId == "0" ) {
            setTopCgState(item)
            //加载二级列表数据
            loadSecondCategory(item.objectId)
            currentTopCategory = item
            isVisibleTop = true
        } else {
            //点击二级或三级任意一个时，还原其选择状态
            secondAndThirdCgList.forEach {
                it.isLongOnClick = false
                it.isSelected = false
            }
        }
        //通知视图状态改变
        action.value = Pair(KEY_SELECTED_ACTION, item)
    }

    /**
     * 设置一级分类选择时的状态
     */
    private fun setTopCgState(item: CategoryInfo) {
        //还原所有选择和长按状态
        topCgList.forEach {
            it.isSelected = false
            it.isLongOnClick = false
        }
        //还原二级和三级，因为在二级或三级中改变状态后，点击一级要做还原
        secondAndThirdCgList.forEach {
            it.isSelected = false
            it.isLongOnClick = false
        }
        //将当前项目设定为选择状态
        item.isSelected = true
    }

    /**
     * 加载二级分类
     * @id : 二级分类的parentId
     */
    private fun loadSecondCategory(id: String) {

    }

    /**
     * 列表项目长按事件
     */
    fun itemLongClick(item: CategoryInfo): Boolean {
        //如果是一级分类
        if (item.parentId == "0") {
            setTopCgLongState(item)
        } else {
            setSecondOrThirdLongState(item)
        }
        action.value = Pair(KEY_SELECTED_ACTION, item)
        return true
    }

    /**
     * 设置一级分类项目长按时状态
     */
    private fun setTopCgLongState(item: CategoryInfo) {
        //还原所有选择和长按状态
        topCgList.forEach {
            it.isLongOnClick = false
            it.isSelected = false
        }
        item.isSelected = true
        item.isLongOnClick = true
    }

    /**
     * 设置二级分类项目长按时状态
     */
    private fun setSecondOrThirdLongState(item: CategoryInfo) {
        //还原所有选择和长按状态
        secondAndThirdCgList.forEach {
            it.isLongOnClick = false
        }
        item.isLongOnClick = true
    }


    /**
     * 展开和收缩三级分类列表
     */
    fun expandThirdCategory(moreView: ThirdCgMoreView) {
        moreView.isExpanded = !moreView.isExpanded
        expandList.value = moreView.isExpanded
    }

    /**
     * 获取子分类
     */
    fun getSubCategory(parent: CategoryInfo): MutableList<CategoryInfo> {
        val subList = mutableListOf<CategoryInfo>()
        secondAndThirdCgList.forEach {
            if (it.parentId == parent.objectId) {
                subList.add(it)
            }
        }
        return subList
    }

    /**
     * 发送增加一级分类请求
     *
     */
    fun addTopAlert(top: CategoryInfo) {
        //如果是总分类，还原最初始状态
        isVisibleTop = false
        setTopCgState(top)
        action.value = Pair(KEY_ADD_ACTION, top)
    }

    /**
     * 发送增加二级分类请求
     */
    fun addSecondAlert() {
        action.value = Pair(KEY_ADD_ACTION, currentTopCategory)
    }

    /**
     * 发送增加三级分类请求
     */
    fun addThirdAlert(second: Footer) {
        secondAndThirdCgList.forEach {
            it.isLongOnClick = false
        }
        action.value = Pair(KEY_ADD_THIRD_ACTION, second.category)
    }

    /**
     * 对数据库执行保存操作
     */
    fun addCategory(newCg: CategoryInfo) {

        val disposable = repo.createCategory(newCg.name, newCg.parentId, newCg.imageUrl).execute()
                .subscribe({
                    val category = CategoryInfo.create(it)
                    if (category.parentId == "0"){
                        topCgList.add(category)
                        refreshList.value = Pair(KEY_REFRESH_LIST, 0)
                    }else
                        secondAndThirdCgList.add(category)
                        refreshList.value = Pair(KEY_REFRESH_LIST,1)
                }, {
                }, {

                })
        compositeDisposable.add(disposable)
    }

    /**
     * 启动修改一级或二级分类对话框
     */
    fun updateAlert(category: CategoryInfo) {
        category.isLongOnClick = false
        action.value = Pair(KEY_UPDATE_ACTION, category)
    }

    /**
     * 启动修改三级分类对话框
     */
    fun updateThirdAlert(third: CategoryInfo) {
        third.isLongOnClick = false
        action.value = Pair(KEY_UPDATE_THIRD_ACTION, third)
    }

    /**
     * 对数据库执行修改操作
     */
    fun updateCategory(category: CategoryInfo) {

    }

    /**
     * 启动删除类别对话框
     */
    fun deleteAlert(category: CategoryInfo) {
        category.isLongOnClick = false
        action.value = Pair(KEY_DELETE_ACTION, category)
    }

    /**
     *  对数据库执行删除操作
     */
    fun deleteCategory(category: CategoryInfo) {

    }

}