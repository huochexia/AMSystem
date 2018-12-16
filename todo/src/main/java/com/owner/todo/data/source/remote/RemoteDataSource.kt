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
package com.owner.todo.data.source.remote

import com.owner.todo.data.Task
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.ResponseBody

/**
 *
 * Created by Liuyong on 2018-12-15.It's AMSystem
 *@description:
 */
interface RemoteDataSource {

    /*
     *获取全部
     */
    fun getTasksList(): Observable<List<Task>>
    /*
      获取单个
     */
    fun getTask(taskId: String): Single<Task>
    /*
    生成新的任务
     */
    fun createTask(task:Task):Observable<Task>
    /*
     更新
     */
    fun updateTask(task: Task):Completable
    /*
      批量删除
     */
    fun clearCompletedTasks(tasks:List<Task>):Observable<ResponseBody>

    /*
      删除单个
     */
    fun deleteTaskById(taskId: String): Completable
}