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

import com.owner.baselibrary.utils.AppPrefsUtils
import com.owner.provideslib.common.ProviderConstant
import com.owner.todo.data.Task
import com.owner.todo.util.AppExecutors
import io.reactivex.Flowable
import io.reactivex.Single

/**
 *
 * Created by Liuyong on 2018-12-08.It's AMSystem
 *@description:
 */
class TasksLocalDataSource private constructor(
        private val appExecutors: AppExecutors,
        private val taskDao: TaskDao
): LocalDataSource {

    override fun getTasksList(): Flowable<List<Task>> = taskDao.getTasks(
            AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ID))


    override fun getTask(taskId: String): Single<Task> = taskDao.getTaskId(taskId)


    override fun saveTask(task: Task) {
        appExecutors.diskIO.execute { taskDao.insertTask(task) }
    }

    override fun completeTask(task: Task) {
        appExecutors.diskIO.execute { taskDao.updateCompleted(task.objectId,task.isCompleted) }
    }

    override fun completeTask(taskId: String) {
       //由TasksRepository 实现
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