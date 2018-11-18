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


     var fourthList = mutableListOf<CategoryInfo>()

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

    /**
     *  得到分类信息列表
     */
    fun getFourthCgList(item: CategoryInfo) {
        val disposable = repo.getCategory(tablename, thirdCg.objectId).execute()
                .subscribe({
                    fourthList = it.results
                }, {
                    mStateView = MultiStateView.VIEW_STATE_ERROR
                }, {
                    mStateView = MultiStateView.VIEW_STATE_CONTENT
                }, {
                    mStateView = MultiStateView.VIEW_STATE_LOADING
                })
        compositeDisposable.add(disposable)
    }

    /**
     * 点击得到详细信息
     */
    fun itemOnClick(item: CategoryInfo) {

    }

    /**
     * 长按事件,进行编辑
     */
    fun itemLongClick(item: CategoryInfo) {

    }

    /**
     * 选择该类，用于资产选择
     */
    fun selectedCategory(item: CategoryInfo) {

    }

    /**
     * 增加
     */
    fun addDialog(item:Footer) {

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

    }
}