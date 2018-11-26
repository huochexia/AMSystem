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
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.owner.assetsparam.R
import com.owner.assetsparam.data.CategoryInfo
import com.owner.assetsparam.databinding.ActivityRegisterAssetArgBinding
import com.owner.assetsparam.view.fragment.CategoryFragment
import com.owner.assetsparam.view.fragment.ManagerFragment
import com.owner.assetsparam.viewmodel.ArgumentViewModel
import com.owner.baselibrary.ext.addFragment
import com.owner.baselibrary.ext.hideFragment
import com.owner.baselibrary.ext.showFragment
import com.owner.baselibrary.view.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_register_asset_arg.*
import java.util.*

/**
 *  登记资产的参数，必选项类别和管理人
 * Created by Liuyong on 2018-11-14.It's AMSystem
 *@description:
 */
class RegisterAssetArgActivity : BaseActivity<ActivityRegisterAssetArgBinding, ArgumentViewModel>() {


    //Fragment 栈管理
    private val mStack = Stack<Fragment>()
    private val titles = arrayOf("资产", "管理人")
    private val categoryFragment by lazy { CategoryFragment.newInstance("Category", false, false) }
    private val managerFragment by lazy { ManagerFragment.newInstance(false, false) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_asset_arg)
        viewModel = ViewModelProviders.of(this).get(ArgumentViewModel::class.java)
        initBottomNav()
        initFragment()
        changeFragment(0)
    }

    /*
         初始化Fragment
       */
    private fun initFragment() {
        addFragment(categoryFragment, R.id.mArgumentContainer)

        addFragment(managerFragment, R.id.mArgumentContainer)

//        依次入栈
        mStack.add(categoryFragment)
        mStack.add(managerFragment)
    }


    /*
     初始化底部导航
    */
    private fun initBottomNav() {
        mAssertArgumentBNav.apply {
            //增加项目到导航栏,并设置默认位置，初始化
            addItem(categoryItem).addItem(managerItem)
                    .setFirstSelectedPosition(0).initialise()
        }.setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener {
            override fun onTabReselected(position: Int) {

            }

            override fun onTabUnselected(position: Int) {

            }

            override fun onTabSelected(position: Int) {
                changeFragment(position)
            }

        })

    }

    private fun changeFragment(position: Int) {
        mStack.forEach {
            hideFragment(it)
        }
        mRegisterHBar.getTitleView().text = titles[position]
        showFragment(mStack[position])
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data != null) {
            getFourth(data)
        }
    }

    /**
     * 得到四级分类选择结果，并暂存在共享ViewModel中
     */
    private fun getFourth(data: Intent) {
        val result = data.getBundleExtra("fourthCg")
        val tableName = result.getString("tableName")
        val categoryInfo = result.getParcelable<CategoryInfo>("categoryInfo")
        val pair = Pair(tableName, categoryInfo)
        viewModel.selectedArgumentMap[pair.first] = pair.second
    }
}