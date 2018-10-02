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
import com.avos.avoscloud.AVException
import com.avos.avoscloud.AVUser
import com.avos.avoscloud.RequestMobileCodeCallback
import com.owner.baselibrary.data.respository.BaseRepository
import com.owner.baselibrary.viewmodel.BaseViewModel
import com.owner.baselibrary.widgets.VerifyButton
import com.owner.usercenter.service.impl.UserServiceImpl
import com.owner.usercenter.ui.activity.ResetPwdActivity
import org.jetbrains.anko.startActivity


/**
 *
 * Created by Liuyong on 2018-09-12.It's MVVMKotlinMall
 *@description:
 */
class ForgetPwdViewModel: BaseViewModel<UserServiceImpl>() {

    var mobile = ""
    var verifyCode = ""

    fun getMobile(s: CharSequence, s1: Int, o: Int, k: Int) {
        mobile = s.toString()
    }

    fun getVerifyCode(s: CharSequence, s1: Int, o: Int, k: Int) {
        verifyCode = s.toString()
    }

    /*
    下一步按钮点击事件
     */
    fun onNext(view: View) {
        view.context.startActivity<ResetPwdActivity>(Pair("verifyCode",verifyCode))
    }

    /*
      获取验证码
     */
    fun acceptVeriftyCode(view: View) {
        (view as VerifyButton).requestSendVerifyNumber()
        AVUser.requestPasswordResetBySmsCodeInBackground(mobile, object : RequestMobileCodeCallback() {
            override fun done(e: AVException?) {
                if (e == null) {

                } else {
                    e.printStackTrace()
                }
            }
        })
    }
}