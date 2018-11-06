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

import com.owner.baselibrary.model.network.entites.UploadImageReq
import com.owner.baselibrary.model.network.entites.UploadImageResp
import com.owner.baselibrary.model.network.service.BaseService
import com.owner.baselibrary.utils.PinyinUtils
import com.owner.usercenter.model.network.entities.*
import com.owner.usercenter.model.network.service.UserService
import com.owner.usercenter.model.repository.UserRepository
import io.reactivex.Observable
import java.io.File

/**
 *
 * Created by Liuyong on 2018-10-12.It's AMSystem
 *@description:
 */
class UserRepositoryImpl : UserRepository {


    /**
     * 注册请求
     */
    override fun register(username: String, password: String, phone: String): Observable<RegisterResp> {
        var letters = PinyinUtils.getFirstSpell(username).toUpperCase().toCharArray()[0].toString()
        // 正则表达式，判断首字母是否是英文字母
        if (!letters.matches("[A-Z]".toRegex())) {
            letters = "#"
        }

        return UserService.register(RegisterReq(username, password, phone, "", letters))
    }

    /**
     *登录请求
     */
    override fun login(username: String, pwd: String): Observable<LoginResp> {
        return UserService.login(LoginReq(username, pwd))
    }

    /**
     * 上传头像
     */
    override fun uploadAvatar(avatar: File): Observable<UploadImageResp> {
        return BaseService.uploadImage(avatar.name, UploadImageReq(avatar))
    }

    /**
     *更新头像
     */
    override fun updateAvatar(token: String, userId: String, avatar: String): Observable<UpdateAvatarResp> {
        return UserService.updateAvatar(token, userId, UpdateAvatarReq(avatar))
    }

}