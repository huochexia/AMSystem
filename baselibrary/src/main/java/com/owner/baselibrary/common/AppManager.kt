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

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import java.util.*

/**
 * 管理Activty出入栈
 * Created by Liuyong on 2018-09-15.It's AMSystem
 *@description:
 */
class AppManager private constructor(){

    companion object {
        val instance:AppManager by lazy {
            AppManager()
        }
    }

    private val activityStack: Stack<Activity> = Stack()

    /*
    入栈
     */
    fun addActivity(activity: Activity) {
        activityStack.add(activity)
    }

    /*
       出栈
     */
    fun removeActivity(activity: Activity) {
        activityStack.remove(activity)
    }

    /*
       清理栈
     */
    fun finishAllActivity() {
        for (activity in activityStack) {
            activity.finish()
        }
        activityStack.clear()
    }

    /*
      退出应用程序
     */
    fun exitApp(context: Context) {
        finishAllActivity()
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        /**
         * 这里涉及运行时权限
         */
        activityManager.killBackgroundProcesses(context.packageName)
        System.exit(0)

    }
}