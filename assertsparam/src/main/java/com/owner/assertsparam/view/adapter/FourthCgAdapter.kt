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

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.owner.assertsparam.BR
import com.owner.assertsparam.R
import com.owner.assertsparam.data.CategoryInfo
import com.owner.assertsparam.data.Footer
import com.owner.assertsparam.databinding.LayoutFourCategoryItemBinding
import com.owner.assertsparam.viewmodel.FourthCategoryViewModel

/**
 *
 * Created by Liuyong on 2018-11-18.It's AMSystem
 *@description:
 */
class FourthCgAdapter (private val thirdCategory:CategoryInfo, private val mVM:FourthCategoryViewModel)
    :RecyclerView.Adapter<FourthCgAdapter.FourthViewHolder>(){

    private var fourCgList = mutableListOf<Any>()

    fun updateList() {
        fourCgList.clear()
        fourCgList.addAll(mVM.fourthList)
        fourCgList.add(Footer(thirdCategory))
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FourthViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FourthViewHolder.create(inflater,parent,viewType)
    }

    override fun getItemCount(): Int = fourCgList.size

    override fun onBindViewHolder(holder: FourthViewHolder, position: Int) {
        holder.bindData(fourCgList[position],mVM)
    }

    override fun getItemViewType(position: Int): Int {
        when (fourCgList[position]) {
            is CategoryInfo->return R.layout.layout_four_category_item
            is Footer -> return R.layout.layout_four_category_footer
        }
        throw RuntimeException("invalid object")
    }

    class FourthViewHolder(private val mBinding: ViewDataBinding) : RecyclerView.ViewHolder(mBinding.root) {
        companion object {
            fun create(inflater: LayoutInflater, parent: ViewGroup, viewType: Int):FourthViewHolder {
                val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater,
                        viewType,parent,false)
                return  FourthViewHolder(binding)
            }
        }

        fun bindData(data: Any, viewModel: FourthCategoryViewModel) {
            if (data is CategoryInfo) {
                mBinding.setVariable(BR.four, data)
                setVisibleEditLL(data)
            } else {
                mBinding.setVariable(BR.four,data)
            }
            mBinding.setVariable(BR.fourthVM,viewModel)
            mBinding.executePendingBindings()
        }
        /*
          设置编辑界面的可见性
         */
        private fun setVisibleEditLL(data: CategoryInfo) {
            if (mBinding is LayoutFourCategoryItemBinding) {
                mBinding.mFourCgll.isSelected = data.isSelected
                if (data.isLongOnClick) {
                    mBinding.mEditFourCgll.visibility = View.VISIBLE
                } else {
                    mBinding.mEditFourCgll.visibility = View.GONE
                }
            }
        }
    }
}