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
package com.owner.todo.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.annotation.VisibleForTesting
import com.owner.todo.data.source.TasksRepository

/**
 *
 * Created by Liuyong on 2018-12-08.It's AMSystem
 *@description:
 */
class ViewModelFactory private constructor(
        private val application: Application,
        private val taskRepository: TasksRepository
):ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            with(modelClass) {
                when {
                    isAssignableFrom(TaskViewModel::class.java) -> TaskViewModel(application, taskRepository)
                    isAssignableFrom(AddEditTaskViewModel::class.java) -> AddEditTaskViewModel(application, taskRepository)
                    else ->
                        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            } as T

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application, taskRepository: TasksRepository) =
                INSTANCE
                        ?: synchronized(ViewModelFactory::class.java) {
                            INSTANCE
                                    ?: ViewModelFactory(application, taskRepository).also {
                        INSTANCE = it
                    }
                }

        @VisibleForTesting
        fun destoryInstance() {
            INSTANCE = null
        }
    }
}