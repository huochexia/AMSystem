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
import android.databinding.ObservableBoolean
import android.databinding.ObservableInt
import android.view.View
import android.widget.Toast
import com.alibaba.android.arouter.launcher.ARouter
import com.owner.baselibrary.common.BaseConstant
import com.owner.baselibrary.ext.execute
import com.owner.baselibrary.ext.pref
import com.owner.baselibrary.utils.AppPrefsUtils
import com.owner.baselibrary.utils.NetWorkUtils
import com.owner.baselibrary.viewmodel.BaseViewModel
import com.owner.provideslib.exception.ExceptionMsg
import com.owner.provideslib.router.RouterPath
import com.owner.usercenter.common.UserConstant
import com.owner.usercenter.model.network.entities.RegisterReq
import com.owner.usercenter.model.network.entities.RegisterResp
import com.owner.usercenter.model.network.service.UserService
import com.owner.usercenter.model.repository.UserRepository
import com.owner.usercenter.model.repository.impl.UserRepositoryImpl
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import org.json.JSONObject
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

    //通过修改这个可观察变量的值，驱动视图显示相应的提示内容
    val error = MutableLiveData<String>()

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
                            getResult(it)
                        }
                compositeDisposable.add(disposable)
            } else {
                error.value = ExceptionMsg.getError(UserConstant.TWO_PASSWORD_NO_SAME)
            }
        } else {
            error.value = ExceptionMsg.getError(UserConstant.NET_NO)
        }

    }

    /**
     * 得到结果
     */
    private fun getResult(it: RegisterResp) {
        if (it.isSuccess()) {
            error.value= ExceptionMsg.getError(UserConstant.ACTION_SUCCESS)
        } else {
            error.value = ExceptionMsg.getError(it.code)
        }

    }

}