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
package com.owner.usercenter.model.network.service

import com.owner.baselibrary.model.network.RetrofitFactory
import com.owner.usercenter.model.network.entities.*
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

/**
 * 这个文件包含与用户操作有关的API和一个用户操作的单例
 * Created by Liuyong on 2018-09-21.It's AMSystem
 *@description:
 */
interface UserApi {
    /*
      注册
     */
    @POST("1.1/users")
    fun register(@Body req: RegisterReq): Single<RegisterResp>
    /*
      登录
     */
    @POST("1.1/login")
    fun login(@Body req:LoginReq):Single<LoginResp>
    /*
      修改头像
     */
    @PUT("1.1/users/{id}")
    fun updateAvatar(@Header("X-LC-Session") token:String,@Path("id") userId:String,
                     @Body req:UpdateAvatarReq):Single<UpdateAvatarResp>
}

/*
 * 代理接口方式，实现单例
 */
object UserService: UserApi by RetrofitFactory.instance.create(UserApi::class.java)

