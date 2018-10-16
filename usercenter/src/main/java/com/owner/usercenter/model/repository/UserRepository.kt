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
package com.owner.usercenter.model.repository

import com.owner.baselibrary.model.network.entites.UploadImageResp
import com.owner.baselibrary.model.respository.BaseRepository
import com.owner.usercenter.model.network.entities.LoginResp
import com.owner.usercenter.model.network.entities.RegisterResp
import io.reactivex.Observable
import retrofit2.Response
import java.io.File

/**
 *
 * Created by Liuyong on 2018-10-12.It's AMSystem
 *@description:
 */
interface UserRepository : BaseRepository {
    /*
      注册
     */
    fun register(username:String,password:String,phone:String): Observable<Response<RegisterResp>>
    /*
      登录
     */
    fun login(username:String,pwd:String):Observable<Response<LoginResp>>
    /*
      上传头像
     */
    fun uploadAvatar(avatar: File):Observable<Response<UploadImageResp>>
}