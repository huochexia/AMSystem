package com.owner.assertsparam.view.adapter

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.owner.assertsparam.data.CategoryInfo
import com.owner.assertsparam.databinding.LayoutSecondCategoryItemBinding
import com.owner.assertsparam.viewmodel.CategoryFgViewModel
import com.owner.assertsparam.viewmodel.ThirdCgMoreView

/**
 * 二级分类适配器
 * @topCategory:一级分类，通过它的Id，筛选它的子类
 * Created by Administrator on 2018/10/25 0025
 */
class SecondCgAdapter(private val topCategory: CategoryInfo, val mCategoryVM: CategoryFgViewModel)
    : RecyclerView.Adapter<SecondCgAdapter.SecondViewHolder>() {

    private val secondCgList = mutableListOf<CategoryInfo>()

    init {
       updateList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, ViewType: Int): SecondViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return  SecondViewHolder.create(inflater, parent)
    }

    override fun getItemCount(): Int = secondCgList.size

    override fun onBindViewHolder(holder: SecondViewHolder, position: Int) {
        val category = secondCgList[position]
        holder.bindData(category, mCategoryVM)
    }

    /**
     * 因为传入Adapter的参数是ViewModel，而不是数据列表，对ViewModel中数据列表的变更不会影响到
     * Adapter中的列表，所以需要对外公开一个更新列表的方法
     */
    fun updateList() {
        secondCgList.clear()
        secondCgList.addAll(mCategoryVM.secondAndThirdCgList.filter {
            it.parentId==topCategory.objectId
        })
    }
    /**
     * ViewHolder 嵌套类
     */
    class SecondViewHolder(val context: Context, val mBinding: LayoutSecondCategoryItemBinding)
        : RecyclerView.ViewHolder(mBinding.root) {

        lateinit var moreView:ThirdCgMoreView
        lateinit var thirdCgAdapter: ThirdCgAdapter
        companion object {
            fun create(inflater: LayoutInflater, parent: ViewGroup): SecondViewHolder {
                val binding = LayoutSecondCategoryItemBinding.inflate(inflater, parent, false)
                return SecondViewHolder(parent.context, binding)
            }
        }

        fun bindData(category: CategoryInfo, viewModel: CategoryFgViewModel) {
            mBinding.category = category
            mBinding.categoryVM = viewModel
            mBinding.executePendingBindings()
            setVisibleEditLL(category.isLongOnClick)
            setThirdCgList(category, viewModel)
        }

        /**
         * 构造三级分类的适配器
         */
        private fun setThirdCgList(category: CategoryInfo, viewModel: CategoryFgViewModel) {
            val glm = GridLayoutManager(context, 3)
            mBinding.mThirdCategoryRv.layoutManager = glm
            //为每个item构建一个
            if (mBinding.thirdCgMore == null) {
                moreView = ThirdCgMoreView()
                mBinding.thirdCgMore = moreView
            }
            thirdCgAdapter = ThirdCgAdapter(category, viewModel,moreView)
            mBinding.mThirdCategoryRv.adapter = thirdCgAdapter
        }

        /**
            设置编辑界面的可见性
         */
        private fun setVisibleEditLL(visible: Boolean) {
            if (visible) {
                mBinding.mEditSecondCgll.visibility = View.VISIBLE
            } else {
                mBinding.mEditSecondCgll.visibility = View.GONE
            }
        }
    }
}