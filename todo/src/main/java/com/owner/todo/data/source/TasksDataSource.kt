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

/**
 * 访问任务数据的主要入口
 * 用前仅仅getTasksList()和getTask()使用了回调，未来应该给其他方法也增加回调方法，以便当网络或数据库
 * 操作成功或出现错误时通知用户。
 * Created by Liuyong on 2018-12-08.It's AMSystem
 *@description:
 */
interface TasksDataSource {
    /*
     *加载任务列表回调接口
     */
    interface LoadTasksListCallback {

        fun onTasksListLoad(tasks: List<Task>)

        fun onDataNotAvailable()//没有得到结果
    }

    /*
       加载某一项任务回调接口
     */
    interface GetTaskCallback {

        fun onTaskLoaded(task: Task)

        fun onDataNotAvailable()//没有得到结果
    }

    fun getTasksList(callback: LoadTasksListCallback)

    fun getTask(taskId: String, callback: GetTaskCallback)

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