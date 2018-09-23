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
package com.owner.baselibrary.ui.activity

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.CallSuper
import com.owner.baselibrary.common.AMSystemApp
import com.owner.baselibrary.common.AppManager
import com.owner.baselibrary.injection.component.ActivityComponent
import com.owner.baselibrary.injection.component.DaggerActivityComponent
import com.owner.baselibrary.injection.module.ActivityModule
import com.owner.baselibrary.injection.module.LifecycleModule
import com.owner.baselibrary.viewmodel.BaseViewModel
import com.squareup.leakcanary.RefWatcher
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import javax.inject.Inject

/**
 * 抽象类，因为没有实现MvvmView接口的方法
 * 实现Fragment注入器接口，管理Activity出入栈
 *  提供了ViewDataBinding对象和 注入ViewModel，与ViewModel建立联系
 * Created by Liuyong on 2018-09-15.It's AMSystem
 *@description:
 */
abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel> : RxAppCompatActivity(){

    //为子类提供binding
    protected lateinit var binding: B
    //为子类提供viewModel
    @Inject
    protected lateinit var viewModel: VM
    @Inject
    protected lateinit var refWatcher: RefWatcher
    /**
     * 为子类依赖提供组件
     */
    lateinit var  activityComponent: ActivityComponent



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent =DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .appComponent((application as AMSystemApp).appComponent)
                .lifecycleModule(LifecycleModule(this))
                .build()
        initInjection()
        AppManager.instance.addActivity(this)
    }

    /**
     * 子类完成自己的依赖注入
     */
    abstract fun initInjection()


    override fun onDestroy() {
        super.onDestroy()
        AppManager.instance.removeActivity(this)
    }


}