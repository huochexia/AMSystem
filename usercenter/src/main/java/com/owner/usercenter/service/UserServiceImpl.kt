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

import io.reactivex.Observable
import javax.inject.Inject

/**
 *
 * Created by Liuyong on 2018-09-21.It's AMSystem
 *@description:
 */
class UserServiceImpl @Inject constructor():UserService{
    /*
       注册
     */
    override fun register(name: String, pwd: String, verifyCode: String): Observable<Boolean> {

        return Observable.just(true)
    }

}