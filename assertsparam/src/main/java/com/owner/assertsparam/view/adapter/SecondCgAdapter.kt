package com.owner.assertsparam.view.adapter

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.owner.assertsparam.data.CategoryInfo
import com.owner.assertsparam.databinding.LayoutSecondCategoryItemBinding
import com.owner.assertsparam.viewmodel.CategoryFgViewModel

/**
 * 二级分类适配器
 * Created by Administrator on 2018/10/25 0025
 */
class SecondCgAdapter(private val topCategory: CategoryInfo, private val mCategoryVM: CategoryFgViewModel) : RecyclerView.Adapter<SecondCgAdapter.SecondViewHolder>() {

    private val secondCgList = mutableListOf<CategoryInfo>()
    init {
        secondCgList.clear()
        mCategoryVM.secondAndThirdCgList.forEach {
            if (it.parentId == topCategory.id) {
                secondCgList.add(it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, ViewType: Int): SecondViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SecondViewHolder.create(inflater, parent)
    }

    override fun getItemCount(): Int = secondCgList.size

    override fun onBindViewHolder(holder: SecondViewHolder, position: Int) {
        val category = secondCgList[position]
        holder.bindData(category,mCategoryVM)
    }

    class SecondViewHolder(val mBinding: LayoutSecondCategoryItemBinding)
        : RecyclerView.ViewHolder(mBinding.root) {

        companion object {
            lateinit var context: Context
            fun create(inflater: LayoutInflater, parent: ViewGroup): SecondViewHolder {
                val binding = LayoutSecondCategoryItemBinding.inflate(inflater, parent, false)
                 context = parent.context
                return SecondViewHolder(binding)
            }
        }

        fun bindData(category: CategoryInfo, viewModel: CategoryFgViewModel) {
            mBinding.category = category
            mBinding.categoryVM = viewModel
            mBinding.executePendingBindings()
            setThirdCgList(category,viewModel)
        }

        fun setThirdCgList(category: CategoryInfo,viewModel: CategoryFgViewModel) {
            val ll = GridLayoutManager(SecondViewHolder.context,3)
            mBinding.mThirdCategoryRv.layoutManager = ll
            mBinding.mThirdCategoryRv.adapter = ThirdCgAdapter(category,viewModel)
        }
    }
}