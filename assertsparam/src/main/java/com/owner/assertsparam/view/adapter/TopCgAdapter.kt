package com.owner.assertsparam.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.owner.assertsparam.data.CategoryInfo
import com.owner.assertsparam.databinding.LayoutTopCategoryItemBinding
import com.owner.assertsparam.viewmodel.CategoryFgViewModel

/**
 * Adapter,除了传入数据列表以外，还需要传入CategoryFgViewModel对象，因为项目布局中要引入该对象。布局
 * 中引入这个对象是为了实现点击项目时视图响应该点击事件。点击事件处理项目选中后的变化
 * Created by Administrator on 2018/10/22 0022
 */
class TopCgAdapter( private val mCategoryVM:CategoryFgViewModel )
    : RecyclerView.Adapter<TopCgAdapter.TopCgViewHolder>() {

    private val dataList = mCategoryVM.topCgList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopCgViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TopCgViewHolder.create(inflater, parent)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: TopCgViewHolder, position: Int) {
        val categoryItem = dataList[position]
        holder.bindToData(categoryItem,mCategoryVM)
    }

    /**
     * ViewHolder
     */
    class TopCgViewHolder(private val mBinding: LayoutTopCategoryItemBinding) : RecyclerView.ViewHolder(mBinding.root) {

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
        fun bindToData(topCategory: CategoryInfo,categoryFgViewModel: CategoryFgViewModel) {
            //绑定CategoryInfo对象
            mBinding.category= topCategory
            //设置TextView当前状态
            mBinding.mTopCategoryNameTv.isSelected = topCategory.isSelected
            //绑定CategoryFgViewModel对象
            mBinding.categoryVM = categoryFgViewModel
            //必须执行此方法
            mBinding.executePendingBindings()
        }
    }
}

