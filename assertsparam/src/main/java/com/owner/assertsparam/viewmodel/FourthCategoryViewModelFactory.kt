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

import android.app.DownloadManager
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.owner.assertsparam.data.CategoryInfo
import java.lang.IllegalArgumentException

/**
 *
 * Created by Liuyong on 2018-11-10.It's AMSystem
 *@description:
 */
class FourthCategoryViewModelFactory(private val tableName: String, private val isEdited:Boolean,
                                     private val isQuery:Boolean,private val thirdCg:CategoryInfo)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FourthCategoryViewModel::class.java)) {
            return FourthCategoryViewModel(tableName,isEdited,isQuery,thirdCg) as T
        }
        throw IllegalArgumentException("UnKnown ViewModel class!")
    }
}