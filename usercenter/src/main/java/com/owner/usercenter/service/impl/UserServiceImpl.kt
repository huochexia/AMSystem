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
package com.owner.usercenter.service.impl

import com.avos.avoscloud.AVUser
import com.owner.usercenter.data.UserRepository
import com.owner.usercenter.service.UserService
import io.reactivex.Observable

/**
 *
 * Created by Liuyong on 2018-09-21.It's AMSystem
 *@description:
 */
class UserServiceImpl : UserService {


    private val userRepository = UserRepository()
    /*
       注册
     */
    override fun register(name: String, pwd: String): Observable<String> {
        return userRepository.register(name, pwd)
                .flatMap {
                    if (it.status != 200) {

                    }
                    Observable.just(it.data)
                }
    }

    override fun login(name: String, pwd: String):Observable<AVUser> {
        return userRepository.login(name, pwd).flatMap {
            Observable.just(it.data)
        }
    }

}