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
package com.owner.assetsparam.viewmodel

import com.owner.assetsparam.model.repository.AssetInfoRepository
import com.owner.assetsparam.model.repository.impl.AssetInfoRepositoryImpl
import com.owner.baselibrary.viewmodel.BaseViewModel

/**
 *  资产信息ViewModel
 * Created by Liuyong on 2018-11-28.It's AMSystem
 *@description:
 */
class AssetInfoViewModel(val isEdited:Boolean) :BaseViewModel<AssetInfoRepository>(){

    init {
        repo = AssetInfoRepositoryImpl()
    }


}