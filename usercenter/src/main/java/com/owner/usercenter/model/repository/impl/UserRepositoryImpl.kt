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
package com.owner.usercenter.model.repository.impl

import com.owner.usercenter.model.network.entities.LoginReq
import com.owner.usercenter.model.network.entities.LoginResp
import com.owner.usercenter.model.network.entities.RegisterReq
import com.owner.usercenter.model.network.entities.RegisterResp
import com.owner.usercenter.model.network.service.UserService
import com.owner.usercenter.model.repository.UserRepository
import io.reactivex.Observable
import retrofit2.Response

/**
 *
 * Created by Liuyong on 2018-10-12.It's AMSystem
 *@description:
 */
class UserRepositoryImpl:UserRepository {

    /**
     * 注册请求
     */
    override fun register(username: String, password: String, phone: String): Observable<Response<RegisterResp>> {
        return UserService.register(RegisterReq(username,password,phone,""))
    }

    override fun login(username: String, pwd: String): Observable<Response<LoginResp>> {
        return UserService.login(LoginReq(username,pwd))
    }
}