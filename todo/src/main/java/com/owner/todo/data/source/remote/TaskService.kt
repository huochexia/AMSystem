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

import com.owner.baselibrary.model.network.RetrofitFactory
import com.owner.todo.data.Task
import com.owner.todo.data.source.remote.entites.TaskList
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*

/**
 *
 * Created by Liuyong on 2018-12-09.It's AMSystem
 *@description:
 */
interface TaskServiceApi {
    /*
    创建任务
     */
    @POST(value= "1.1/classes/Task?fetchWhenSave=true")
    fun createTask(@Body task: Task): Observable<Task>

    /*
    查询全部
     */
    @GET("1.1/classes/Task")
    fun getTasksList(@Query("where") condition: String): Observable<TaskList>

    /*
      查询某个任务
     */
    @GET("1.1/classes/Task/{id}")
    fun getTaskById(@Path("id") taskId: String): Single<Task>

    /*
      更新任务
     */
    @PUT("1.1/classes/Task/{objectId}")
    fun updateTask(@Path("objectId") objectId:String, @Body task:Task):Completable

    /*
      删除任务
     */
    @DELETE("1.1/classes/Task/{id}")
    fun deleteTaskById(@Path("id") taskId: String):Single<Any>

    /*
      按条件删除任务
     */
    @DELETE("1.1/classes/Task")
    fun deleteTask(@Query("where") condition:String):Single<Any>
}

object TaskService : TaskServiceApi by RetrofitFactory.instance.create(TaskServiceApi::class.java)