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
import com.owner.assertsparam.viewmodel.CategoryFgViewModel
import com.owner.assertsparam.viewmodel.ThirdCgMoreView

/**
 * 三级分类适配器
 * @secondCategory:二级分类，通过它的Id从列表中筛选出它的子分类
 * Created by Administrator on 2018/10/25 0025
 */
class ThirdCgAdapter(private val secondCategory: CategoryInfo, private val mCategoryVM: CategoryFgViewModel,
                     private val thirdCgMore: ThirdCgMoreView)
    : RecyclerView.Adapter<ThirdCgAdapter.ThirdViewHolder>() {

    private var thirdCgAllList = mutableListOf<Any>()
    private var thirdCgSubList = mutableListOf<Any>()

    //筛选三级类别
    init {
        //1、清空列表防止重复，筛选出三级分类
        thirdCgAllList.clear()
        mCategoryVM.secondAndThirdCgList.forEach {
            if (it.parentId == secondCategory.id)
                thirdCgAllList.add(it)
        }
        //将Footer对象加入列表
        thirdCgAllList.add(thirdCgAllList
                .size, Footer(secondCategory))
        //2、如果全部内容列表大于4（即四个分类一个Footer），收缩内容。
        if (thirdCgAllList.size > 4) {
            showSubList()
        } else {
            showAllList()
        }

    }

    override fun getItemCount(): Int = thirdCgSubList.size

    override fun onBindViewHolder(holder: ThirdViewHolder, position: Int) {
        val category = thirdCgSubList[position]
        holder.bindData(category, mCategoryVM)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThirdViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ThirdViewHolder.create(inflater, parent, viewType)
    }

    override fun getItemViewType(position: Int): Int {
        val item = thirdCgSubList[position]
        when (item) {
            is CategoryInfo -> return R.layout.layout_third_category_item
            is Footer -> return R.layout.layout_third_category_footer
        }
        throw RuntimeException("invalid object")
    }

    /**
     * 显示全部内容
     */
    fun showAllList() {
        thirdCgMore.isMore = false
        thirdCgSubList.clear()
        thirdCgSubList.addAll(thirdCgAllList)
        notifyDataSetChanged()
    }

    /**
     * 显示部分内容
     */
    fun showSubList() {
        thirdCgMore.isMore = true
        thirdCgSubList.clear()
        thirdCgSubList = thirdCgAllList.subList(0, 3)
        notifyDataSetChanged()
    }

    /**
     * ViewHolder
     */
    class ThirdViewHolder(val mBinding: ViewDataBinding) : RecyclerView.ViewHolder(mBinding.root) {

        companion object {
            fun create(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): ThirdViewHolder {
                val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, viewType, parent, false)
                return ThirdViewHolder(binding)
            }
        }

        fun bindData(data: Any, viewModel: CategoryFgViewModel) {
            if (data is CategoryInfo) {
                mBinding.setVariable(BR.data, data)
            }
            if (data is Footer) {
                mBinding.setVariable(BR.data, data)
            }
            mBinding.setVariable(BR.categoryFgVM, viewModel)
            mBinding.executePendingBindings()
        }
    }
}