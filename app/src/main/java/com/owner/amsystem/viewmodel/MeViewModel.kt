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
package com.owner.amsystem.viewmodel

import android.view.View
import com.owner.amsystem.model.repository.MainRepository
import com.owner.amsystem.view.activity.SettingActivity
import com.owner.baselibrary.viewmodel.BaseViewModel
import com.owner.provideslib.common.isLogined
import com.owner.usercenter.view.activity.LoginActivity
import org.jetbrains.anko.startActivity

/**
 *
 * Created by Liuyong on 2018-10-10.It's AMSystem
 *@description:
 */
class MeViewModel:BaseViewModel<MainRepository>(){
    /**
     * 启动个人设置
     */
    fun startSettingActivity(view: View) {

        val context = view.context
        context.startActivity<SettingActivity>()
    }

    /**
     * 启动登录界面
     */
    fun startUserInfoOrLogin(view: View) {
        if (isLogined()) {
            //如果是登录状态，进入用户信息界面
            println("User is login!")
        } else {
            //如果没有登录，则进入登录界面
            view.context.startActivity<LoginActivity>()
        }

    }
}