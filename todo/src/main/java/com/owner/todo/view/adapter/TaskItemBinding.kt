package com.owner.todo.view.adapter

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import com.owner.todo.data.Task

/**
 * Created by Administrator on 2018/12/13 0013
 */
object TaskItemBinding {
    @BindingAdapter("app:items")
    @JvmStatic
    fun setItems(view: RecyclerView, items: List<Task>) {

        with(view.adapter as TaskRecyclerAdapter) {
            setList(items)
        }
    }
}