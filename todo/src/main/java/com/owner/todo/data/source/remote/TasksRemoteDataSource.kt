package com.owner.todo.data.source.remote

import com.owner.todo.data.Task
import com.owner.todo.data.source.TasksDataSource

/**
 *  访问远程数据
 * Created by Liuyong on 2018-12-08.It's AMSystem
 *@description:
 */
object TasksRemoteDataSource : TasksDataSource {
    override fun getTasksList(callback: TasksDataSource.LoadTasksListCallback) {
        val tasks = ArrayList<Task>()
        if (tasks.isEmpty()) {
            callback.onTasksListLoad(tasks)
        }
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