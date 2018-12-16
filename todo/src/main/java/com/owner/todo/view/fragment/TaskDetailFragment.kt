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
import android.support.v4.content.ContextCompat
import android.view.*
import com.owner.baselibrary.ext.setupSnacker
import com.owner.todo.R
import com.owner.todo.databinding.FragmentTaskDetailBinding
import com.owner.todo.view.activity.TaskDetailActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *
 * Created by Liuyong on 2018-12-14.It's AMSystem
 *@description:
 */
class TaskDetailFragment : Fragment() {

    private lateinit var mBinding: FragmentTaskDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mBinding = FragmentTaskDetailBinding.inflate(inflater, container, false).apply {
            viewmodel = (activity as TaskDetailActivity).obtainViewModel()

        }
        setHasOptionsMenu(true)

        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupFab()

        mBinding.viewmodel?.let {
            view?.setupSnacker(this, it.snackbarMessage, Snackbar.LENGTH_LONG)

        }
        setupRefreshLayout()
    }

    private fun setupFab() {
        activity?.findViewById<FloatingActionButton>(R.id.mEditTaskFAB)!!.setOnClickListener {
            mBinding.viewmodel?.editTask()
        }
    }

    override fun onResume() {
        super.onResume()
        mBinding.viewmodel?.start(arguments!!.getString(ARGUMENT_TASK_ID))!!
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }

    /**
     * 选项菜单
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_task_detail_fragment, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_delete -> {
                mBinding.viewmodel?.deleteTask()
                return true
            }
            else -> return false
        }
    }

    /**
     * 刷新动画的变化颜色
     */
    private fun setupRefreshLayout() {
        mBinding.mRefreshLayout.run {
            setColorSchemeColors(
                    ContextCompat.getColor(context, R.color.refreshColorPrimary),
                    ContextCompat.getColor(context, R.color.refreshColorAccent),
                    ContextCompat.getColor(context, R.color.colorPrimaryDark)

            )
        }
    }

    companion object {

        const val ARGUMENT_TASK_ID = "TASK_ID"
        const val REQUEST_EDIT_TASK = 1

        fun newInstance(taskId: String) = TaskDetailFragment().apply {
            arguments = Bundle().apply {
                putString(ARGUMENT_TASK_ID, taskId)
            }
        }
    }
}