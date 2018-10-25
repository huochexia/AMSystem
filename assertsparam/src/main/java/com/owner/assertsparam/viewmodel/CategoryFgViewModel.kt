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
import com.owner.assertsparam.BR
import com.owner.assertsparam.data.CategoryInfo
import com.owner.assertsparam.model.repository.AssertsParamRepository
import com.owner.baselibrary.viewmodel.BaseViewModel

/**
 *
 * Created by Liuyong on 2018-10-20.It's AMSystem
 *@description:
 */
class CategoryFgViewModel : BaseViewModel<AssertsParamRepository>() {

    companion object {
        const val KEY_SELECTED_ACTION = "selecte"
        const val KEY_UPDATE_ACTION = "update"
        const val KEY_DELETE_ACTION = "delete"
    }
    //点击事件行为，选择、修改、删除
    var action = MutableLiveData<Pair<String, CategoryInfo>>()
    //一级类别
    var topCgList = mutableListOf<CategoryInfo>()
    //二级和三级类别库
    var secondAndThirdCgList = mutableListOf<CategoryInfo>()
    var thirdCgList = mutableListOf<CategoryInfo>()


    /*
       多状态视图参数，此处只能用这种形式，因为布局中属性值是Int类。如果用observableInt或者是LiveData<Int>
       都会给该值增加封装，造成databinding失败
     */
    @get:Bindable
    var viewState = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.viewState)
        }

    init {
        val top1 = CategoryInfo("1", "电教设备", "0")
        val top2 = CategoryInfo("2", "办公家具", "0")
        val top3 = CategoryInfo("3", "学员公寓家俱", "0")
        val second1 = CategoryInfo("11","电脑主机","1")
        val second2 = CategoryInfo("12","电脑显示器","1")
        val second3 = CategoryInfo("21","桌子","2")
        val third1 = CategoryInfo("111","清华同方","11","https://img14.360buyimg.com/n0/jfs/t3157/231/5756125504/98807/97ab361d/588084a1N06efb01d.jpg")
        val third2 = CategoryInfo("112","联想","11" ,"https://img10.360buyimg.com/n7/jfs/t5905/106/1120548052/61075/6eafa3a5/592f8f7bN763e3d30.jpg")
        val third3 = CategoryInfo("113","IBM","11","https://img10.360buyimg.com/n7/jfs/t5905/106/1120548052/61075/6eafa3a5/592f8f7bN763e3d30.jpg")
        val third4 = CategoryInfo("114","SONY","11","https://img10.360buyimg.com/n7/jfs/t5584/99/6135095454/371625/423b9ba5/59681d91N915995a7.jpg")
        val third5 = CategoryInfo("211","三星","12","https://img10.360buyimg.com/n7/jfs/t5584/99/6135095454/371625/423b9ba5/59681d91N915995a7.jpg")
        val third6 = CategoryInfo("212","ThinkVision","21","https://img14.360buyimg.com/n1/s190x190_jfs/t7525/189/155179632/395056/e200017f/598fb8a4N7800dee6.jpg")
        topCgList.add(top1)
        topCgList.add(top2)
        topCgList.add(top3)
        secondAndThirdCgList.add(second1)
        secondAndThirdCgList.add(second2)
        secondAndThirdCgList.add(second3)
        secondAndThirdCgList.add(third1)
        secondAndThirdCgList.add(third2)
        secondAndThirdCgList.add(third3)
        secondAndThirdCgList.add(third4)
        secondAndThirdCgList.add(third5)
        secondAndThirdCgList.add(third6)

    }

    //存储用户当前选择的类别对象，并触发刷新事件
    var selectedCg = MutableLiveData<CategoryInfo>()

    /**
     * 一级列表项目点击事件
     */
    fun topItemOnClick(item: CategoryInfo) {
        //还原所有选择和长按状态
        topCgList.forEach {
            it.isSelected = false
            it.isLongOnClick = false
        }
        //将当前项目设定为选择状态
        item.isSelected = true
        action.value = Pair(KEY_SELECTED_ACTION,item)
        //加载二级列表数据
        loadSecondCategory(item.id)

    }

    /**
     * 加载二级分类
     * @id : 二级分类的parentId
     */
    private fun loadSecondCategory(id: String) {

    }

    /**
     * 一级列表项目长按事件
     */
    fun topItemLongClick(item: CategoryInfo): Boolean {
        //还原所有选择和长按状态
        topCgList.forEach {
            it.isLongOnClick = false
            it.isSelected = false
        }
        item.isSelected = true
        item.isLongOnClick = true
        action.value = Pair(KEY_SELECTED_ACTION,item)
        return true
    }

    /**
     * 启动修改类别对话框
     */
    fun updateAlert(category: CategoryInfo) {
        category.isLongOnClick = false
        action.value = Pair(KEY_UPDATE_ACTION, category)
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
        action.value = Pair(KEY_DELETE_ACTION,category)
    }

    /**
     *  对数据库执行删除操作
     */
    fun deleteCategory(category: CategoryInfo) {

    }
}