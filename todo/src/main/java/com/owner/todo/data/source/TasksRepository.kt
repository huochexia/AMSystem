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
import com.owner.baselibrary.utils.AppPrefsUtils
import com.owner.provideslib.common.ProviderConstant
import com.owner.todo.data.Task
import com.owner.todo.data.source.local.LocalDataSource
import com.owner.todo.data.source.remote.RemoteDataSource
import com.owner.todo.data.source.remote.TasksRemoteDataSource
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.ResponseBody

/**
 *  是远程、本地以及缓存的合体
 * Created by Liuyong on 2018-12-08.It's AMSystem
 *@description:
 */
class TasksRepository(
        val remoteDataSource: RemoteDataSource,
        val localDataSource: LocalDataSource
) : BaseRepository {

    var cachedTasks: LinkedHashMap<String, Task> = LinkedHashMap()
    /**
     * 用于标记缓存无效，强迫在下一次请求数据时进行更新。
     */
    var cacheIsDirty = false

    /**
     * 从缓存、本地数据库或远程数据库中获取任务列表，先从缓存，然后判断数据是否变化，无变化则从本地，
     * 有变化则从远程。无论是从本地还是从远程，都需要先刷新缓存。
     */
    fun getTasksList(): Observable<List<Task>> {
        if (cacheIsDirty) {
            return getTasksFromRemoteDataSource()
        }
        /*
          从三个不同数据源获取数据，缓存空则本地，本地空则远程。下面是响应编程的写法
         */
        val cacheObservable = Observable.just(ArrayList(cachedTasks.values))
        val localObservable = localDataSource.getTasksList().toObservable().flatMap {
            refreshCache(it)
            Observable.just(ArrayList(cachedTasks.values))
        }
        val remoteObservable = getTasksFromRemoteDataSource()
        return Observable.concat(cacheObservable,localObservable,remoteObservable)
                .takeUntil{
                    it.isNotEmpty()
                }
        /*
           下面是传统的写法。
         */
//        //如果缓存数据有效，立即响应
//        if (cachedTasks.isNotEmpty() && !cacheIsDirty) {
//
//            return Observable.just(ArrayList(cachedTasks.values))
//        }
//
//        return if (cacheIsDirty) {
//            //如果缓存的数据是脏的，我们需要从网络获取新的数据
//            getTasksFromRemoteDataSource()
//        } else {
//            //否则，从本地数据库中获取数据。
//            localDataSource.getTasksList().toObservable().flatMap {
//                if (it.isEmpty()) {
//                    getTasksFromRemoteDataSource()
//                } else {
//                    refreshCache(it)
//                    Observable.just(ArrayList(cachedTasks.values))
//                }
//            }
//        }
    }

    private fun getTasksFromRemoteDataSource(): Observable<List<Task>> {

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
        val cachedTask = Task(task.title, task.description,
                AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ID), task.objectId).apply {
            isCompleted = task.isCompleted
        }
        cachedTasks[cachedTask.objectId] = cachedTask
        perform(cachedTask)
    }

    /**
     * 生成新的,当远程生成数据成功后，刷新缓存和本地数据，以保持一致
     */
    fun createTask(task: Task): Observable<Task> {
        return remoteDataSource.createTask(task)
    }
    /**
     * UI对数据的更新是在缓存中进行的，所以为了保持UI与数据库一致，所以要将更新变化保存的远程和本地。
     * 应该是远程保存成功了才保存本地，这样才能保持一致性
     */
    fun saveTask(task: Task): Completable =
            remoteDataSource.updateTask(task)
                    .doOnComplete {
                        //更新缓存和本地。没考虑如果本地更新失败的处理
                        cacheAndPerform(task) {
                            localDataSource.saveTask(it)
                        }
                    }

    /**
     * UI上设置任务为完成状态，同样要更新远程数据和本地数据
     */
    fun completeTask(task: Task): Completable =
            remoteDataSource.updateTask(task).doOnComplete {
                cacheAndPerform(task) {
                    localDataSource.completeTask(task)
                }
            }.doOnError {
                println("Update is Failure"+it.localizedMessage)
            }


    fun completeTask(taskId: String): Completable = completeTask(getTaskWithId(taskId)!!)

    private fun getTaskWithId(taskId: String) = cachedTasks[taskId]


    /**
     * 清理已完成任务，分别清理远程数据库，本地数据库以及缓存中
     */
    fun clearCompletedTasks(tasks:List<Task>):Observable<ResponseBody> =
         remoteDataSource.clearCompletedTasks(tasks).doOnNext {
             localDataSource.clearCompletedTasks()
             //利用集合的过滤功能，过滤掉已完成任务，需要强转
             cachedTasks = cachedTasks.filterValues {task->
                 !task.isCompleted
             } as LinkedHashMap<String, Task>
         }



    /**
     * 从本地获取单个任务，除非本地是新的或空的。
     */
    fun getTask(taskId: String): Single<Task> {
        //先从缓存中得到该对象
        val taskInCache = getTaskWithId(taskId)

        if (taskInCache != null) {
            return Single.just(taskInCache)
        }
        //从本地获取数据，如果没有，则是从远程获取
        return localDataSource.getTask(taskId).flatMap {
            if (it == null) {
                TasksRemoteDataSource.getTask(taskId).doOnSuccess { task ->
                    cacheAndPerform(task) {}
                    localDataSource.saveTask(task)
                }
            } else {
                cacheAndPerform(it) {}
                Single.just(it)
            }
        }
    }

    /**
     * 刷新列表，是为了让缓存、本地与远程数据一致起来，所以刷新列表就是重新获取远程数据。正常使用
     * 过程中，如果缓存中有数据或本地有数据时是不从远程获取数据的，所以为了能够强制执行从远程获取
     * 数据，就要设定一个条件，当cacheIsDirty为true时，从远程获取数据。于是刷新的逻辑就是将cacheIsDirty
     * 设置为true后，再调用获取数据方法。
     */
    fun refreshTasks() {
        cacheIsDirty = true
    }

    /**
     * 删除所有任务
     */
    fun deleteAllTasks() {
//        remoteDataSource.deleteAllTasks()
//        localDataSource.deleteAllTasks()
//        cachedTasks.clear()
    }

    fun deleteTask(taskId: String) {
        remoteDataSource.deleteTaskById(taskId)
        localDataSource.deleteTask(taskId)
        cachedTasks.remove(taskId)
    }

    fun deleteTaskById(taskId: String): Completable {
        return remoteDataSource.deleteTaskById(taskId).doOnComplete {
            localDataSource.deleteTask(taskId)
            cachedTasks.remove(taskId)
        }.doOnError {
            println("Delete is Failure!"+it.localizedMessage)
        }

    }

    /**
     * 单例模式
     */
    companion object {
        private var INSTANCE: TasksRepository? = null

        @JvmStatic
        fun getInstance(tasksRemoteDataSource: TasksRemoteDataSource,
                        taskLocalDataSource: LocalDataSource) =
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