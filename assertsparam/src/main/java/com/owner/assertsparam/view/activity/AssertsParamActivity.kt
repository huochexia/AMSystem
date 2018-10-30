package com.owner.assertsparam.view.activity

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.owner.assertsparam.R
import com.owner.assertsparam.view.fragment.CategoryFragment
import com.owner.assertsparam.view.fragment.ManagerFragment
import com.owner.assertsparam.viewmodel.AssertsParaViewModel
import com.owner.baselibrary.ext.addFragment
import com.owner.baselibrary.ext.hideFragment
import com.owner.baselibrary.ext.showFragment
import com.owner.baselibrary.view.activity.BaseActivity
import com.owner.baselibrary.viewmodel.BaseViewModel
import kotlinx.android.synthetic.main.activity_asserts_param.*
import java.util.*

class AssertsParamActivity : BaseActivity<ViewDataBinding,BaseViewModel<*>>() {
    //Fragment 栈管理
    private val mStack = Stack<Fragment>()

    private val locationFragment by lazy { ManagerFragment() }
    private val categoryFragment by lazy { CategoryFragment() }
    private val departmentFragment by lazy { ManagerFragment() }
    private val managerFragment by lazy { ManagerFragment() }

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asserts_param)
        viewModel = ViewModelProviders.of(this).get(AssertsParaViewModel::class.java)

        initFragment()
        initBottomNav()

    }

    /*
      初始化Fragment
    */
    private fun initFragment() {
        addFragment(locationFragment, R.id.mFgContainer)
        addFragment(categoryFragment, R.id.mFgContainer)
        addFragment(departmentFragment, R.id.mFgContainer)
        addFragment(managerFragment, R.id.mFgContainer)
        //依次入栈
        mStack.add(locationFragment)
        mStack.add(categoryFragment)
        mStack.add(departmentFragment)
        mStack.add(managerFragment)
    }

    /*
   初始化底部导航
    */
    private fun initBottomNav() {
        mAssertParamBNav.setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener {
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
}
