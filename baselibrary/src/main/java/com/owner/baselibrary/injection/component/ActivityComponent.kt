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
package com.owner.baselibrary.injection.component

import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.owner.baselibrary.injection.module.ActivityModule
import com.owner.baselibrary.injection.module.ApiModule
import com.owner.baselibrary.injection.module.LifecycleModule
import com.owner.baselibrary.injection.qualifier.ActivityContext
import com.owner.baselibrary.injection.qualifier.AppContext
import com.owner.baselibrary.injection.scope.ActivityScope
import com.squareup.leakcanary.RefWatcher
import com.trello.rxlifecycle2.LifecycleProvider
import dagger.Component

/**
 * Activity在Dagger中属于二级组件，它主要服务于Activity，为它的子类提供服务
 * 提供方法。它依赖AppComponent，但是上一级的方法不能直接继承，还是需要在这里暴露出来。
 * 同时，它的一些服务方法也要暴露出去
 * Created by Liuyong on 2018-09-15.It's AMSystem
 *@description:
 */
@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class, LifecycleModule::class])
interface ActivityComponent {


    @AppContext
    fun context(): Context

    @ActivityContext
    fun activity(): AppCompatActivity

    fun lifecycleProvider(): LifecycleProvider<*>

    fun refWatcher(): RefWatcher

}