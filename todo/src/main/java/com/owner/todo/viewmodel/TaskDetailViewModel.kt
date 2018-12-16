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
package com.owner.todo.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.support.annotation.StringRes
import com.owner.baselibrary.common.SingleLiveEvent
import com.owner.todo.R
import com.owner.todo.data.Task
import com.owner.todo.data.source.TasksRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 *
 * Created by Liuyong on 2018-12-14.It's AMSystem
 *@description:
 */
class TaskDetailViewModel(
        context: Application,
        private val tasksRepository: TasksRepository
) :AndroidViewModel(context){

    val compositeDisposable = CompositeDisposable()

    val singleTask = ObservableField<Task>()
    val completed = ObservableBoolean()
    val editTaskCommand = SingleLiveEvent<Void>()
    val deleteTaskCommand = SingleLiveEvent<Void>()
    val snackbarMessage = SingleLiveEvent<Int>()
    var isDataLoading = false
        private  set
    val isDataAvailable
        get() = singleTask.get() !=null

    fun deleteTask() {
        singleTask.get()?.let {
            tasksRepository.deleteTaskById(it.objectId).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                       deleteTaskCommand.call()
                    },{})
        }
    }

    fun editTask() {
        editTaskCommand.call()
    }

    fun setCompleted(completed: Boolean) {
        if (isDataLoading) {
            return
        }
        val task = this.singleTask.get().apply {
            this!!.isCompleted = completed
        }
        val disposable=tasksRepository.completeTask(task!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        compositeDisposable.add(disposable)
        if (completed) {
            showSnackbarMessage(R.string.task_marked_complete)
        } else {
            showSnackbarMessage(R.string.task_marked_active)
        }

    }

    private fun showSnackbarMessage(@StringRes message: Int) {
        snackbarMessage.value = message
    }
    fun start(taskId: String?):Single<Task> {
        isDataLoading = true
        return taskId?.let { it ->
            tasksRepository.getTask(it).doOnSuccess { task ->
                setTask(task)
                isDataLoading = false
            }
        }!!
    }

    fun setTask(task: Task) {
        this.singleTask.set(task)
        completed.set(task.isCompleted)

    }

    fun onRefresh() {
        if (singleTask.get() != null) {
            start(singleTask.get()!!.objectId)
        }
    }
}