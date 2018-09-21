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

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.databinding.Observable
import android.databinding.PropertyChangeRegistry
import android.os.Bundle
import android.support.annotation.CallSuper
import com.owner.baselibrary.injection.qualifier.ActivityContext
import com.owner.baselibrary.injection.qualifier.AppContext
import com.owner.baselibrary.utils.NetWorkUtils
import com.owner.baselibrary.viewmodel.view.BaseView
import com.owner.baselibrary.viewmodel.view.MvvmView
import com.trello.rxlifecycle2.LifecycleProvider
import javax.inject.Inject

/**
 *VeiwModel基类，为子类提供BaseView对象，LifecycleProvider对象，Activity级的context
 * 继承ViewModel对象，实现DataBinding的Observable接口
 * Created by Liuyong on 2018-09-16.It's AMSystem
 *@description:
 */
abstract class BaseViewModel<T : MvvmView> : ViewModel(), MvvmViewModel<T> {

    /*
      为子类提供View对象
     */
    var view: T? = null
        private set
    /**
     *满足所有ViewModel对LifecycelProvider的使用
     */
    @Inject
    lateinit var lifecycleProvider: LifecycleProvider<*>


    @CallSuper//表示任何覆盖的方法都应该调用这个方法。
    override fun attachView(view: T, savedInstanceState: Bundle?) {
        this.view = view
        if (savedInstanceState != null) {
            restoreInstanceState(savedInstanceState)
        }
    }

    @CallSuper
    override fun detachView() {
        view = null
    }

    /**
     *开放这个方法由具体业务ViewModel来实现
     */
    protected open fun restoreInstanceState(savedInstanceState: Bundle?) {}

    override fun saveInstanceState(outState: Bundle) {}




    /**
     * 实现Observable接口部分
     */
    private val callbacks = PropertyChangeRegistry()

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.remove(callback)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }

    /*
       通知全部
     */
    fun notifyAllChanged() {
        callbacks.notifyCallbacks(this, 0, null)
    }

    /*
       通知某个属性
     */
    fun notifyPropertyChanged(fieldId: Int) {
        callbacks.notifyCallbacks(this, fieldId, null)
    }

}