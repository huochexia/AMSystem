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
package com.owner.baselibrary.rvbinding.adapter.binder

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import com.owner.baselibrary.BR
import com.owner.baselibrary.rvbinding.function.BiFunction
import com.owner.baselibrary.rvbinding.adapter.holder.DataBindingViewHolder
import me.drakeet.multitype.ItemViewBinder

/**
 * RecyclerView.Adapter的模板类，继承ItemViewBinder抽象类。[T]为列表条目类
 * Created by Liuyong on 2018-10-07.It's MVVMKotlinMall
 *@description:
 */
open class DataBindingItemViewBinder<T, DB : ViewDataBinding>(private val delegate: Delegate<T, DB>)
    : ItemViewBinder<T, DataBindingViewHolder<DB>>() {

    /**
     * 实现Adapter的方法，
     */
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): DataBindingViewHolder<DB> {
        return DataBindingViewHolder(delegate.onCreateDataBinding(inflater, parent))
    }

    /**
     * 实现Adapter方法
     */
    override fun onBindViewHolder(holder: DataBindingViewHolder<DB>, item: T) {
       val binding = holder.binding
        binding.setVariable(BR.data,item)
        //这是为了绑定外部方法，实现比如点击某项条目事件等
        delegate.onBind(binding,item,holder.adapterPosition)
        binding.executePendingBindings()
    }

    /**
     * 二级构造方法
     */
    constructor(factory: BiFunction<LayoutInflater, ViewGroup, DB>, binder: OnBindItem<T, DB>?)
            : this(SimpeDelegate(factory, binder))

    /**
     * 关键的构造方法，通过传入布局文件，调用DATaBindingUtil的inflater方法
     */
    constructor(resId: Int, binder: OnBindItem<T, DB>?) : this(
            object : BiFunction<LayoutInflater, ViewGroup, DB> {
                override fun apply(inflater: LayoutInflater, parent: ViewGroup): DB {
                    return DataBindingUtil.inflate(inflater, resId, parent, false)
                }
            }, binder)

    interface Delegate<T, DB : ViewDataBinding> {
        fun onCreateDataBinding(inflater: LayoutInflater, parent: ViewGroup): DB

        fun onBind(dataBinding: DB, item: T, position: Int)

    }

    interface OnBindItem<T, DB : ViewDataBinding> {

        fun bind(dataBinding: DB, data: T, position: Int)
    }

    companion object {
        class SimpeDelegate<T, DB : ViewDataBinding>(private val factory: BiFunction<LayoutInflater, ViewGroup, DB>,
                                                     private val binder: OnBindItem<T, DB>?) : Delegate<T, DB> {


            override fun onBind(dataBinding: DB, item: T, position: Int) {
                binder?.bind(dataBinding, item, position)
            }

            override fun onCreateDataBinding(inflater: LayoutInflater, parent: ViewGroup): DB {
                return factory.apply(inflater, parent)
            }

        }
    }
}