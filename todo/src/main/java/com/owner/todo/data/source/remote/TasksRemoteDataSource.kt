package com.owner.todo.data.source.remote

import com.owner.baselibrary.ext.execute
import com.owner.baselibrary.utils.AppPrefsUtils
import com.owner.provideslib.common.ProviderConstant
import com.owner.todo.data.Task
import com.owner.todo.data.source.TasksDataSource

/**
 *  访问远程数据
 * Created by Liuyong on 2018-12-08.It's AMSystem
 *@description:
 */
object TasksRemoteDataSource : TasksDataSource {
    override fun getTasksList(callback: TasksDataSource.LoadTasksListCallback) {

        val userId = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ID)
        val where: String = """{"userId","$userId"}"""
        val disposable=TaskService.getTasksList(where).execute()
                .subscribe({
                    if (it.results == null) {
                        val tasks = ArrayList<Task>()
                        callback.onTasksListLoad(tasks)
                    } else {
                        callback.onTasksListLoad(it.results)
                    }
                }, {
                    callback.onDataNotAvailable()
                })
    }

    override fun getTask(taskId: String, callback: TasksDataSource.GetTaskCallback) {

    }

    override fun saveTask(task: Task) {

    }

    override fun completeTask(task: Task) {

    }

    override fun completeTask(taskId: String) {

    }

    override fun activateTask(task: Task) {

    }

    override fun activateTask(taskId: String) {

    }

    override fun clearCompletedTasks() {

    }

    override fun refreshTasks() {

    }

    override fun deleteAllTasks() {

    }

    override fun deleteTask(taskId: String) {

    }
}