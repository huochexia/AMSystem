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
package com.owner.baselibrary.injection.module

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.owner.baselibrary.injection.qualifier.AppContext
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 *
 * Created by Liuyong on 2018-09-15.It's AMSystem
 *@description:
 */
@Module
class AppModule(private val app: Application) {

    @Provides
    @Singleton
    @AppContext
    internal fun provideAppContext(): Context = app

    @Provides
    @Singleton
    internal fun provideResources(): Resources = app.resources

    @Provides
    @Singleton
    internal fun provideRefWatcher(): RefWatcher = LeakCanary.install(app)

}