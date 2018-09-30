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

import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.view.View
import android.widget.Toast
import com.avos.avoscloud.AVException
import com.avos.avoscloud.AVUser
import com.avos.avoscloud.SignUpCallback
import com.owner.baselibrary.common.AMSystemApp
import com.owner.baselibrary.utils.NetWorkUtils
import com.owner.baselibrary.viewmodel.BaseViewModel
import com.owner.baselibrary.widgets.VerifyButton
import com.owner.usercenter.common.UserConstant
import com.owner.usercenter.data.UserRepository
import com.owner.usercenter.service.UserService
import com.owner.usercenter.service.impl.UserServiceImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import rx.Scheduler
import timber.log.Timber

/**
 *
 * Created by Liuyong on 2018-09-18.It's AMSystem
 *@description:
 */
class RegisterViewModel : BaseViewModel<UserRepository>() {

    private var mobile: String = ""
    private var verifyCode = ""
    private var pwd: String = ""
    private var pwdAgain: String = ""
    var result = ObservableInt(-1)
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

        if (NetWorkUtils.isNetWorkAvailable(AMSystemApp.instance)) {
            if (pwd == pwdAgain) {
                val user = AVUser()
                user.username = mobile
                user.setPassword(pwd)
                user.signUpInBackground(object : SignUpCallback() {
                    override fun done(e: AVException?) {
                        if (e == null) {
                            result.set(UserConstant.REGISTER_SUCCESS)
                        } else {
                            result.set(e.code)
                        }
                    }

                })
            } else {
                result.set(UserConstant.TWO_PASSWORD_NO_SAME)
            }
        } else {
            result.set(UserConstant.NET_NOUSER)
        }
    }

}