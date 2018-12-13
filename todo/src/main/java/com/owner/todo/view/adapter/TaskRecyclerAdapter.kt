package com.owner.todo.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.owner.todo.data.Task
import com.owner.todo.databinding.TaskListItemBinding
import com.owner.todo.viewmodel.TaskViewModel

/**
 * Created by Administrator on 2018/12/13 0013
 */
class TaskRecyclerAdapter(val viewModel: TaskViewModel) : RecyclerView.Adapter<TaskRecyclerAdapter.TaskViewHolder>() {

    private var tasks: List<Task> = arrayListOf()

    fun setList(tasks: List<Task>) {
        this.tasks =tasks
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder.create(parent, viewType)
    }

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bindData(tasks[position], viewModel)
    }


    class TaskViewHolder(val mBinding: TaskListItemBinding) : RecyclerView.ViewHolder(mBinding.root) {

        companion object {
            fun create(parent: ViewGroup, viewType: Int): TaskViewHolder {
                val inflate = LayoutInflater.from(parent.context)
                val binding = TaskListItemBinding.inflate(inflate, parent, false)
                return TaskViewHolder(binding)
            }
        }

        fun bindData(task: Task, viewmodel: TaskViewModel) {
            mBinding.task = task
            mBinding.viewmodel = viewmodel
            mBinding.executePendingBindings()
        }
    }
}