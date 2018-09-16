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

import android.app.Application
import android.content.Context
import com.owner.baselibrary.injection.component.AppComponent
import com.owner.baselibrary.injection.component.DaggerAppComponent
import com.owner.baselibrary.injection.module.ApiModule
import com.owner.baselibrary.injection.module.AppModule
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import io.reactivex.plugins.RxJavaPlugins
import timber.log.Timber
import javax.inject.Inject


/**
 * 基本应用程序: 单例模式
 * 初始化工作：RefWatcher,Timber
 * Created by Liuyong on 2018-09-15.It's AMSystem
 *@description:
 */
class AMSystemApp : Application() {
    /*
       定义内存泄露管理工具
     */
    @Inject
    lateinit var refWatcher: RefWatcher


    companion object {
        lateinit var instance: AMSystemApp
            private set

        lateinit var appComponent: AppComponent
        /*
            静态方法，获取RefWatcher
         */
        fun getRefWatcher(context: Context): RefWatcher {
            return instance.refWatcher
        }
    }

    override fun onCreate() {
        super.onCreate()
        /*
      初始化Application的依赖注入
     */
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
        instance = this

        //初始化Timber日志管理工具
        Timber.plant(Timber.DebugTree())
        RxJavaPlugins.setErrorHandler { Timber.e(it) }
    }


}