package com.owner.assertsparam.view.activity

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.orhanobut.logger.Logger
import com.owner.assertsparam.Interface.QueryAssertsInfo
import com.owner.assertsparam.R
import com.owner.assertsparam.data.CategoryInfo
import com.owner.assertsparam.data.Manager
import com.owner.assertsparam.view.fragment.CategoryFragment
import com.owner.assertsparam.view.fragment.FourCategoryFragment
import com.owner.assertsparam.view.fragment.ManagerFragment
import com.owner.assertsparam.viewmodel.ArgumentViewModel
import com.owner.baselibrary.ext.addFragment
import com.owner.baselibrary.ext.hideFragment
import com.owner.baselibrary.ext.showFragment
import com.owner.baselibrary.view.activity.BaseActivity
import com.owner.baselibrary.viewmodel.BaseViewModel
import com.owner.provideslib.router.RouterPath
import kotlinx.android.synthetic.main.activity_asserts_argument.*
import org.jetbrains.anko.toast
import java.util.*

@Route(path = RouterPath.AssertsParam.PATH_ASSERTSPARAM_MAIN)
class AssertsArgumentActivity : BaseActivity<ViewDataBinding, ArgumentViewModel>(),QueryAssertsInfo {

    override fun queryAssert(tablename: String, condition: Any) {
        when (tablename) {
            "Location"->{
                condition as CategoryInfo
            }
            "Category"->{
                condition as CategoryInfo
            }
            "Department"->{
                condition as CategoryInfo
            }
            "Manager"->{
                condition as Manager
            }
        }
        toast(condition.toString())
    }

    //Fragment 栈管理
    private val mStack = Stack<Fragment>()

    private val locationFragment by lazy { CategoryFragment.newInstance("Location",true,false) }
    private val categoryFragment by lazy { CategoryFragment.newInstance("Category",true,false) }
    private val departmentFragment by lazy {CategoryFragment.newInstance("Department",true,false) }
    private val managerFragment by lazy { ManagerFragment.newInstance(true,false) }

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asserts_argument)
        viewModel = ViewModelProviders.of(this).get(ArgumentViewModel::class.java)

        initFragment()
        initBottomNav()
        changeFragment(0)

    }

    /*
      初始化Fragment
    */
    private fun initFragment() {
        addFragment(locationFragment, R.id.mFgContainer)
        locationFragment.queryInterface =this
        addFragment(categoryFragment, R.id.mFgContainer)
        categoryFragment.queryInterface =this
        addFragment(departmentFragment, R.id.mFgContainer)
        departmentFragment.queryInterface = this
        addFragment(managerFragment, R.id.mFgContainer)
        managerFragment.queryInterface = this

//        依次入栈
        mStack.add(locationFragment)
        mStack.add(categoryFragment)
        mStack.add(departmentFragment)
        mStack.add(managerFragment)
    }

    /*
   初始化底部导航
    */
    private fun initBottomNav() {
        mAssertParamBNav.apply {
            addItem(locationItem).addItem(categoryItem).addItem(departmentItem).addItem(managerItem)
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
        when (requestCode) {
           ManagerFragment.SELECT_MANAGER_REQUEST_CODE->{
                managerFragment.onActivityResult(requestCode, resultCode, data)
            }
        }

    }
}
