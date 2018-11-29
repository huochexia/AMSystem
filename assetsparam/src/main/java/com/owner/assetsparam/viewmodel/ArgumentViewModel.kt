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

import android.arch.lifecycle.MutableLiveData
import com.owner.baselibrary.model.respository.BaseRepository
import com.owner.baselibrary.viewmodel.BaseViewModel

/**
 *
 * Created by Liuyong on 2018-10-21.It's AMSystem
 *@description:
 */
class ArgumentViewModel :BaseViewModel<BaseRepository>(){
    /*
      已经选择了的参数，通过Map的key，确保同一类参数的唯一性
     */
    var selectedArgumentMap = MutableLiveData<Pair<String,Any>>()
}