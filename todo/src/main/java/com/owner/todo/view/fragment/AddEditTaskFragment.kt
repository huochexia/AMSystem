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
package com.owner.todo.view.fragment

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.owner.baselibrary.ext.setupSnacker
import com.owner.todo.R
import com.owner.todo.databinding.FragmentAddEditTaskBinding
import com.owner.todo.view.activity.AddEditTaskActivity
import kotlinx.android.synthetic.main.activity_task.view.*

/**
 *
 * Created by Liuyong on 2018-12-11.It's AMSystem
 *@description:
 */
class AddEditTaskFragment :Fragment(){

    private lateinit var mBinding :FragmentAddEditTaskBinding

    companion object {
        const val ARGUMENT_EDIT_TASK_ID = "EDIT_TASK_ID"

        fun newInstance() = AddEditTaskFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupFab()
        mBinding.viewmodel?.let {
            view?.setupSnacker(this,it.snackbarMessage,Snackbar.LENGTH_LONG)
        }
        setupActionBar()
        loadData()
    }

    /**
     * 改变activity中浮动按钮的图样，并添加点按事件
     */
    private fun setupFab() {
        activity?.findViewById<FloatingActionButton>(R.id.mFabAddEditTask).apply {
            this!!.setImageResource(R.drawable.ic_done)
            setOnClickListener{mBinding.viewmodel?.saveTask()}
        }
    }

    /**
     * 根据传入Fragment参数设置activity的工具栏标题内容
     */
    private fun setupActionBar() {
        (activity as AppCompatActivity).supportActionBar?.setTitle(
                if (arguments != null && arguments!!.get(ARGUMENT_EDIT_TASK_ID) != null) {
                    R.string.edit_task
                } else {
                    R.string.add_task
                }
        )
    }
    private fun loadData() {
        mBinding.viewmodel?.start(arguments?.getString(ARGUMENT_EDIT_TASK_ID))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentAddEditTaskBinding.inflate(inflater,container,false).apply {
            viewmodel = (activity as AddEditTaskActivity).obtainViewModel()
        }
        setHasOptionsMenu(true)
        retainInstance = false//非中断保存，为true时Fragment不会被销毁
        return  mBinding.root
    }


}