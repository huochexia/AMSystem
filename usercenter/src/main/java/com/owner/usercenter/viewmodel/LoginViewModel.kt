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
import android.widget.Toast
import com.alibaba.android.arouter.launcher.ARouter
import com.owner.baselibrary.common.AMSystemApp
import com.owner.baselibrary.ext.execute
import com.owner.baselibrary.utils.NetWorkUtils
import com.owner.baselibrary.viewmodel.BaseViewModel
import com.owner.provideslib.exception.ExceptionMsg
import com.owner.provideslib.router.RouterPath
import com.owner.usercenter.common.UserConstant
import com.owner.usercenter.model.network.entities.LoginResp
import com.owner.usercenter.model.repository.UserRepository
import com.owner.usercenter.model.repository.impl.UserRepositoryImpl
import com.owner.usercenter.utils.UserUtils
import io.reactivex.rxkotlin.subscribeBy
import org.json.JSONObject
import retrofit2.Response


/**
 *
 * Created by Liuyong on 2018-09-18.It's AMSystem
 *@description:
 */
class LoginViewModel : BaseViewModel<UserRepository>() {

    init {
        repo = UserRepositoryImpl()
    }

    //登录结果，通过它驱动视图变化
    var result = ObservableInt(UserConstant.RESULT_INIT_VALUE)

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
    fun login(view: View) {
        if (NetWorkUtils.isNetWorkAvailable(AMSystemApp.instance)) {
            val disposable = repo.login(mUserName, mPwd)
                    .execute()
                    .subscribeBy {
                        getResult(it, view)
                    }
            compositeDisposable.add(disposable)
        } else {
            result.set(UserConstant.NET_NO)
        }
    }

    /**
     * 得到网络响应结果
     */
    private fun getResult(it: Response<LoginResp>, view: View) {
        if (it.isSuccessful) {
            loginSuccess(it)
        } else {
            val error = it.errorBody()?.string()
            val json = JSONObject(error)
            println("error:"+json.getInt("code"))
            result.set(json.getInt("code"))

        }
    }

    /**
     * 登录成功
     */
    private fun loginSuccess(resp: Response<LoginResp>) {
        val userInfo = UserUtils.respToUserInfo(resp.body()!!)
        UserUtils.putUserInfo(userInfo)
        ARouter.getInstance().build(RouterPath.App.PATH_MAIN).navigation()

    }


}