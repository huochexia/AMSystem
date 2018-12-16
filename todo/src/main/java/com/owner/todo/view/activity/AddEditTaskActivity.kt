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
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.owner.baselibrary.ext.addFragment
import com.owner.baselibrary.ext.setupToolBar
import com.owner.todo.R
import com.owner.todo.databinding.ActivityAddOrEditTaskBinding
import com.owner.todo.ext.obtainViewModel
import com.owner.todo.util.ADD_EDIT_RESULT_OK
import com.owner.todo.view.Interface.AddEditTaskNavigator
import com.owner.todo.view.fragment.AddEditTaskFragment
import com.owner.todo.viewmodel.AddEditTaskViewModel

/**
 *
 * Created by Liuyong on 2018-12-11.It's AMSystem
 *@description:
 */
class AddEditTaskActivity : AppCompatActivity(), AddEditTaskNavigator {

    private lateinit var mBinding: ActivityAddOrEditTaskBinding
    /*
       在使用toolbar的情况下，onBackPressed可以使系统自带的后退按钮，
       onSupportNavigateUp可以使用toolbar的后退按钮后退
     */
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onTaskSaved() {
        setResult(ADD_EDIT_RESULT_OK)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_or_edit_task)

        mBinding.viewmodel = obtainViewModel()

        setupToolBar(R.id.mToolbar) {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)//显示Toolbar的回退键
        }

        addFragment(obtainViewFragment(),R.id.mFragmentContainer)

        subscribeToNavigationChanges()
    }

    override fun onDestroy() {
        mBinding.viewmodel?.compositeDisposable?.clear()
        super.onDestroy()
    }
    private fun obtainViewFragment()= supportFragmentManager.findFragmentById(R.id.mFragmentContainer) ?:
            AddEditTaskFragment.newInstance().apply {
                arguments = Bundle().apply {
                    putString(AddEditTaskFragment.ARGUMENT_EDIT_TASK_ID,
                            intent.getStringExtra(AddEditTaskFragment.ARGUMENT_EDIT_TASK_ID))
                }
            }

    private fun subscribeToNavigationChanges() {
        obtainViewModel().taskUpdateEvent.observe(this, Observer {
            this@AddEditTaskActivity.onTaskSaved()
        })
    }

    fun obtainViewModel(): AddEditTaskViewModel = obtainViewModel(AddEditTaskViewModel::class.java)

    companion object {
        const val REQUEST_CODE = 1
    }
}