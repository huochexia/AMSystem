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
package com.owner.todo.data.source

import com.owner.todo.data.Task
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * 访问任务数据的主要入口
 * 这里没有使用回调方式，如getTasksList(callback)和getTask(id,callback)使用了回调，通过回调接口参数得到查询
 * 结果。回调接口的具体逻辑实现也就是对结果的处理是在调用该接口方法的类中，即TaskViewModel中。
 *
 * 而是使用了Rxjava架构，以流链接的方式将结果返回给TaskViewModel中
 * Created by Liuyong on 2018-12-08.It's AMSystem
 *@description:
 */
interface TasksDataSource {
//    /*
//     *加载任务列表回调接口
//     */
//    interface LoadTasksListCallback {
//
//        fun onTasksListLoad(tasks: List<Task>)
//
//        fun onDataNotAvailable()//没有得到结果
//    }
//
//    /*
//       加载某一项任务回调接口
//     */
//    interface GetTaskCallback {
//
//        fun onTaskLoaded(task: Task)
//
//        fun onDataNotAvailable()//没有得到结果
//    }

    fun getTasksList(): Flowable<List<Task>>

    fun getTask(taskId: String):Single<Task>

    fun saveTask(task: Task)
    /*
      完成任务
     */
    fun completeTask(task: Task)

    fun completeTask(taskId: String)
    /*
      激活任务
     */
    fun activateTask(task: Task)

    fun activateTask(taskId: String)
    /*
      清理已完成任务
     */
    fun clearCompletedTasks()

    /*
      刷新任务列表
     */
    fun refreshTasks()

    fun deleteAllTasks()

    fun deleteTask(taskId: String)
}