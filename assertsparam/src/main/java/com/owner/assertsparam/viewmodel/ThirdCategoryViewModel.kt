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

import android.databinding.ViewDataBinding
import android.view.View
import android.widget.ImageView
import com.owner.assertsparam.model.repository.AssertsParamRepository
import com.owner.assertsparam.model.repository.impl.AssertsParamRepositoryImpl
import com.owner.baselibrary.viewmodel.BaseViewModel

/**
 *
 * Created by Liuyong on 2018-10-20.It's AMSystem
 *@description:
 */
class ThirdCategoryViewModel : BaseViewModel<AssertsParamRepository>(){


    lateinit var imgUrl:String
    lateinit var thirdCgName :String

    /*
      绑定事件
     */
    fun onClick(view: View) {

    }
}