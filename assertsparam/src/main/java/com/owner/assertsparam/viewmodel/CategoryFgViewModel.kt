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

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.databinding.Bindable
import android.view.View
import android.widget.Toast
import com.orhanobut.logger.Logger
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

    var topCgList = mutableListOf<CategoryInfo>()

    var isVisiable = MutableLiveData<Boolean>()

    init {
        val top1 = CategoryInfo(1, "桌子", 0)

        val top2 = CategoryInfo(2, "办公家具", 1)

        val top3 = CategoryInfo(3, "学员公寓家俱", 2)

        topCgList.add(top1)
        topCgList.add(top2)
        topCgList.add(top3)
        isVisiable.value = false
    }

    var selectedCg = MutableLiveData<CategoryInfo>()
    fun topItemOnClick(item: CategoryInfo) {
        //取消所有选择
        topCgList.forEach {
            it.isSelected = false
        }
        //将当前项目设定为选择状态
        item.isSelected = true
        selectedCg.value = item

    }

}