package com.owner.assertsparam.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.owner.assertsparam.data.CategoryInfo
import com.owner.assertsparam.databinding.LayoutThirdCategoryItemBinding
import com.owner.assertsparam.viewmodel.CategoryFgViewModel

/**
 * 三级分类适配器
 * Created by Administrator on 2018/10/25 0025
 */
class ThirdCgAdapter(private val secondCategory: CategoryInfo, private val mCategoryVM: CategoryFgViewModel) : RecyclerView.Adapter<ThirdCgAdapter.ThirdViewHolder>() {

    private  var thirdCgList = mutableListOf<CategoryInfo>()
    //筛选三级类别
    init {
        thirdCgList.clear()
        mCategoryVM.secondAndThirdCgList.forEach {
            if (it.parentId == secondCategory.id)
                thirdCgList.add(it)
        }
    }

    override fun getItemCount(): Int = thirdCgList.size

    override fun onBindViewHolder(holder: ThirdViewHolder, position: Int) {
        val category = thirdCgList[position]
        holder.bindData(category, mCategoryVM)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThirdViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ThirdViewHolder.create(inflater, parent)
    }

    class ThirdViewHolder(val mBinding: LayoutThirdCategoryItemBinding) : RecyclerView.ViewHolder(mBinding.root) {

        companion object {
            fun create(inflater: LayoutInflater, parent: ViewGroup): ThirdViewHolder {
                val binding = LayoutThirdCategoryItemBinding.inflate(inflater, parent, false)
                return ThirdViewHolder(binding)
            }
        }

        fun bindData(category: CategoryInfo, viewModel: CategoryFgViewModel) {
            mBinding.category = category
            mBinding.categoryFgVM = viewModel
            mBinding.executePendingBindings()
        }
    }
}