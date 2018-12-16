package com.owner.todo.data.source.remote

import com.owner.baselibrary.utils.AppPrefsUtils
import com.owner.provideslib.common.ProviderConstant
import com.owner.todo.data.Task
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

/**
 *  访问远程数据
 * Created by Liuyong on 2018-12-08.It's AMSystem
 *@description:
 */
object TasksRemoteDataSource : RemoteDataSource {

    private val userId = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ID)

    override fun getTasksList(): Observable<List<Task>> {

        val where = """{"userId":"$userId"}"""
        return TaskService.getTasksList(where).flatMap {
            Observable.just(it.results)
        }

    }

    override fun getTask(taskId: String): Single<Task> {

        val where = """{"id","$taskId"}"""
        return TaskService.getTaskById(where)
    }

    override fun createTask(task: Task): Observable<Task> = TaskService.createTask(task)

    override fun updateTask(task: Task): Completable {
        val taskId = task.objectId

        return TaskService.updateTask(taskId, task)
    }

    override fun clearCompletedTasks() {
        val where = """{"{'$'}and":[{"isCompleted":"true"},{"userId":"$userId"}]}"""
        TaskService.deleteTask(where)
    }

    override fun refreshTasks() {

    }

    override fun deleteAllTasks() {
        val where ="""{"userId":"$userId"}"""
        TaskService.deleteTask(where)
    }

    override fun deleteTaskById(taskId: String): Single<Any> {
        return TaskService.deleteTaskById(taskId)
    }
}