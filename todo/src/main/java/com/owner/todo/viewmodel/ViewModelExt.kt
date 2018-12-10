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

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.owner.todo.data.source.TasksRepository
import com.owner.todo.data.source.local.TasksLocalDataSource
import com.owner.todo.data.source.local.ToDoDatabase
import com.owner.todo.data.source.remote.TasksRemoteDataSource
import com.owner.todo.util.AppExecutors

/**
 *
 * Created by Liuyong on 2018-12-09.It's AMSystem
 *@description:
 */
fun <T : ViewModel> AppCompatActivity.obtainViewModel(viewModelClass: Class<T>) =
        ViewModelProviders.of(this, ViewModelFactory.getInstance(application,
                provideTasksRepository(applicationContext))).get(viewModelClass)


fun provideTasksRepository(context: Context) : TasksRepository {

    val database = ToDoDatabase.getInstance(context)
    return TasksRepository(TasksRemoteDataSource,TasksLocalDataSource.getInstance(AppExecutors(),database.taskDao()))
}