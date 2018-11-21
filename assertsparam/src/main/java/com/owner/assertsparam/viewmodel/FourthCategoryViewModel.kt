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
import com.owner.assertsparam.BR
import com.owner.assertsparam.data.CategoryInfo
import com.owner.assertsparam.data.Footer
import com.owner.assertsparam.model.repository.AssertsParamRepository
import com.owner.assertsparam.model.repository.impl.APRepositoryImpl
import com.owner.baselibrary.ext.execute
import com.owner.baselibrary.viewmodel.BaseViewModel

/**
 *
 * Created by Liuyong on 2018-11-18.It's AMSystem
 *@description:
 */
class FourthCategoryViewModel(val tablename: String, val isEdited: Boolean, val isQuery: Boolean, val thirdCg: CategoryInfo)
    : BaseViewModel<AssertsParamRepository>() {
    companion object {
        const val ACTION_SELECTED = "selected"
        const val ACTION_QUERY = "query"
        const val ACTION_ADD = "add"
        const val ACTION_UPDATE = "update"
        const val ACTION_DELETE = "delete"
    }

    //可观察到的行为
    var action = MutableLiveData<Pair<String, CategoryInfo>>()


    var fourthList = mutableListOf<CategoryInfo>()
    var currentSelected: CategoryInfo = CategoryInfo("-1", "")

    init {

        repo = APRepositoryImpl()
        getFourthCgList(thirdCg)
    }

    @get:Bindable
    var mStateView = MultiStateView.VIEW_STATE_EMPTY
        set(value) {
            field = value
            notifyPropertyChanged(BR.mStateView)
        }
    var refresh = MutableLiveData<String>()
    /**
     *  得到分类信息列表
     */
    private fun getFourthCgList(item: CategoryInfo) {
        val disposable = repo.getCategory(tablename, thirdCg.objectId).execute()
                .subscribe({
                    fourthList.addAll(it.results)
                }, {
                    mStateView = MultiStateView.VIEW_STATE_ERROR
                }, {
                    mStateView = MultiStateView.VIEW_STATE_CONTENT
                    refresh.value = "complete"
                }, {
                    mStateView = MultiStateView.VIEW_STATE_LOADING
                })
        compositeDisposable.add(disposable)
    }

    /**
     * 四级分类点击事件主要作用：1、还原状态；2、查看详细信息（isQuery=true);3、用于设置（isEdited=false)
     */
    fun itemOnClick(item: CategoryInfo) {
        //因为无论isEdited是真是假，对四级分类来说，点击都是进行选择操作，所以只要isQuery为假即非查询
        //状态，都进行选择操作
        if (!isQuery) {
            selectedCategory(item)
        } else {
            action.value = Pair(ACTION_QUERY, item)
        }
    }

    /**
     * 恢复原始状态
     */
    private fun restore(it: CategoryInfo) {
        it.isSelected = false
        it.isLongOnClick = false
    }

    /**
     * 长按事件,进行编辑
     */
    fun itemLongClick(item: CategoryInfo): Boolean =
            if (isQuery || !isEdited) { //如果是查询状态，或者是非编辑状态，长按事件返回false
                false
            } else {
                restore(item)
                item.isLongOnClick = true
                action.value = Pair(ACTION_SELECTED, item)
                true
            }


    /**
     * 选择该类，用于资产选择
     */
    fun selectedCategory(item: CategoryInfo) {
        //如果选择的还是当前对象，则进行取反，否则重新选择
        if (item == currentSelected) {
            item.isSelected = !currentSelected.isSelected
            currentSelected = CategoryInfo("-1", "")
        } else {
            fourthList.forEach {
                restore(it)
            }
            item.isSelected = true
            currentSelected = item
        }
        action.value = Pair(ACTION_SELECTED, item)
    }

    /**
     * 增加
     */
    fun addDialog(item: Footer) {

    }

    /**
     * 修改分类信息，图片，名称等
     */
    fun updateDialog(item: CategoryInfo) {

    }

    /**
     *  删除分类信息
     */
    fun deleteDialog(item: CategoryInfo) {
        action.value = Pair(ACTION_DELETE, item)
    }

    fun deleteData(item: CategoryInfo) {

    }
}