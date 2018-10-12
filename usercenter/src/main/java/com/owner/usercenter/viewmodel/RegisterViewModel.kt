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
import com.owner.baselibrary.common.BaseConstant
import com.owner.baselibrary.ext.execute
import com.owner.baselibrary.ext.pref
import com.owner.baselibrary.utils.AppPrefsUtils
import com.owner.baselibrary.utils.NetWorkUtils
import com.owner.baselibrary.viewmodel.BaseViewModel
import com.owner.usercenter.common.UserConstant
import com.owner.usercenter.model.network.entities.RegisterReq
import com.owner.usercenter.model.network.entities.RegisterResp
import com.owner.usercenter.model.network.service.UserService
import com.owner.usercenter.model.repository.UserRepository
import com.owner.usercenter.model.repository.impl.UserRepositoryImpl
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import retrofit2.Response

/**
 *
 * Created by Liuyong on 2018-09-18.It's AMSystem
 *@description:
 */
class RegisterViewModel : BaseViewModel<UserRepository>() {

    private var mobile: String = ""
    private var userName = ""
    private var pwd: String = ""
    private var pwdAgain: String = ""
    var result = ObservableInt(-1)
    init {
        repo = UserRepositoryImpl()
    }
    /**
     * 从视图绑定中获取输入内容
     */
    fun getMobile(s: CharSequence, s1: Int, o: Int, k: Int) {
        mobile = s.toString()
    }

    fun getUserName(s: CharSequence, s1: Int, o: Int, k: Int) {
        userName = s.toString()
    }

    fun getPwd(s: CharSequence, s1: Int, o: Int, k: Int) {
        pwd = s.toString()
    }

    fun getPwdAgain(s: CharSequence, s1: Int, o: Int, k: Int) {
        pwdAgain = s.toString()
    }

    /**
     * 注册按钮，使用Api方式
     */
    fun register(view: View) {
        if (NetWorkUtils.isNetWorkAvailable(view.context)) {
            if (pwd == pwdAgain) {
                val disposable = repo.register(userName, pwd, mobile)
                        .execute()
                        .subscribeBy {
                            if (it.isSuccessful) {
                                val token = it.body()?.sessionToken
                                AppPrefsUtils.putString(BaseConstant.KEY_SP_TOKEN,token!!)

                            } else {
                                println("error:"+it.errorBody()?.string())
                            }
                        }
                compositeDisposable.add(disposable)
            } else {
                result.set(UserConstant.TWO_PASSWORD_NO_SAME)
            }
        } else {
            result.set(UserConstant.NET_NOUSER)
        }


    }

}