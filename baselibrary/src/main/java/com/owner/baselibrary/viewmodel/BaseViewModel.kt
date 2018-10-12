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
import android.databinding.PropertyChangeRegistry
import com.owner.baselibrary.model.respository.BaseRepository
import io.reactivex.disposables.CompositeDisposable

/**
 * VeiwModel基类继承ViewModel对象，实现DataBinding的Observable接口
 * 1、ViewModel不能持有View对象，因为ViewModel的生命周期大于View 。ViewModel与View之间的数据传递依靠
 * LiveData对象或者databinding。这两者均为可观察者，它们的变化会直接反应到UI上。使用双向绑定后，
 * UI的变化也会更新ViewModel中的数据
 * 2、持有BaseService对象。ViewModel通过Service向Repository获取数据流，Repository向网络服务器端请求
 * 数据。
 * 数据流：UI--->[ViewModel]--->[Repository] --->[Service]--->[Repository] --->[ViewModel] --->UI
 * Created by Liuyong on 2018-09-16.It's AMSystem
 *@description:
 */
abstract class BaseViewModel<R:BaseRepository>: ViewModel(), Observable {

    /**
     * 管理Rxjava的Observable对象
     */
    val compositeDisposable = CompositeDisposable()
    /**
     * 数据仓库
     */
    lateinit var repo:R
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