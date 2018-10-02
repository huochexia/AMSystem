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

import com.owner.baselibrary.data.net.RetrofitFactory
import com.owner.baselibrary.data.protocol.BaseResp
import com.owner.baselibrary.data.respository.BaseRepository
import com.owner.usercenter.data.api.UserApiService
import com.owner.usercenter.data.protocol.RegisterReq
import io.reactivex.Observable
import retrofit2.Response


/**
 *  通过Retrofit从服务器端获得数据
 * Created by Liuyong on 2018-09-22.It's AMSystem
 *@description:
 */
class UserRepository : BaseRepository {
    /**
     * 注册
     */
    fun register(username: String, pwd: String, phone: String):Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(UserApiService::class.java)
                .registerApi(RegisterReq(username,pwd,phone))
    }
}