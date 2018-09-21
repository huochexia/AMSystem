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
package com.owner.baselibrary.viewmodel

import android.arch.lifecycle.ViewModel
import android.databinding.Observable
import android.os.Bundle
import com.owner.baselibrary.viewmodel.view.MvvmView

/**
 * 基于MVVM架构的最基本ViewModel接口
 * 1、ViewModel的一个原则是永远不持有View和非Application的Context，防止内存泄露发生。
 * 这里采用的从外界注入的方式，通过方法绑定和解除与View建立联系
 * 2、继承databinding 的Observable
 * 3、定义方法处理系统状态发生变化时的处理，如转屏
 * Created by Liuyong on 2018-09-16.It's AMSystem
 *@description:
 */
interface MvvmViewModel<V : MvvmView> : Observable {
    /*
        与View建立联系
     */
    fun attachView(view: V, savedInstanceState: Bundle?)
    /*
        取消与View之间的联系
     */
    fun detachView()
    /*
        保存系统状态值
     */
    fun saveInstanceState(outState: Bundle)
}