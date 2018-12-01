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
package com.owner.assetsparam.view.activity

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.owner.assetsparam.R
import com.owner.assetsparam.data.CategoryInfo
import com.owner.assetsparam.databinding.ActivityRegisterAssetArgBinding
import com.owner.assetsparam.view.fragment.AssetInfoFragment
import com.owner.assetsparam.view.fragment.CategoryFragment
import com.owner.assetsparam.view.fragment.ManagerFragment
import com.owner.assetsparam.viewmodel.ShareAssetViewModel
import com.owner.baselibrary.ext.addFragment
import com.owner.baselibrary.ext.hideFragment
import com.owner.baselibrary.ext.showFragment
import com.owner.baselibrary.view.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_register_asset_arg.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.toast
import java.util.*

/**
 *  登记资产的参数，必选项类别和管理人
 * Created by Liuyong on 2018-11-14.It's AMSystem
 *@description:
 */
class RegisterAssetActivity : BaseActivity<ActivityRegisterAssetArgBinding, ShareAssetViewModel>() {



    //Fragment 栈管理
    private val mStack = Stack<Fragment>()
    private val titles = arrayOf("资产", "存放地点", "管理人")
    private var currentPosition = 0
    private val locationFragment by lazy { CategoryFragment.newInstance("Location", false, false) }
    private val categoryFragment by lazy { CategoryFragment.newInstance("Category", false, false) }
    private val managerFragment by lazy { ManagerFragment.newInstance(false, false) }
    private val assetFragment by lazy { AssetInfoFragment.newInstance(false) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_asset_arg)

        viewModel = ViewModelProviders.of(this).get(ShareAssetViewModel::class.java)

        initButton()
        initFragment()
        changeFragment(0)
    }


    /*
         初始化Fragment
       */
    private fun initFragment() {
        addFragment(categoryFragment, R.id.mArgumentContainer)
        addFragment(locationFragment, R.id.mArgumentContainer)
        addFragment(managerFragment, R.id.mArgumentContainer)
        addFragment(assetFragment, R.id.mArgumentContainer)

//        依次入栈
        mStack.add(categoryFragment)
        mStack.add(locationFragment)
        mStack.add(managerFragment)
        mStack.add(assetFragment)
    }
    /*
      初始化按钮
     */
    private fun initButton() {
        mPrevBtn.setOnClickListener {
            currentPosition--
            changeFragment(currentPosition)
            setButton(currentPosition)
        }

        mNextBtn.setOnClickListener {
            if (viewModel.sharedAsset.category == null || viewModel.sharedAsset.category!!.isSelected.not() ) {
                alert {
                    message = "请选择要登记的资产"
                }.show()
            } else {
                currentPosition++
                changeFragment(currentPosition)
                setButton(currentPosition)
            }
        }

        mFinishBtn.setOnClickListener { }
    }

    /*
       设置按钮状态
     */
    private fun setButton(currentPosition: Int) {
        when (currentPosition) {
            0 -> {
                mPrevBtn.visibility = View.GONE
                mNextBtn.visibility = View.VISIBLE
                mFinishBtn.visibility = View.GONE
            }
            1, 2 -> {
                mPrevBtn.visibility = View.VISIBLE
                mNextBtn.visibility = View.VISIBLE
                mFinishBtn.visibility = View.GONE
            }
            3 -> {
                mPrevBtn.visibility = View.VISIBLE
                mNextBtn.visibility = View.GONE
                mFinishBtn.visibility = View.VISIBLE
            }
        }
    }

    private fun changeFragment(position: Int) {
        mStack.forEach {
            hideFragment(it)
        }
        if (position < 3) {
            mRegisterHBar.getTitleView().text = titles[position]
        }
        showFragment(mStack[position])
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        data?.apply { getFourth(this) }?:toast("没有返回选择结果！")
    }

    /**
     * 得到四级分类选择结果，并暂存在共享ViewModel中
     */
    private fun getFourth(data: Intent) {
        val result = data.getBundleExtra("fourthCg")
        val tableName = result.getString("tableName")
        val categoryInfo = result.getParcelable<CategoryInfo>("categoryInfo")
        when (tableName) {
            "Location" -> {
                viewModel.sharedAsset.location = categoryInfo
            }
            "Category" -> {
                viewModel.sharedAsset.category = categoryInfo
            }

        }
    }
}