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
package com.owner.todo.util

import android.os.Handler
import android.os.Looper
import android.support.annotation.MainThread
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 *因为数据库操作是耗时操作，所以不能放入主线程中，所以增加线程管理代码 。标准写法
 * Created by Liuyong on 2018-12-08.It's AMSystem
 *@description:
 */
const val THREAD_COUNT = 3

open class AppExecutors constructor(
        val diskIO:Executor = DiskIOThreadExecutor(),
        val networkIO:Executor = Executors.newFixedThreadPool(THREAD_COUNT),
        val mainThread: Executor = MainThreadExecutor()
){
    private class MainThreadExecutor:Executor{
        private val mainThreadHandler = Handler(Looper.getMainLooper())
        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }
}

class DiskIOThreadExecutor : Executor {

    private val diskIO =  Executors.newSingleThreadExecutor()
    override fun execute(command: Runnable) {
        diskIO.execute(command)
    }

}