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
import com.owner.baselibrary.common.BaseConstant
import com.owner.baselibrary.ext.execute
import com.owner.baselibrary.ext.pref
import com.owner.baselibrary.utils.AppPrefsUtils
import com.owner.baselibrary.utils.NetWorkUtils
import com.owner.baselibrary.viewmodel.BaseViewModel
import com.owner.provideslib.router.RouterPath
import com.owner.usercenter.common.UserConstant
import com.owner.usercenter.model.repository.UserRepository
import com.owner.usercenter.model.repository.impl.UserRepositoryImpl
import io.reactivex.rxkotlin.subscribeBy
import org.jetbrains.anko.startActivity


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
    var result = ObservableInt(-2)

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
                        if (it.isSuccessful) {
                            AppPrefsUtils.putString("username", it.body()?.username!!)
                            AppPrefsUtils.putString(BaseConstant.KEY_SP_TOKEN, it.body()?.sessionToken!!)
                            Toast.makeText(view.context, "success", Toast.LENGTH_SHORT).show()
                            ARouter.getInstance().build(RouterPath.App.PATH_MAIN).navigation()
                        } else {
                            println(it.errorBody()?.string())
                        }
                    }
            compositeDisposable.add(disposable)
        } else {
            result.set(UserConstant.NET_NOUSER)
        }
    }


}