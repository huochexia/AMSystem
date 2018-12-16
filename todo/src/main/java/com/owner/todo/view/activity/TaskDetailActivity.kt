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
package com.owner.todo.view.activity

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.owner.baselibrary.ext.addFragment
import com.owner.baselibrary.ext.setupToolBar
import com.owner.todo.R
import com.owner.todo.ext.obtainViewModel
import com.owner.todo.util.ADD_EDIT_RESULT_OK
import com.owner.todo.util.DELETE_RESULT_OK
import com.owner.todo.util.EDIT_RESULT_OK
import com.owner.todo.view.fragment.AddEditTaskFragment
import com.owner.todo.view.fragment.TaskDetailFragment
import com.owner.todo.view.fragment.TaskDetailFragment.Companion.REQUEST_EDIT_TASK
import com.owner.todo.viewmodel.TaskDetailViewModel

/**
 *
 * Created by Liuyong on 2018-12-14.It's AMSystem
 *@description:
 */
class TaskDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: TaskDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_task_detail)

        setupToolBar(R.id.mToolbar) {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        supportFragmentManager.findFragmentById(R.id.mDetailContainer)
                ?: addFragment(TaskDetailFragment.newInstance(intent.getStringExtra(EXTRA_TASK_ID))
                        , R.id.mDetailContainer)

        viewModel = obtainViewModel()

        subscribeToNavigationChanges(viewModel)
    }

    private fun subscribeToNavigationChanges(viewModel: TaskDetailViewModel) {
        //观察ViewModel中的导航命令
        val activity = this@TaskDetailActivity
        viewModel.run {
            editTaskCommand.observe(activity,
                    Observer { activity.onStartEditTask() })
            deleteTaskCommand.observe(activity,
                    Observer { activity.onTaskDeleted() })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        /*
          流程：由TaskActivity启动TaskDetailActivity,由TaskDetailActivity中启动AddEditTaskActivity。
          结果AddEditTaskActivity后要求返回到TaskActivity中。所以这里要根据响应码进行判断，如果是从
          AddEditTaskActivity中返回的，则要继续返回，所以这里调用的setResult()方法。
         */
        if (requestCode == REQUEST_EDIT_TASK)
            if (resultCode == ADD_EDIT_RESULT_OK) {
            setResult(EDIT_RESULT_OK)
            finish()
            }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun onTaskDeleted() {
        setResult(DELETE_RESULT_OK)
        finish()
    }

    private fun onStartEditTask() {
        val taskId = intent.getStringExtra(EXTRA_TASK_ID)
        val intent = Intent(this, AddEditTaskActivity::class.java).apply {
            putExtra(AddEditTaskFragment.ARGUMENT_EDIT_TASK_ID, taskId)
        }
        startActivityForResult(intent, REQUEST_EDIT_TASK)
    }


    companion object {
        const val EXTRA_TASK_ID = "TASK_ID"
    }

    fun obtainViewModel() = obtainViewModel(TaskDetailViewModel::class.java)
}