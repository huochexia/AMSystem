package com.owner.assertsparam.view.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.owner.assertsparam.BR
import com.owner.assertsparam.R
import com.owner.assertsparam.data.CategoryInfo
import com.owner.assertsparam.data.Footer
import com.owner.assertsparam.databinding.LayoutThirdCategoryItemBinding
import com.owner.assertsparam.viewmodel.CategoryFgViewModel
import java.lang.Exception
import java.lang.RuntimeException

/**
 * 三级分类适配器
 * @secondCategory:二级分类，通过它的Id从列表中筛选出它的子分类
 * Created by Administrator on 2018/10/25 0025
 */
class ThirdCgAdapter(private val secondCategory: CategoryInfo, private val mCategoryVM: CategoryFgViewModel)
    : RecyclerView.Adapter<ThirdCgAdapter.ThirdViewHolder>() {

    private  var thirdCgList = mutableListOf<Any>()
    //筛选三级类别
    init {
        thirdCgList.clear()
        mCategoryVM.secondAndThirdCgList.forEach {
            if (it.parentId == secondCategory.id)
                thirdCgList.add(it)
        }
        thirdCgList.add(thirdCgList.size,Footer(secondCategory))
    }

    override fun getItemCount(): Int = thirdCgList.size

    override fun onBindViewHolder(holder: ThirdViewHolder, position: Int) {
        val category = thirdCgList[position]
        holder.bindData(category, mCategoryVM)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThirdViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ThirdViewHolder.create(inflater, parent,viewType)
    }

    override fun getItemViewType(position: Int): Int {
        val item = thirdCgList[position]
        when (item) {
            is CategoryInfo -> return R.layout.layout_third_category_item
            is Footer -> return  R.layout.layout_third_category_footer
        }
        throw RuntimeException("invalid object")
    }
    /**
     * ViewHolder
     */
    class ThirdViewHolder(val mBinding: ViewDataBinding) : RecyclerView.ViewHolder(mBinding.root) {

        companion object {
            fun create(inflater: LayoutInflater, parent: ViewGroup,viewType: Int): ThirdViewHolder {
                val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater,viewType,parent,false)
                return ThirdViewHolder(binding)
            }
        }

        fun bindData(data: Any, viewModel: CategoryFgViewModel) {
            if (data is CategoryInfo) {
               mBinding.setVariable(BR.data,data)
            }
            if (data is Footer) {
                mBinding.setVariable(BR.data,data)
            }
            mBinding.setVariable(BR.categoryFgVM, viewModel)
            mBinding.executePendingBindings()
        }
    }
}