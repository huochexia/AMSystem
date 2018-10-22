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

import android.view.View
import android.widget.Toast
import com.owner.assertsparam.data.CategoryInfo
import com.owner.assertsparam.model.repository.AssertsParamRepository
import com.owner.baselibrary.viewmodel.BaseViewModel

/**
 * 一级分类ViewModel
 * Created by Liuyong on 2018-10-20.It's AMSystem
 *@description:
 */
class TopCgItemViewModel(var info: CategoryInfo) : BaseViewModel<AssertsParamRepository>() {

    fun onClick(view: View) {
        view.isSelected = true
        Toast.makeText(view.context, info.name, Toast.LENGTH_SHORT).show()
    }

    fun onLongClick(view: View): Boolean {
        Toast.makeText(view.context, "长按" + info.name, Toast.LENGTH_SHORT).show()
        return true
    }
}