package com.owner.assertsparam.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.owner.assertsparam.data.CategoryInfo
import com.owner.assertsparam.databinding.LayoutTopCategoryItemBinding
import com.owner.assertsparam.viewmodel.CategoryFgViewModel
import com.owner.assertsparam.viewmodel.TopCgItemViewModel

/**
 * Adapter
 * Created by Administrator on 2018/10/22 0022
 */
class TopCgAdapter(var topCgList: MutableList<CategoryInfo>) : RecyclerView.Adapter<TopCgAdapter.TopCgViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopCgViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TopCgViewHolder.create(inflater, parent)
    }

    override fun getItemCount(): Int = topCgList.size

    override fun onBindViewHolder(holder: TopCgViewHolder, position: Int) {
        val categoryItem = topCgList[position]
        holder.bindToData(categoryItem)
    }

    /**
     * ViewHolder
     */
    class TopCgViewHolder(val mBinding: LayoutTopCategoryItemBinding) : RecyclerView.ViewHolder(mBinding.root) {

        companion object {
            /*
              通过项目布局文件对应的databinding类生成binding,当然也可以使用DataBindingUtil类生成binding
             */
            fun create(inflater: LayoutInflater, parent: ViewGroup): TopCgViewHolder {
                val binding = LayoutTopCategoryItemBinding.inflate(inflater, parent, false)
                return TopCgViewHolder(binding)
            }
        }

        /*
          与数据源绑定
         */
        fun bindToData(topCategory: CategoryInfo) {
            if (mBinding.categoryVM == null)
                mBinding.categoryVM = CategoryFgViewModel()
            if (mBinding.topCateVM == null) {
                mBinding.topCateVM = TopCgItemViewModel(topCategory)
            } else {
                mBinding.topCateVM?.info = topCategory
            }
            mBinding.mTopCategoryNameTv.isSelected = topCategory.isSelected
            mBinding.executePendingBindings()
        }
    }
}

