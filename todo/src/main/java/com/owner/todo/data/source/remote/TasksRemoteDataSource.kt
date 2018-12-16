package com.owner.todo.data.source.remote

import com.owner.baselibrary.utils.AppPrefsUtils
import com.owner.provideslib.common.ProviderConstant
import com.owner.todo.data.Task
import com.owner.todo.data.source.remote.entites.BatchDeleteRequest
import com.owner.todo.data.source.remote.entites.TaskDelete
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.ResponseBody

/**
 *  访问远程数据
 * Created by Liuyong on 2018-12-08.It's AMSystem
 *@description:
 */
object TasksRemoteDataSource : RemoteDataSource {

    override fun getTasksList(): Observable<List<Task>> {

        val where = """{"userId":"${AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ID)}"}"""
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

    override fun clearCompletedTasks(tasks:List<Task>):Observable<ResponseBody> {
        val deleteTasks = arrayListOf<TaskDelete>()
        tasks.forEach {
            val one = TaskDelete(taskId = it.objectId)
            deleteTasks.add(one)
        }
        return TaskService.deleteTasks(BatchDeleteRequest(deleteTasks))
    }




    override fun deleteTaskById(taskId: String): Completable {
        return TaskService.deleteTaskById(taskId)
    }
}