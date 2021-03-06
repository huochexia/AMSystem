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
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.alibaba.android.arouter.facade.annotation.Route
import com.owner.baselibrary.ext.addFragment
import com.owner.baselibrary.ext.setupToolBar
import com.owner.baselibrary.ext.showSnackbar
import com.owner.provideslib.router.RouterPath
import com.owner.todo.R
import com.owner.todo.view.fragment.TasksFragment
import com.owner.todo.viewmodel.TaskViewModel
import com.owner.todo.ext.obtainViewModel
import com.owner.todo.view.Interface.TaskItemNavigator
import com.owner.todo.view.Interface.TasksNavigator
import org.jetbrains.anko.startActivity

/**
 *
 * Created by Liuyong on 2018-12-08.It's AMSystem
 *@description:
 */
@Route(path = RouterPath.Todo.PATH_TODO_TASK)
class TasksActivity : AppCompatActivity(), TaskItemNavigator, TasksNavigator {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var viewModel: TaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)
        setupToolBar(R.id.mToolbar) {
            setHomeAsUpIndicator(R.drawable.ic_menu)
            setDisplayHomeAsUpEnabled(true)
        }

        setupNavigationDrawer()

        setupFragment()

        viewModel = obtainViewModel().apply {

            openTaskEvent.observe(this@TasksActivity, Observer { taskId ->
                if (taskId != null){
                    openTaskDetails(taskId)
                }
            })

            newTaskEvent.observe(this@TasksActivity, Observer {
                this@TasksActivity.addNewTask()
            })
        }

    }
    /**
     * 启动编辑任务详情界面
     */
    override fun openTaskDetails(taskId: String) {
        val intent = Intent(this,TaskDetailActivity::class.java).apply {
            putExtra(TaskDetailActivity.EXTRA_TASK_ID,taskId)
        }
        startActivityForResult(intent,AddEditTaskActivity.REQUEST_CODE)
    }

    /**
     * 启动增加任务界面
     */
    override fun addNewTask() {
        val intent = Intent(this,AddEditTaskActivity::class.java)
        startActivityForResult(intent,AddEditTaskActivity.REQUEST_CODE)
    }

    private fun setupFragment() {
        TasksFragment.newInstance()
                .let {
                    addFragment(it, R.id.mContentFrame)
                }

    }

    private fun setupNavigationDrawer() {

        drawerLayout = findViewById<DrawerLayout>(R.id.mDrawerLayout).apply {
            setStatusBarBackground(R.color.colorPrimaryDark)
        }
        setupDrawerContent(findViewById(R.id.mNavView))
    }

    private fun setupDrawerContent(navigationView: NavigationView) {
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.mListNavMenuItem -> {

                }
                R.id.mStatisticsNavMenuItem -> {
                    val intent = Intent(this,StatisticsActivity::class.java).apply {
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    }
                    startActivity(intent)
                }
                R.id.mFinishMenuItem -> {
                    finish()
                }
            }
            menuItem.isChecked = true
            drawerLayout.closeDrawers()
            true
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        viewModel.handleActivityResult(requestCode,resultCode)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
            when (item.itemId) {
                android.R.id.home -> {
                    drawerLayout.openDrawer(GravityCompat.START)
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }

    override fun onDestroy() {
        viewModel.compositeDisposable.clear()
        super.onDestroy()
    }
    fun obtainViewModel(): TaskViewModel = obtainViewModel(TaskViewModel::class.java)
}