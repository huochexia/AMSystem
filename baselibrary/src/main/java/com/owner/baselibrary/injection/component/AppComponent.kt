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
import com.owner.baselibrary.injection.module.ApiModule
import com.owner.baselibrary.injection.module.AppModule
import com.owner.baselibrary.injection.qualifier.AppContext
import com.squareup.leakcanary.RefWatcher
import dagger.Component
import javax.inject.Singleton

/**
 * 应用程序在Dagger中属于一级组件，单例，它主要是对外提供服务，如context。它只是暴露获取方法，
 * 具体实例还是由module来提供
 * Created by Liuyong on 2018-09-15.It's AMSystem
 *@description:
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    @AppContext
    fun context(): Context

    fun refWatcher():RefWatcher


}