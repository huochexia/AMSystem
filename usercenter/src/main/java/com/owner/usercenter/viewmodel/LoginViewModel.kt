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

import android.arch.lifecycle.MutableLiveData
import com.alibaba.android.arouter.launcher.ARouter
import com.owner.baselibrary.viewmodel.BaseViewModel
import com.owner.provideslib.router.RouterPath
import com.owner.usercenter.model.network.entities.LoginResp
import com.owner.usercenter.model.repository.UserRepository
import com.owner.usercenter.model.repository.impl.UserRepositoryImpl
import com.owner.usercenter.utils.UserUtils
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 *
 * Created by Liuyong on 2018-09-18.It's AMSystem
 *@description:
 */
class LoginViewModel : BaseViewModel<UserRepository>() {


    init {
        repo = UserRepositoryImpl()

    }
    private var mUserName: String = ""

    private var mPwd: String = ""

    /**
     * 从视图绑定中获取输入内容
     */
    fun getUserName(s: CharSequence, s1: Int, o: Int, k: Int) {
        mUserName = s.toString()
    }

    fun getPwd(s: CharSequence, s1: Int, o: Int, k: Int) {
        mPwd = s.toString()
    }


    /**
     * 登录事件,暂时使用手机号做为用户名，正式版可以通过手机号获取验证码进行登录
     */
    fun login(): Single<LoginResp> {

        return repo.login(mUserName, mPwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess {
                    if (it.isSuccess())
                        loginSuccess(it)
                    }

    }

    /**
     * 登录成功
     */
    private fun loginSuccess(resp: LoginResp) {
        val userInfo = UserUtils.respToUserInfo(resp)
        UserUtils.putUserInfo(userInfo)
        ARouter.getInstance().build(RouterPath.App.PATH_MAIN).navigation()

    }


}