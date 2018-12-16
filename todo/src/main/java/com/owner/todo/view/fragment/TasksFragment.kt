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
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import android.widget.PopupMenu
import com.owner.baselibrary.ext.setupSnacker
import com.owner.todo.R
import com.owner.todo.common.TasksFilterType
import com.owner.todo.databinding.FragmentTaskBinding
import com.owner.todo.view.activity.TasksActivity
import com.owner.todo.view.adapter.TaskRecyclerAdapter
import kotlinx.android.synthetic.main.activity_task.*
import kotlinx.android.synthetic.main.fragment_task.*

/**
 *
 * Created by Liuyong on 2018-12-09.It's AMSystem
 *@description:
 */
class TasksFragment : Fragment() {

    private lateinit var mBinding: FragmentTaskBinding
    private lateinit var adapter:TaskRecyclerAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentTaskBinding.inflate(inflater, container, false).apply {
            viewmodel = (activity as TasksActivity).obtainViewModel()
        }
        setHasOptionsMenu(true)
        return mBinding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mBinding.viewmodel?.let {
            view?.setupSnacker(this,it.snackbarMessage,Snackbar.LENGTH_LONG)
        }
        setupFab()

        mTasksList.layoutManager=LinearLayoutManager(context)
        adapter = TaskRecyclerAdapter(mBinding.viewmodel!!)
        mTasksList.adapter = adapter

        setupRefreshLayout()
    }

    /**
     * 说明：start()是加载数据的方法，onResume是Fragment的生命周期方法。当从增加或编辑界面返回主界面
     * 时，Fragment重新建立时，都会调用这个方法。这样得到新的数据。在start()方法中，数据是添加到items
     * 这个可观察变量中的，它绑定在列表视图上，所以它会及时通知视图刷新列表。
     */
    override fun onResume() {
        super.onResume()
        mBinding.viewmodel?.start()

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_task_fragment, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
            when (item.itemId) {
                R.id.mFilterMenuItem -> {
                    showFilteringPopUpMenu()
                    true
                }
                R.id.mClearMenuItem -> {
                    mBinding.viewmodel?.clearCompleteTasks()
                    true
                }
                R.id.mRefreshMenuItem -> {
                    mBinding.viewmodel?.loadTasks(true)
                    true
                }
                else -> false
            }

    private fun showFilteringPopUpMenu() {
        PopupMenu(context,activity?.findViewById<View>(R.id.mFilterMenuItem)).run {

            menuInflater.inflate(R.menu.filter_tasks,menu)

            setOnMenuItemClickListener {
                mBinding.viewmodel?.run {
                    currentFiltering =
                            when (it.itemId) {
                                R.id.completed ->TasksFilterType.COMPLETED_TASKS
                                R.id.active -> TasksFilterType.ACTIVE_TASKS
                                else -> TasksFilterType.ALL_TASKS
                            }
//                    loadTasks(false)
                    filterTasks()
                }
                true
            }
            show()

        }
    }

    private fun setupFab() {
        activity!!.findViewById<FloatingActionButton>(R.id.mFabAddTask).run {
            setOnClickListener {
                mBinding.viewmodel?.addNewTask()
            }
        }
    }

    /**
     * 刷新动画的变化颜色
     */
    private fun setupRefreshLayout() {
        mBinding.mRefreshLayout.run {
            setColorSchemeColors(
                    ContextCompat.getColor(context,R.color.refreshColorPrimary),
                    ContextCompat.getColor(context,R.color.refreshColorAccent),
                    ContextCompat.getColor(context,R.color.colorPrimaryDark)

            )
            scrollUpChild = mBinding.mTasksList
        }
    }
    companion object {
        fun newInstance() = TasksFragment()
    }


}