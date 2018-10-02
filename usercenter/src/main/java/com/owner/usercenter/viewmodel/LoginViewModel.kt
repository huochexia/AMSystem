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

import android.databinding.Bindable
import android.databinding.ObservableInt
import android.view.View
import com.avos.avoscloud.AVException
import com.avos.avoscloud.AVUser
import com.avos.avoscloud.LogInCallback
import com.owner.baselibrary.BR
import com.owner.baselibrary.common.AMSystemApp
import com.owner.baselibrary.common.Setting
import com.owner.baselibrary.ext.pref
import com.owner.baselibrary.utils.NetWorkUtils
import com.owner.baselibrary.viewmodel.BaseViewModel
import com.owner.usercenter.common.UserConstant
import com.owner.usercenter.data.UserRepository
import com.owner.usercenter.service.impl.UserServiceImpl


/**
 *
 * Created by Liuyong on 2018-09-18.It's AMSystem
 *@description:
 */
class LoginViewModel : BaseViewModel<UserServiceImpl>() {

    //登录结果，通过它驱动视图变化
    var result = ObservableInt(-2)

    private var mobile: String by pref("")

    private var pwd: String = ""

    /**
     * 从视图绑定中获取输入内容
     */
    fun getMobile(s: CharSequence, s1: Int, o: Int, k: Int) {
        mobile = s.toString()
    }

    fun getPwd(s: CharSequence, s1: Int, o: Int, k: Int) {
        pwd = s.toString()
    }


    /**
     * 注册按钮
     */
    fun login(view: View) {
        if (NetWorkUtils.isNetWorkAvailable(AMSystemApp.instance)) {
            AVUser.logInInBackground(mobile, pwd, object : LogInCallback<AVUser>() {
                override fun done(avUser: AVUser?, e: AVException?) {
                    if (e != null) {
                        result.set(e.code)
                        }else{
                        //将成功用户保存本地
                        Setting.lastSignUpUser = mobile
                    }

                }
            })
        } else {
            result.set(UserConstant.NET_NOUSER)
        }
    }


}