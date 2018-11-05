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
package com.owner.assertsparam.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.owner.assertsparam.data.Manager
import com.owner.assertsparam.databinding.LayoutManagerItemBinding
import com.owner.assertsparam.viewmodel.ManagerViewModel

/**
 *
 * Created by Liuyong on 2018-10-30.It's AMSystem
 *@description:
 */
class ManagerAdapter(private val mManagerVM: ManagerViewModel) : RecyclerView.Adapter<ManagerAdapter.ManagerViewHolder>() {

    private var managerList = mutableListOf<Manager>()

    init {
       updateList()
    }

    fun updateList() {
        managerList.clear()
        managerList.addAll(mManagerVM.getSortList())
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManagerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ManagerViewHolder.create(inflater, parent)
    }

    override fun getItemCount(): Int = managerList.size

    override fun onBindViewHolder(holder: ManagerViewHolder, position: Int) {
        holder.bindData(managerList[position], mManagerVM)
    }



    /**
     * 根据当前位置获取分类的首字母的char ascii值
     */
    fun getSectionForPosition(position: Int): Int {
        return managerList[position].letters.toCharArray()[0].toInt()
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    fun getPositionForSection(section: Int): Int {
        for (i in 0 until itemCount) {
            val sortStr = managerList[i].letters
            val firstChar = sortStr.toUpperCase()[0]
            if (firstChar.toInt() == section) {
                return i
            }
        }
        return -1
    }
    /**
     * ViewHolder
     */
    class ManagerViewHolder(val mBinding: LayoutManagerItemBinding) : RecyclerView.ViewHolder(mBinding.root) {
        companion object {
            fun create(inflater: LayoutInflater, parent: ViewGroup): ManagerViewHolder {
                val binding = LayoutManagerItemBinding.inflate(inflater, parent, false)
                return ManagerViewHolder(binding)
            }
        }

        fun bindData( manager:Manager,vm: ManagerViewModel) {
            mBinding.manager = manager
            mBinding.managerVM = vm
        }
    }
}