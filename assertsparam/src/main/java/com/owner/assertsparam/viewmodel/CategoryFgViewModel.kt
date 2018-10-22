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
import android.view.View
import android.widget.Toast
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

    lateinit var selectedCg : MutableLiveData<Int>

    init {
        val top1 = CategoryInfo(1, "桌子", 0)

        val top2 = CategoryInfo(2, "办公家具", 1)

        val top3 = CategoryInfo(3, "学员公寓家俱", 2)

        topCgList.add(top1)
        topCgList.add(top2)
        topCgList.add(top3)
        selectedCg = MutableLiveData()
    }

    fun topItemOnClick(view: View, item: CategoryInfo) {
        //还原所有分类值
        topCgList.forEach {
            it.isSelected = false
        }
        selectedCg.value=3
        Toast.makeText(view.context, item.name, Toast.LENGTH_SHORT).show()
    }


}