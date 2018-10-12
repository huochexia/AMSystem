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
import android.content.ContextWrapper
import android.support.multidex.MultiDexApplication
import com.alibaba.android.arouter.launcher.ARouter
import com.avos.avoscloud.AVOSCloud
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import io.reactivex.plugins.RxJavaPlugins
import timber.log.Timber


/**
 * 基本应用程序: 单例模式.因为编辑是方法会超过65535，所以这里需要继承MultiDexApplication
 * 初始化工作：RefWatcher,Timber
 * Created by Liuyong on 2018-09-15.It's AMSystem
 *@description:
 */
class AMSystemApp : MultiDexApplication() {
    /*
       定义内存泄露管理工具
     */
    lateinit var refWatcher: RefWatcher
    companion object {
        lateinit var instance: AMSystemApp
            private set

        /*
            静态方法，获取RefWatcher
         */
        fun getRefWatcher(context: Context): RefWatcher {
            return instance.refWatcher
        }
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        //初始化ARouter
        ARouter.openLog()
        ARouter.openDebug()
        ARouter.init(this)

        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this,"NNsHKVMl4HG7DWLoqp3NsUjB-gzGzoHsz", "NKAMBzaJ248RQB4i5qPOCkIB")
        AVOSCloud.setDebugLogEnabled(true)//开启调试日志
        //初始化Timber日志管理工具
        Timber.plant(Timber.DebugTree())
        RxJavaPlugins.setErrorHandler { Timber.e(it) }

        refWatcher = LeakCanary.install(this)
    }


}

/**
 * 通过单例方式获取Application的Context
 */
object AppContext:ContextWrapper(AMSystemApp.instance)