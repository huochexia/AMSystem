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
package com.owner.usercenter.viewmodel

import android.view.View
import com.owner.baselibrary.utils.NetWorkUtils
import com.owner.baselibrary.viewmodel.BaseViewModel
import com.owner.baselibrary.viewmodel.MvvmViewModel
import com.owner.baselibrary.widgets.VerifyButton
import com.owner.usercenter.service.UserService
import com.owner.usercenter.viewmodel.view.RegisterView
import javax.inject.Inject

/**
 *
 * Created by Liuyong on 2018-09-18.It's AMSystem
 *@description:
 */
class RegisterViewModel @Inject constructor() : BaseViewModel<RegisterView>() {
    @Inject
    lateinit var userServiceImpl: UserService

    var mobile: String = ""
    var verifyCode = ""
    var pwd: String = ""
    var pwdAgain: String = ""

    /**
     * 从视图绑定中获取输入内容
     */
    fun getMobile(s: CharSequence, s1: Int, o: Int, k: Int) {
        mobile = s.toString()
    }

    fun getVerifyCode(s: CharSequence, s1: Int, o: Int, k: Int) {
        verifyCode = s.toString()
    }

    fun getPwd(s: CharSequence, s1: Int, o: Int, k: Int) {
        pwd = s.toString()
    }

    fun getPwdAgain(s: CharSequence, s1: Int, o: Int, k: Int) {
        pwdAgain = s.toString()
    }

    /**
     * 获取验证码
     */
    fun acceptVerifyCode(view: View) {
        (view as VerifyButton).requestSendVerifyNumber()
    }

    /**
     * 注册按钮
     */
    fun register(view: View) {
        if (NetWorkUtils.isNetWorkAvailable(view.context)) {

        }
    }

}