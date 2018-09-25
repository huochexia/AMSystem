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
package com.owner.usercenter.data

import android.content.Context
import android.databinding.ObservableInt
import android.widget.Toast
import com.avos.avoscloud.*
import com.owner.baselibrary.common.AMSystemApp
import com.owner.baselibrary.data.respository.BaseRepository
import io.reactivex.Observable
import com.avos.avoscloud.AVException
import com.avos.avoscloud.AVUser
import com.avos.avoscloud.LogInCallback
import com.owner.baselibrary.data.net.RetrofitFactory
import com.owner.baselibrary.data.protocol.BaseResp
import com.owner.usercenter.data.api.UserApi
import com.owner.usercenter.data.protocol.LoginReq
import com.owner.usercenter.data.protocol.RegisterReq


/**
 *
 * Created by Liuyong on 2018-09-22.It's AMSystem
 *@description:
 */
class UserRepository : BaseRepository {
    /*
       注册
     */

    fun register(name: String, pwd: String) :Observable<BaseResp<String>> {

        return RetrofitFactory.instance.create(UserApi::class.java)
                .registerApi(RegisterReq(name,pwd))
    }

    /*
       登录
     */
    fun login(name: String, pwd: String) :Observable<BaseResp<AVUser>> {
       return RetrofitFactory.instance.create(UserApi::class.java)
               .loginApi(LoginReq(name,pwd))
    }
}