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
import com.owner.baselibrary.ext.execute
import com.owner.todo.R
import com.owner.todo.data.Task
import com.owner.todo.data.source.TasksRepository
import io.reactivex.disposables.CompositeDisposable

/**
 *
 * Created by Liuyong on 2018-12-16.It's AMSystem
 *@description:
 */
class StatisticsViewModel(
        private val context:Application,
        private val tasksRepository: TasksRepository
) :AndroidViewModel(context){

    val compositeDisposable = CompositeDisposable()

    val dataLoading = ObservableBoolean(false)
    val error = ObservableBoolean(false)
    val numberOfActiveTasksString = ObservableField<String>()
    val numberOfCompletedTasksString = ObservableField<String>()
    /**
     * Controls whether the stats are shown or a "No data" message
     */
    val empty = ObservableBoolean()

    private var numberOfActiveTasks = 0
    private var numberOfCompletedTasks = 0

    fun start() {
        loadStatistics()
    }

    private fun loadStatistics() {
        dataLoading.set(true)
        val disposable =tasksRepository.getTasksList().execute()
                .subscribe({
                    error.set(false)
                    computeStats(it)
                },{
                    error.set(true)
                    numberOfActiveTasks = 0
                    numberOfCompletedTasks = 0
                    updateDataBindingObservables()
                })
        compositeDisposable.add(disposable)
    }

    /**
     * 计算
     */
    private fun computeStats(list: List<Task>) {
        numberOfCompletedTasks = list.count { it.isCompleted }
        numberOfActiveTasks = list.count { !it.isCompleted }
        updateDataBindingObservables()
    }

    private fun updateDataBindingObservables() {

        numberOfCompletedTasksString.set(
                context.getString(R.string.statistics_completed_tasks,numberOfCompletedTasks))
        numberOfActiveTasksString.set(
                context.getString(R.string.statistics_active_tasks,numberOfActiveTasks))
        empty.set(numberOfActiveTasks+numberOfCompletedTasks == 0)
        dataLoading.set(false)
    }
}