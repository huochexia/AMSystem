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
package com.owner.usercenter.utils

import com.owner.baselibrary.common.BaseConstant
import com.owner.baselibrary.utils.AppPrefsUtils
import com.owner.provideslib.common.ProviderConstant
import com.owner.usercenter.data.UserInfo
import com.owner.usercenter.model.network.entities.LoginResp

/**
 *
 * Created by Liuyong on 2018-10-14.It's AMSystem
 *@description:
 */
object UserUtils {
    /*
       登录成功后保存用户信息在本地。退出登录时，传入null，清空存储.最主要的清空Token
     */
    fun putUserInfo(userInfo: UserInfo?) {
        AppPrefsUtils.putString(BaseConstant.KEY_SP_TOKEN, userInfo?.token ?: "")
        AppPrefsUtils.putString(ProviderConstant.KEY_SP_USER_NAME, userInfo?.userName ?: "")
        AppPrefsUtils.putString(ProviderConstant.KEY_SP_USER_MOBILE, userInfo?.userMobile ?: "")

    }

    /*
       登录响应转换为用户信息
     */
    fun respToUserInfo(repo: LoginResp): UserInfo {
        return UserInfo(repo.sessionToken,repo.username,repo.mobilePhoneNumber)
    }
}