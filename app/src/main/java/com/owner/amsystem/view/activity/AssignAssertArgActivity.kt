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
package com.owner.amsystem.view.activity

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.owner.amsystem.R
import com.owner.amsystem.databinding.ActivityAssignArgumentBinding
import com.owner.amsystem.viewmodel.AssertViewModel
import com.owner.assertsparam.view.fragment.CategoryFragment
import com.owner.assertsparam.view.fragment.ManagerFragment
import com.owner.baselibrary.ext.addFragment
import com.owner.baselibrary.ext.hideFragment
import com.owner.baselibrary.ext.showFragment
import com.owner.baselibrary.view.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_assign_argument.*
import java.util.*

/**
 *  指定资产参数
 * Created by Liuyong on 2018-11-14.It's AMSystem
 *@description:
 */
class AssignAssertArgActivity : BaseActivity<ActivityAssignArgumentBinding, AssertViewModel>() {


    //Fragment 栈管理
    private val mStack = Stack<Fragment>()

    private val locationFragment by lazy { CategoryFragment.newInstance("Location", false, false) }
    private val categoryFragment by lazy { CategoryFragment.newInstance("Category", false, false) }
    private val managerFragment by lazy { ManagerFragment.newInstance(false, false) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assign_argument)
        viewModel = ViewModelProviders.of(this).get(AssertViewModel::class.java)
        initBottomNav()
        initFragment()
    }

    /*
         初始化Fragment
       */
    private fun initFragment() {
        addFragment(locationFragment, R.id.mArgumentContainer)

        addFragment(categoryFragment, R.id.mArgumentContainer)

        addFragment(managerFragment, R.id.mArgumentContainer)


//        依次入栈
        mStack.add(locationFragment)
        mStack.add(categoryFragment)
        mStack.add(managerFragment)
    }

    /*
   初始化底部导航
    */
    private fun initBottomNav() {
        mAssertArgumentBNav.apply {
            //增加项目到导航栏,并设置默认位置，初始化
            addItem(locationItem).addItem(categoryItem).addItem(managerItem)
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
        showFragment(mStack[position])
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        managerFragment.onActivityResult(requestCode, resultCode, data)
    }
}