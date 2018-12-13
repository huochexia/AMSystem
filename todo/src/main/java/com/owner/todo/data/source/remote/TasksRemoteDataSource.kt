package com.owner.todo.data.source.remote

import com.owner.baselibrary.utils.AppPrefsUtils
import com.owner.provideslib.common.ProviderConstant
import com.owner.todo.data.Task
import com.owner.todo.data.source.TasksDataSource
import io.reactivex.Flowable
import io.reactivex.Single

/**
 *  访问远程数据
 * Created by Liuyong on 2018-12-08.It's AMSystem
 *@description:
 */
object TasksRemoteDataSource : TasksDataSource {

    private val userId = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ID)

    override fun getTasksList(): Flowable<List<Task>> {

        val where: String = """{"userId","$userId"}"""
        return TaskService.getTasksList(where).flatMap {
            Flowable.just(it.results)
        }

    }

    override fun getTask(taskId: String): Single<Task> {

        val where = """{"id","$taskId"}"""
        return TaskService.getTaskById(where)
    }

    override fun saveTask(task: Task) {
        TaskService.createTask(task)
    }

    override fun completeTask(task: Task) {
        completeTask(task.id)
    }

    override fun completeTask(taskId: String) {
        TaskService.updateTask(taskId)
    }

    override fun activateTask(task: Task) {
        activateTask(task.id)
    }

    override fun activateTask(taskId: String) {
        TaskService.updateTask(taskId)
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

    override fun deleteTask(taskId: String) {
        TaskService.deleteTaskById(taskId)
    }
}