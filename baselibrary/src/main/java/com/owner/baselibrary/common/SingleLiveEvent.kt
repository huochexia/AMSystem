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
package com.owner.baselibrary.common

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.support.annotation.MainThread
import com.orhanobut.logger.Logger
import java.util.concurrent.atomic.AtomicBoolean

/**
 *
 * Created by Liuyong on 2018-12-08.It's AMSystem
 *@description:
 */
class SingleLiveEvent<T> : MutableLiveData<T>() {

    private val pending = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
        if (hasActiveObservers()) {
            Logger.d("注册了多个观察者，但是发生改变时仅仅一个得到通知")
        }
        //观察内部
        super.observe(owner, Observer<T>{
            if (pending.compareAndSet(true,false)){
                observer.onChanged(it)
            }
        })
    }

    @MainThread
    override fun setValue(value: T?) {
        pending.set(true)
        super.setValue(value)
    }
    /**
     * 清空
     */
    @MainThread
    fun call() {
        value = null
    }
}