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
import com.owner.baselibrary.ext.execute
import com.owner.baselibrary.utils.AppPrefsUtils
import com.owner.provideslib.common.ProviderConstant
import com.owner.todo.R
import com.owner.todo.data.Task
import com.owner.todo.data.source.TasksRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * ViewModel for the Add/Edit screen
 * Created by Liuyong on 2018-12-10.It's AMSystem
 *@description:
 */
class AddEditTaskViewModel(
        context: Application,
        private val tasksRepository: TasksRepository
) : AndroidViewModel(context) {

    val compositeDisposable = CompositeDisposable()
    val title = ObservableField<String>()
    val description = ObservableField<String>()
    val dataLoading = ObservableBoolean(false)

    internal val snackbarMessage = SingleLiveEvent<Int>()
    internal val taskUpdateEvent = SingleLiveEvent<Void>()

    private var taskId: String? = null
    private val isNewTask
        get() = taskId == null

    private var isDataLoaded = false
    private var taskCompleted = false

    fun start(taskId: String?) {
        if (dataLoading.get()) {
            return
        }
        this.taskId = taskId
        if (isNewTask || isDataLoaded) {
            return
        }
        dataLoading.set(true)
        taskId?.let {
            val disposable = tasksRepository.getTask(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ task ->
                        onTaskLoaded(task)
                    }, {
                        dataLoading.set(false)
                    })
            compositeDisposable.add(disposable)
        }
    }

    private fun onTaskLoaded(task: Task) {
        title.set(task.title)
        description.set(task.description)
        taskCompleted = task.isCompleted
        dataLoading.set(false)
        isDataLoaded = true
    }

    fun saveTask() {
        val task = Task(title.get()!!,description.get()!!,AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ID))
        if (task.isEmpty) {
            showSnackbarMessage(R.string.empty_task_message)
        }

        if (isNewTask) {
            createTask(task)
        } else {
            taskId?.let {
                updateTask(Task(title.get()!!,description.get()!!,
                        AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ID),it)
                        .apply { isCompleted = taskCompleted })
            }
        }

    }

    /**
     * 生成新的任务
     */
    private fun createTask(newTask: Task) {
        val disposable = tasksRepository.createTask(newTask).execute()
                .subscribe({},{},{ taskUpdateEvent.call()})

        compositeDisposable.add(disposable)
    }

    private fun updateTask(task: Task) {
        if (isNewTask) {
            throw RuntimeException("updateTask() was called but task is new.")
        }
        val disposable=tasksRepository.saveTask(task).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    taskUpdateEvent.call()
                },{})
       compositeDisposable.add(disposable)
    }
    private fun showSnackbarMessage(@StringRes message: Int) {
       snackbarMessage.value = message
    }
}