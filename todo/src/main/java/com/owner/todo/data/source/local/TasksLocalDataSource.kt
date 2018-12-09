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
package com.owner.todo.data.source.local

import com.owner.todo.data.Task
import com.owner.todo.data.source.TasksDataSource
import com.owner.todo.util.AppExecutors

/**
 *
 * Created by Liuyong on 2018-12-08.It's AMSystem
 *@description:
 */
class TasksLocalDataSource private constructor(
        private val appExecutors: AppExecutors,
        private val taskDao: TaskDao
):TasksDataSource{
    override fun getTasksList(callback: TasksDataSource.LoadTasksListCallback) {
        appExecutors.diskIO.execute{
            val tasks = taskDao.getTasks()
            appExecutors.mainThread.execute{
                if (tasks.isEmpty()) {
                    callback.onDataNotAvailable()
                } else {
                    callback.onTasksListLoad(tasks)
                }
            }
        }
    }

    override fun getTask(taskId: String, callback: TasksDataSource.GetTaskCallback) {
        appExecutors.diskIO.execute{
            val task = taskDao.getTaskId(taskId)
            appExecutors.mainThread.execute {
                if (task != null) {
                    callback.onTaskLoaded(task)
                } else {
                    callback.onDataNotAvailable()
                }
            }
        }
    }

    override fun saveTask(task: Task) {
        appExecutors.diskIO.execute { taskDao.insertTask(task) }
    }

    override fun completeTask(task: Task) {
        appExecutors.diskIO.execute { taskDao.updateCompleted(task.id,true) }
    }

    override fun completeTask(taskId: String) {
       //由TasksRepository 实现
    }

    override fun activateTask(task: Task) {
        appExecutors.diskIO.execute { taskDao.updateCompleted(task.id,false) }
    }

    override fun activateTask(taskId: String) {
        //同上
    }

    override fun clearCompletedTasks() {
        appExecutors.diskIO.execute { taskDao.deleteCompletedTasks() }
    }

    override fun refreshTasks() {
        //由TasksRepository 实现
    }

    override fun deleteAllTasks() {
       appExecutors.diskIO.execute { taskDao.deleteTasks() }
    }

    override fun deleteTask(taskId: String) {
        appExecutors.diskIO.execute { taskDao.deleteTaskById(taskId) }
    }
    companion object {
        fun getInstance(appExecutors: AppExecutors,taskDao: TaskDao) = TasksLocalDataSource(appExecutors,taskDao)
    }
}