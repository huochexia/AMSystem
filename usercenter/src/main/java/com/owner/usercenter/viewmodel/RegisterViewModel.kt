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
import com.owner.baselibrary.viewmodel.BaseViewModel
import com.owner.usercenter.model.network.entities.RegisterResp
import com.owner.usercenter.model.repository.UserRepository
import com.owner.usercenter.model.repository.impl.UserRepositoryImpl
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

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
    val userId = MutableLiveData<String>()

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
     * 比较两次输入密码是否一致
     */
    fun comparePwd(): Boolean = pwd == pwdAgain
    /**
     * 注册按钮，使用Api方式
     */
    fun register(): Single<RegisterResp> {

        return repo.register(userName, pwd, mobile)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess {
                    if (it.isSuccess()) {
                        userId.value = it.objectId
                    }
                }

    }

}