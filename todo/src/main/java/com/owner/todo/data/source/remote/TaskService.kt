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
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 *
 * Created by Liuyong on 2018-12-09.It's AMSystem
 *@description:
 */
interface TaskServiceApi{
    /*
    创建任务
     */
    @POST("1.1/classes/Task?fetchWhenSave=true")
    fun createTask(@Body task:Task):Single<Task>
    /*
    查询全部
     */
    @GET("1.1/classes/Task")
    fun getTasksList(@Query(value = "where") condition:String): Flowable<TaskList>

    /*
      查询某个任务
     */
    @GET("1.1/classes/Task")
    fun getTaskById(@Query(value = "where") condition:String): Single<Task>
}

object TaskService:TaskServiceApi by RetrofitFactory.instance.create(TaskServiceApi::class.java)