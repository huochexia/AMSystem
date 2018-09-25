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
package com.owner.usercenter.data.api

import com.avos.avoscloud.AVUser
import com.owner.baselibrary.common.BaseConstant
import com.owner.baselibrary.data.protocol.BaseResp
import com.owner.usercenter.data.protocol.LoginReq
import com.owner.usercenter.data.protocol.RegisterReq
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 *  访问用户Api
 * Created by Liuyong on 2018-09-24.It's AMSystem
 *@description:
 */
interface UserApi {

    /**
     * "@Body"注解的目的是将RegisterReq对象转换成Json字符串
     */

    @POST("1.1/users")
    fun registerApi(@Body req: RegisterReq): Observable<BaseResp<String>>
    /**
     * 登录
     */
    @POST("1.1/login")
    fun loginApi(@Body req: LoginReq) : Observable<BaseResp<AVUser>>
}