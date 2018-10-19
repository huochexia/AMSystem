package com.owner.amsystem.view.activity

import android.databinding.ViewDataBinding
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.avos.avoscloud.LogUtil
import com.orhanobut.logger.Logger
import com.owner.amsystem.R
import com.owner.amsystem.view.fragment.AssertFragment
import com.owner.amsystem.view.fragment.HomeFragment
import com.owner.amsystem.view.fragment.MeFragment
import com.owner.amsystem.view.fragment.MessageFragment
import com.owner.baselibrary.common.AppManager
import com.owner.baselibrary.ext.addFragment
import com.owner.baselibrary.ext.hideFragment
import com.owner.baselibrary.ext.showFragment
import com.owner.baselibrary.view.activity.BaseActivity
import com.owner.baselibrary.viewmodel.BaseViewModel
import com.owner.provideslib.router.RouterPath
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import java.util.*

@Route(path = RouterPath.App.PATH_MAIN)
class MainActivity : BaseActivity<ViewDataBinding,BaseViewModel<*>>() {

    private var pressTime:Long = 0
    //Fragment 栈管理
    private val mStack = Stack<Fragment>()

    private val homeFragment by lazy { HomeFragment() }
    private val assertFragment by lazy { AssertFragment() }
    private val messageFragment by lazy { MessageFragment() }
    private val meFragment by lazy { MeFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFragment()
        initBottomNav()
        changeFragment(0)
    }

    /*
       初始化Fragment
     */
    private fun initFragment() {
        addFragment(homeFragment,R.id.mContainer)
        addFragment(assertFragment,R.id.mContainer)
        addFragment(messageFragment,R.id.mContainer)
        addFragment(meFragment,R.id.mContainer)
        //依次入栈
        mStack.add(homeFragment)
        mStack.add(assertFragment)
        mStack.add(messageFragment)
        mStack.add(meFragment)
    }

    /*
    初始化底部导航
     */
    private fun initBottomNav() {
        mBottomNavBar.setTabSelectedListener(object :BottomNavigationBar.OnTabSelectedListener{
            override fun onTabReselected(position: Int) {

            }

            override fun onTabUnselected(position: Int) {

            }

            override fun onTabSelected(position: Int) {
                changeFragment(position)
            }

        })
        mBottomNavBar.checkMsgBadge(false)
        mBottomNavBar.checkAssertBadge(0)
    }

    private fun changeFragment(position: Int) {
        mStack.forEach {
            hideFragment(it)
        }
        showFragment(mStack[position])
    }

    /*
        重写Back事件，双击退出
     */
    override fun onBackPressed() {
        val time = System.currentTimeMillis()
        if (time - pressTime > 2000){
            toast("再按一次退出程序")
            pressTime = time
        } else{
            AppManager.instance.exitApp(this)
        }
    }
}
