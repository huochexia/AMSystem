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
package com.owner.baselibrary.view.activity

import android.databinding.ViewDataBinding
import android.os.Bundle
import com.owner.baselibrary.common.AppManager
import com.owner.baselibrary.viewmodel.BaseViewModel
import com.squareup.leakcanary.RefWatcher
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

/**
 * 基础类，继承RxAppCompatActivity,管理Rxjava，使用管理Activity出入栈
 *  提供了ViewDataBinding对象和 注入ViewModel，与ViewModel建立联系
 * Created by Liuyong on 2018-09-15.It's AMSystem
 *@description:
 */
open class BaseActivity<B : ViewDataBinding, VM : BaseViewModel<*>> : RxAppCompatActivity() {

    //为子类提供binding
    protected lateinit var binding: B
    //    为子类提供viewModel
    lateinit var viewModel: VM

    protected lateinit var refWatcher: RefWatcher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppManager.instance.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.instance.removeActivity(this)
        viewModel.compositeDisposable.clear()
    }


}