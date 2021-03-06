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
package com.owner.usercenter.model.network.entities

/**
 * 用户登录时网络请求对象和响应对象
 * Created by Liuyong on 2018-10-12.It's AMSystem
 *@description:
 */
/*
   请求对象
 */
data class LoginReq(val username: String, val password: String)

/*
  网络响应对象
 */
data class LoginResp(val code :Int = 0,
                     val error:String?,
                     val sessionToken: String?,
                     val username: String?,
                     val mobilePhoneNumber: String?,
                     val objectId: String?,
                     val avatar:String?,
                     val emailVerified: Boolean,
                     val mobilePhoneVerified: Boolean){

    fun isSuccess() :Boolean = code == 0 && sessionToken.isNullOrEmpty().not()
}

