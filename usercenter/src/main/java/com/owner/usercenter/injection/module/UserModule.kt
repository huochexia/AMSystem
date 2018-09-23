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
package com.owner.usercenter.injection.module

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import com.owner.baselibrary.data.respository.BaseRepository
import com.owner.baselibrary.injection.qualifier.ActivityContext
import com.owner.baselibrary.injection.scope.PerComponentScope
import com.owner.usercenter.data.UserRepository
import com.owner.usercenter.service.UserService
import com.owner.usercenter.service.impl.UserServiceImpl
import com.owner.usercenter.viewmodel.RegisterViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 *
 * Created by Liuyong on 2018-09-21.It's AMSystem
 *@description:
 */
@Module

class UserModule {

    @Provides
    @PerComponentScope
    fun provideRegisterServiceImpl(serviceImpl: UserServiceImpl): UserService {
        return serviceImpl
    }

    @Provides
    @PerComponentScope
    fun provideRepositoryImpl(userRepository: UserRepository): BaseRepository {
        return userRepository
    }
//
//    @Provides
//    @PerComponentScope
//    fun provideRegisterViewModel(@ActivityContext activity:AppCompatActivity): RegisterViewModel {
//        return ViewModelProviders.of(activity).get(RegisterViewModel::class.java)
//    }
}