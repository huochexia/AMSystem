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
import com.alibaba.android.arouter.launcher.ARouter
import com.kennyc.view.MultiStateView
import com.orhanobut.logger.Logger
import com.owner.assertsparam.BR
import com.owner.assertsparam.data.CategoryInfo
import com.owner.assertsparam.data.Footer
import com.owner.assertsparam.model.repository.AssertsParamRepository
import com.owner.assertsparam.model.repository.impl.APRepositoryImpl
import com.owner.baselibrary.ext.execute
import com.owner.baselibrary.viewmodel.BaseViewModel
import com.owner.provideslib.router.RouterPath
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 *@typeName :分类的名称。该参数的意义是区分种类。因为所有分类的结构是一样的，只是名称不一样，这个名称
 * 对应数据库中表。因为使用RESTFULL，所以只需要区分表名即可。只要能够指定表名，就可以从不同表中获取相应
 * 的分类对象。
 * @isEdited :用于决定是否可以对数据进行编辑，当为true时，可进行增改删。当为false时不可编辑处于选择状态
 * @isQuery :当为真是当前状态处于查询，点击后得到查询结果。此时的isEdited应该是true
 * Created by Liuyong on 2018-10-20.It's AMSystem
 *@description:
 */
class CategoryFgViewModel(private val tableName: String, val isEdited: Boolean,val isQuery:Boolean)
    : BaseViewModel<AssertsParamRepository>() {

    companion object {
        const val KEY_SELECTED_ACTION = "select"
        const val KEY_UPDATE_ACTION = "update"
        const val KEY_DELETE_ACTION = "delete"
        const val KEY_ADD_ACTION = "add"
        const val KEY_ADD_THIRD_ACTION = "add_third"
        const val KEY_UPDATE_THIRD_ACTION = "update_third"
        const val KEY_REFRESH_LIST = "refresh"
    }

    //当前分类名称
    var categoryName: String = ""
        get() {
            when (tableName) {
                "Location" -> return "位置"
                "Category" -> return "资产"
                "Department" -> return "部门"
            }
            return ""
        }
    //点击事件行为，选择、增加、修改、删除.
    var action = MutableLiveData<Pair<String, CategoryInfo>>()

    // 得到选择的分类，<表名，分类>
    var getCategoryInfo = MutableLiveData<Pair<String, CategoryInfo>>()
    // 启动查询功能
    var gotoQueryAsserts = MutableLiveData<Pair<String,CategoryInfo>>()
    //是否展开三级分类列表的状态
    var expandList = MutableLiveData<Boolean>()
    //刷新列表
    var refreshList = MutableLiveData<Pair<String, Int>>()
    //一级分类数据列表
    var topCgList = mutableListOf<CategoryInfo>()
    //二级和三级分类数据表
    var secondAndThirdCgList = mutableListOf<CategoryInfo>()
    //选择的二级或三级分类
    private var mSelectedCategory: CategoryInfo? = null
    /*
        显示当前一级分类名称,用于在二级分类上显示一级分类名称
     */
    @get:Bindable
    var mCurrentTopCategory = CategoryInfo("", "")
        set(value) {
            field = value
            notifyPropertyChanged(BR.mCurrentTopCategory)
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
        loadTopCategory()
    }


    /**
     * 一级分类点击用于加载二、三级分类。二、三级分类点击事件分情况处理。如果是查询状态，调用查询接口；
     * 如果是非查询状态，只用于刷新列表，取消长按状态。
     */
    fun itemOnClick(item: CategoryInfo) {
        if (item.parentId == "0" ) {
            setClickState(item)
            //如果是重新选择一级分类，则加载二级列表数据,避免多次网络访问
            if (mCurrentTopCategory.objectId != item.objectId) {
                loadSecondCategory(item)
            } else {
                action.value = Pair(KEY_SELECTED_ACTION, item)
            }
            mCurrentTopCategory = item
            mSelectedCategory = null
        }else{
            if (isQuery) {
                //驱动查询
                gotoQueryAsserts.value=Pair(tableName,item)
            } else {
                setClickState(item)
                isAgainClick(item)
                action.value = Pair(KEY_SELECTED_ACTION, item)
            }

        }
    }

    /**
     * 如果三级分类有子类，则进入下一级。否则再判断
     */
    fun thirdItemClick(item: CategoryInfo) {
        when {
            item.hasChild -> ARouter.getInstance().build(RouterPath.AssertsParam.PATH_ASSERTSPARAM_FOUR)
                    .withString("tableName", tableName)
                    .withBoolean("isEdited", isEdited)
                    .withBoolean("isQuery", isQuery)
                    .withParcelable("thirdCg", item)
                    .navigation()
            isQuery && isEdited-> //驱动查询
                gotoQueryAsserts.value = Pair(tableName, item)
            !isEdited->selectedCategory(item)
            else -> {
                setClickState(item)
                isAgainClick(item)
                action.value = Pair(KEY_SELECTED_ACTION, item)
            }
        }
    }
    /**
     * 选择二、三级分类，第一次选择，第二次取消选择
     */
    fun selectedCategory(item: CategoryInfo) {
        //二次点击取消选择，如果点击的是当前分类，则取消选择。如果不是，则重新选择
        isAgainClick(item)
        //通知视图状态改变
        action.value = Pair(KEY_SELECTED_ACTION, item)
        //保存选择,如果已选择分类为 null，则存入一个id为空字符串的分类对象。使用时在进行判断
        getCategoryInfo.value = Pair(tableName,mSelectedCategory?:CategoryInfo("-1",""))
    }

    private fun isAgainClick(item: CategoryInfo) {
        if (item.objectId == mSelectedCategory?.objectId ?: "") {
            item.isSelected = !item.isSelected
        } else {
            //点击二级或三级任意一个时，还原其选择状态
            secondAndThirdCgList.forEach {
                it.isLongOnClick = false
                it.isSelected = false
            }
            item.isSelected = true
        }
        mSelectedCategory = if (item.isSelected) {
            item
        } else {
            null
        }
    }

    /**
     *  从二三级分类列表中过滤出某个二级分类的子类
     *
     */
    fun getChildList(item: CategoryInfo): List<CategoryInfo> {
        return secondAndThirdCgList.filter {
            it.parentId == item.objectId
        }
    }
    /**
     * 设置分类选择时的状态
     */
    private fun setClickState(item: CategoryInfo) {
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
     * 列表项目长按事件,如果可编辑状态时返回true
     */
    fun itemLongClick(item: CategoryInfo): Boolean {

        if (isEdited) {
            //如果是一级分类
            if (item.parentId == "0") {
                setLongClickState(item, topCgList)
                //如果当前一级分类不是传入分类，则要加载二级分类列表，并重新指定当前一级分类
                if (mSelectedCategory?.objectId != item.objectId) {
                    loadSecondCategory(item)
                }
                action.value = Pair(KEY_SELECTED_ACTION, item)
            } else {
                setLongClickState(item, secondAndThirdCgList)
                action.value = Pair(KEY_SELECTED_ACTION, item)
            }
            return true
        }
        return false
    }

    /**
     * 设置分类项目长按时状态
     */
    private fun setLongClickState(item: CategoryInfo, list: MutableList<CategoryInfo>) {
        //还原所有选择和长按状态
        list.forEach {
            it.isLongOnClick = false
            it.isSelected = false
        }
        item.isSelected = true
        item.isLongOnClick = true
        if (item.parentId != "0") {
            mSelectedCategory = item
        } else {
            mCurrentTopCategory = item
        }
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
        subList.addAll(secondAndThirdCgList.filter {
                     it.parentId==parent.objectId
        })
        return subList
    }

    /**
     * 加载一级分类列表
     */
    private fun loadTopCategory() {
        val disposable = repo.getCategory(tableName, "0").execute()
                .subscribe({
                    topCgList.addAll(it.results)
                }, {
                    mTopViewState = MultiStateView.VIEW_STATE_ERROR
                    Logger.d(it.message)
                }, {
                    mTopViewState = MultiStateView.VIEW_STATE_CONTENT
                }, {
                    mTopViewState = MultiStateView.VIEW_STATE_LOADING
                })
        compositeDisposable.add(disposable)
    }

    /**
     * 加载一级分类的二级以及其下的三级分类。
     * 原理：分别产生两个列表流，一个是二级分类，然后利用二级分类得到三级分类，最后合并两个列表流
     * @item: 一级分类
     */
    private fun loadSecondCategory(item: CategoryInfo) {
        secondAndThirdCgList.clear()
        //二级分类数据流
        val secondList = repo.getCategory(tableName, item.objectId).flatMap {
            Observable.fromIterable(it.results)
        }
        //三级分类数据流
        val thirdList = secondList.flatMap {
            repo.getCategory(tableName, it.objectId)
        }.flatMap {
            Observable.fromIterable(it.results)
        }
        //合并数据流
        val disposable = Observable.merge(secondList, thirdList).execute()
                .subscribe({
                    secondAndThirdCgList.add(it)
                }, {
                    mSecondViewState = MultiStateView.VIEW_STATE_ERROR
                }, {
                    mSecondViewState = MultiStateView.VIEW_STATE_CONTENT
                    //通知视图状态改变
                    action.value = Pair(KEY_SELECTED_ACTION, item)

                }, {
                    mSecondViewState = MultiStateView.VIEW_STATE_LOADING
                })
        isVisibleTop = true
        compositeDisposable.add(disposable)
    }
    /**
     * 发送增加一级分类请求
     *
     */
    fun addTopAlert(top: CategoryInfo) {
        //发送增加一级分类信号
        action.value = Pair(KEY_ADD_ACTION, top)
    }

    /**
     * 发送增加二级分类请求
     */
    fun addSecondAlert() {
        action.value = Pair(KEY_ADD_ACTION, mCurrentTopCategory)
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
     * 对数据库执行保存操作,保存成功后返回新增对象。对新增对象区分，一级分类加入topCgList中，刷新列表。
     * 二级分类加入secondAndThirdCgList中，刷新二级列表。
     *
     */
    fun addCategory(newCg: CategoryInfo) {
        val disposable = repo.createCategory(tableName, newCg.name, newCg.parentId, newCg.imageUrl)
                .execute()
                .subscribe({
                    if (it.parentId == "0") {
                        topCgList.add(it)
                        mCurrentTopCategory = it
                        isVisibleTop = true
                        setClickState(it)
                        action.value = Pair(KEY_SELECTED_ACTION, it)
                    } else {
                        secondAndThirdCgList.add(it)
                        refreshList.value = Pair(KEY_REFRESH_LIST, 1)
                    }

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
        val disposable = repo.updateCategory(tableName, category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        compositeDisposable.add(disposable)
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
        val disposable = repo.deleteCategory(tableName, category.objectId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        compositeDisposable.add(disposable)
    }

}