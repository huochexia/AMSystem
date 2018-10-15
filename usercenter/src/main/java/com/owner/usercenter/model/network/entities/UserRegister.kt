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
 *
 * Created by Liuyong on 2018-10-11.It's AMSystem
 *@description:
 */
/*
  注册请求体
 */
data class RegisterReq(val username:String,val password:String,val mobilePhoneNumber:String ,val avatar:String)
/*
  注册成功响应体
 */
data class RegisterResp(val sessionToken:String,val createAt:String,val objectId:String)


