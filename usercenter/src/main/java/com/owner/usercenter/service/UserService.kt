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
package com.owner.usercenter.service

import android.content.Context
import android.databinding.ObservableInt
import com.avos.avoscloud.AVUser
import com.owner.baselibrary.service.BaseService
import io.reactivex.Observable
import retrofit2.Response

/**
 * 这个接口属于中间件结构，Repository是直接从云服务器端获取数据类，它获得的数据可能是打包的，
 * 如BaseResp对象。然后利用这个接口获取的对象进行解包，得到ViewModel所需要的数据类型。
 * 如果从云端得到数据没有进行包装，可以被ViewModel直接使用，则可以省略这个接口。
 * 定义一些方法，主要目的是把从服务器上获取的Observable所包含的数
 * Created by Liuyong on 2018-09-21.It's AMSystem
 *@description:
 */
interface UserService:BaseService {

     fun register(name:String,pwd:String,phone:String):Observable<Boolean>


}