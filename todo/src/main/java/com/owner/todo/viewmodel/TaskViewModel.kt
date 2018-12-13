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
import android.content.Context
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableList
import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import com.owner.baselibrary.common.SingleLiveEvent
import com.owner.todo.R
import com.owner.todo.common.TasksFilterType
import com.owner.todo.data.Task
import com.owner.todo.data.source.TasksDataSource
import com.owner.todo.data.source.TasksRepository
import com.owner.todo.util.ADD_EDIT_RESULT_OK
import com.owner.todo.util.DELETE_RESULT_OK
import com.owner.todo.util.EDIT_RESULT_OK
import com.owner.todo.view.activity.AddEditTaskActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 *
 * Created by Liuyong on 2018-12-09.It's AMSystem
 *@description:
 */
class TaskViewModel(context: Application,
                    private val tasksRepository: TasksRepository
) : AndroidViewModel(context) {


    private val context: Context = context.applicationContext
    private val isDataLoadingError = ObservableBoolean(false)//暂时没有它的观察者
    val compositeDisposable = CompositeDisposable()
    internal val openTaskEvent = SingleLiveEvent<String>()

    val items: ObservableList<Task> = ObservableArrayList()
    val dataLoading = ObservableBoolean(false)
    val currentFilteringLabel = ObservableField<String>()
    val noTasksLabel = ObservableField<String>()
    val noTaskIconRes = ObservableField<Drawable>()
    val empty = ObservableBoolean(false)
    val tasksAddViewVisible = ObservableBoolean()
    val snackbarMessage = SingleLiveEvent<Int>()
    val newTaskEvent = SingleLiveEvent<Void>()
    /*
        技巧：当使用setter时，同时执行对其他变量的更改
     */
    var currentFiltering = TasksFilterType.ALL_TASKS
        set(value) {
            field = value
            updateFiltering()//将当前状态的信息赋值给对应的变量。采用种方式是因为一种过滤类型对应四个变量
            //信息：currentFilteringLabel,noTasksLabel,noTaskIconRes,tasksAddVisible
        }

    /**
     * 设置过滤类型，依靠过滤类型设置过滤标签，图标等
     */
    fun updateFiltering() {
        when (currentFiltering) {
            //逻辑：只有当显示全部时，如果得是到空列表，此时显示增加键
            TasksFilterType.ALL_TASKS -> {
                setFilter(R.string.label_all, R.string.no_tasks_all,
                        R.drawable.ic_assignment_turned_in_24dp, true)
            }
            TasksFilterType.ACTIVE_TASKS -> {
                setFilter(R.string.label_active, R.string.no_tasks_active,
                        R.drawable.ic_check_circle_24dp, false)
            }
            TasksFilterType.COMPLETED_TASKS -> {
                setFilter(R.string.label_completed, R.string.no_tasks_completed,
                        R.drawable.ic_verified_user_24dp, false)
            }
        }
    }

    private fun setFilter(@StringRes filteringLabelString: Int, @StringRes noTaskLabelString: Int,
                          @DrawableRes noTaskIconDrawable: Int, tasksAddVisible: Boolean) {
        with(context.resources) {
            currentFilteringLabel.set(getString(filteringLabelString))
            noTasksLabel.set(getString(noTaskLabelString))
            noTaskIconRes.set(getDrawable(noTaskIconDrawable))
            tasksAddViewVisible.set(tasksAddVisible)
        }
    }

    fun start() {
        loadTasks(false)
    }

    fun loadTasks(forceUpdate: Boolean) {

        loadTasks(forceUpdate, true)

    }

    /**
     * @param forceUpdate 传递true，为强行更新，缓存设为脏的
     * @param showLoadingUI 传递true，通知UI显示正在加载图标
     */
    private fun loadTasks(forceUpdate: Boolean, showLoadingUI: Boolean) {
        //显示刷新
        if (showLoadingUI) {
            dataLoading.set(true)
        }
        //强行更新时，将数据缓存设置为脏的
        if (forceUpdate) {
            tasksRepository.refreshTasks()
        }
        val disposable=tasksRepository.getTasksList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    onTaskLoad(it,showLoadingUI)
                },{
                    isDataLoadingError.set(true)
                })
        compositeDisposable.add(disposable)

    }

    /**
     * 过滤任务列表
     */
    private fun onTaskLoad(tasks: List<Task>, showLoadingUI: Boolean) {
        //临时对象，过滤前数据
        val tasksToShow: List<Task>
        //过滤列表
        tasksToShow = when (currentFiltering) {
            TasksFilterType.ALL_TASKS ->
                tasks
            TasksFilterType.ACTIVE_TASKS ->
                tasks.filter { it.isActive }
            TasksFilterType.COMPLETED_TASKS ->
                tasks.filter { it.isCompleted }
        }
        //加载完成后，取消刷新
        if (showLoadingUI) {
            dataLoading.set(false)
        }
        //通知View，加载正常
        isDataLoadingError.set(false)

        with(items) {
            clear()
            addAll(tasksToShow)
            empty.set(isEmpty())
        }
    }

    /**
     * 清除已完成任务
     */
    fun clearCompleteTasks() {
        tasksRepository.clearCompletedTasks()
        //通知View显示完成清理任务提示
        snackbarMessage.value = R.string.completed_tasks_cleared
        //重新加载数据
        loadTasks(false, false)
    }

    /**
     * 完成任务
     */
    fun completeTask(task: Task) {
        //修改任务
        task.isCompleted = !task.isCompleted
        //保存修改
        if (task.isCompleted) {
            tasksRepository.completeTask(task)
            showSnackbarMessage(R.string.task_marked_complete)
        } else {
            tasksRepository.activateTask(task)
            showSnackbarMessage(R.string.task_marked_active)
        }

    }

    private fun showSnackbarMessage(message: Int) {
        snackbarMessage.value = message
    }

    /**
     * 增加新任务
     */
    fun addNewTask() {
        newTaskEvent.call()

    }

    fun handleActivityResult(requestCode: Int, resultCode: Int) {
        if (AddEditTaskActivity.REQUEST_CODE == requestCode) {
            snackbarMessage.value = when (resultCode) {
                ADD_EDIT_RESULT_OK ->R.string.successfully_added_task_message
                EDIT_RESULT_OK ->R.string.successfully_saved_task_message
                DELETE_RESULT_OK ->R.string.successfully_deleted_task_message
                else ->return
            }
        }
    }
}