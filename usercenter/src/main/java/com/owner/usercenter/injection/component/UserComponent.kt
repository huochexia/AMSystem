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
package com.owner.usercenter.injection.component

import android.support.v7.app.AppCompatActivity
import com.owner.baselibrary.injection.component.ActivityComponent
import com.owner.baselibrary.injection.qualifier.ActivityContext
import com.owner.baselibrary.injection.scope.ActivityScope
import com.owner.baselibrary.injection.scope.PerComponentScope
import com.owner.usercenter.injection.module.UserModule
import com.owner.usercenter.ui.activity.RegisterActivity
import dagger.Component

/**
 *
 * Created by Liuyong on 2018-09-21.It's AMSystem
 *@description:
 */
@PerComponentScope
@Component(dependencies = [ActivityComponent::class], modules = [UserModule::class])
interface UserComponent {

    fun inject(registerActivity: RegisterActivity)

}