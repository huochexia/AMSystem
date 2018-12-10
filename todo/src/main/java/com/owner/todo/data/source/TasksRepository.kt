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

import com.owner.baselibrary.model.respository.BaseRepository
import com.owner.todo.data.Task
import com.owner.todo.data.source.remote.TasksRemoteDataSource
import io.reactivex.Flowable
import io.reactivex.Single

/**
 *  是远程、本地以及缓存的合体
 * Created by Liuyong on 2018-12-08.It's AMSystem
 *@description:
 */
class TasksRepository(
        val remoteDataSource: TasksDataSource,
        val localDataSource: TasksDataSource
) : TasksDataSource,BaseRepository {

    var cachedTasks: LinkedHashMap<String, Task> = LinkedHashMap()
    /**
     * 用于标记缓存无效，强迫在下一次请求数据时进行更新。
     */
    var cacheIsDirty = false

    /**
     * 从缓存、本地数据库或远程数据库中获取任务列表，先从缓存，然后判断数据是否变化，无变化则从本地，
     * 有变化则从远程。无论是从本地还是从远程，都需要先刷新缓存。
     */
    override fun getTasksList(): Flowable<List<Task>> {
        //如果缓存数据有效，立即响应
        if (cachedTasks.isNotEmpty() && !cacheIsDirty) {

            return Flowable.just(ArrayList(cachedTasks.values))
        }
        //如果缓存数据是脏的
        return if (cacheIsDirty) {
            //如果缓存的数据是脏的，我们需要从网络获取新的数据
            getTasksFromRemoteDataSource()
        } else {
            //否则，从本地数据库中获取数据。
            localDataSource.getTasksList().doOnNext {
                if (it.isEmpty())
                    getTasksFromRemoteDataSource()
                else {
                    refreshCache(it)
                    Flowable.just(ArrayList(cachedTasks.values))
                }
            }
        }
    }

    private fun getTasksFromRemoteDataSource(): Flowable<List<Task>> {

        return remoteDataSource.getTasksList().doOnNext {
            refreshCache(it)
            refreshLocalDataSource(it)
        }.map {
            ArrayList(cachedTasks.values)
        }
    }

    /**
     * 刷新缓存，先清空缓存，然后遍历新列表，将新列表的内容进行复制一份存入缓存
     */
    private fun refreshCache(tasks: List<Task>) {
        cachedTasks.clear()
        tasks.forEach {
            cacheAndPerform(it) {}
        }
        cacheIsDirty = false
    }

    private fun refreshLocalDataSource(tasks: List<Task>) {
        localDataSource.deleteAllTasks()
        for (task in tasks) {
            localDataSource.saveTask(task)
        }
    }

    /**
     * 复制一个新的对象，存入缓存列表中。可以对新对象进行某个操作
     */
    private fun cacheAndPerform(task: Task, perform: (Task) -> Unit) {
        //生成一个新的对象
        val cachedTask = Task(task.title, task.description, task.id).apply {
            isCompleted = task.isCompleted
        }
        cachedTasks[cachedTask.id] = cachedTask
        perform(cachedTask)
    }

    /**
     * UI对数据的更新是在缓存中进行的，所以为了保持UI与数据库一致，所以要将更新变化保存的远程和本地。
     */
    override fun saveTask(task: Task) {
        //对缓存中的数据进行更新，保持UI为最新
        cacheAndPerform(task) {
            remoteDataSource.saveTask(it)
            localDataSource.saveTask(it)
        }
    }

    /**
     * UI上设置任务为完成状态，同样要更新远程数据和本地数据
     */
    override fun completeTask(task: Task) {
        cacheAndPerform(task) {
            it.isCompleted = true
            remoteDataSource.saveTask(task)
            localDataSource.saveTask(task)
        }
    }

    override fun completeTask(taskId: String) {
        getTaskWithId(taskId)?.let {
            completeTask(it)
        }
    }

    private fun getTaskWithId(taskId: String) = cachedTasks[taskId]
    /**
     * 激活任务，即指定任务为未完成状态
     */
    override fun activateTask(task: Task) {
        cacheAndPerform(task) {
            it.isCompleted = false
            remoteDataSource.saveTask(task)
            localDataSource.saveTask(task)
        }
    }

    override fun activateTask(taskId: String) {
        getTaskWithId(taskId)?.let {
            activateTask(it)
        }
    }

    /**
     * 清理已完成任务，分别清理远程数据库，本地数据库以及缓存中
     */
    override fun clearCompletedTasks() {
        remoteDataSource.clearCompletedTasks()
        localDataSource.clearCompletedTasks()
        //利用集合的过滤功能，过滤掉已完成任务，需要强转
        cachedTasks = cachedTasks.filterValues {
            !it.isCompleted
        } as LinkedHashMap<String, Task>
    }

    /**
     * 从本地获取单个任务，除非本地是新的或空的。
     */
    override fun getTask(taskId: String): Single<Task> {
        //先从缓存中得到该对象
        val taskInCache = getTaskWithId(taskId)

        if (taskInCache != null) {
            return Single.just(taskInCache)
        }
        //从本地获取数据，如果没有，则是从远程获取
        return localDataSource.getTask(taskId).doOnError { throwable ->
            TasksRemoteDataSource.getTask(taskId).doOnSuccess { task ->
                cacheAndPerform(task) {
                    Single.just(it)
                }
            }
        }.doOnSuccess { task ->
            cacheAndPerform(task) {
                Single.just(it)
            }
        }

    }

    /**
     * 刷新列表：缓存数据发生变化，将缓存设置为脏的。
     */
    override fun refreshTasks() {
        cacheIsDirty = true
    }

    /**
     * 删除所有任务
     */
    override fun deleteAllTasks() {
        remoteDataSource.deleteAllTasks()
        localDataSource.deleteAllTasks()
        cachedTasks.clear()
    }

    override fun deleteTask(taskId: String) {
        remoteDataSource.deleteTask(taskId)
        localDataSource.deleteTask(taskId)
        cachedTasks.remove(taskId)
    }

    /**
     * 单例模式
     */
    companion object {
        private var INSTANCE: TasksRepository? = null

        @JvmStatic
        fun getInstance(tasksRemoteDataSource: TasksDataSource,
                        taskLocalDataSource: TasksDataSource) =
                INSTANCE ?: synchronized(TasksRepository::class.java) {
                    INSTANCE ?: TasksRepository(tasksRemoteDataSource, taskLocalDataSource)
                            .also { INSTANCE = it }
                }

        @JvmStatic
        fun destoryInstance() {
            INSTANCE = null
        }
    }
}