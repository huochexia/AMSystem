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

import android.databinding.ObservableInt
import android.view.View
import com.owner.baselibrary.viewmodel.BaseViewModel
import com.avos.avoscloud.AVException
import com.avos.avoscloud.UpdatePasswordCallback
import com.avos.avoscloud.AVUser
import com.owner.usercenter.common.UserConstant
import com.owner.usercenter.model.repository.UserRepository


/**
 *
 * Created by Liuyong on 2018-09-12.It's MVVMKotlinMall
 *@description:
 */
class ResetPwdViewModel: BaseViewModel<UserRepository>() {

    lateinit var verifyCode:String
    var result = ObservableInt(UserConstant.RESULT_INIT_VALUE)
    var pwd = ""
    var pwdAgain = ""

    fun getPwd(s: CharSequence, s1: Int, o: Int, k: Int) {
        pwd = s.toString()
    }

    fun getPwdAgain(s: CharSequence, s1: Int, o: Int, k: Int) {
        pwdAgain = s.toString()
    }

    /*
    下一步按钮点击事件
     */
    fun onReset(view: View) {

        if (pwd == pwdAgain) {
            AVUser.resetPasswordBySmsCodeInBackground(verifyCode, pwd, object : UpdatePasswordCallback() {
                override fun done(e: AVException?) {
                    if (e == null) {
                        result.set(UserConstant.ACTION_SUCCESS)
                    } else {
                        result.set(e.code)
                        e.printStackTrace()
                        println(e.code.toString())
                    }
                }
            })
        }else{
           result.set(UserConstant.TWO_PASSWORD_NO_SAME)
        }
    }


}