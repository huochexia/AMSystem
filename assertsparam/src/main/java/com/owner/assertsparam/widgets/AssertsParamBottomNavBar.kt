package com.owner.assertsparam.widgets

import android.content.Context
import android.util.AttributeSet
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.owner.assertsparam.R


/**
 * Created by Administrator on 2018/10/19 0019
 */
class AssertsParamBottomNavBar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BottomNavigationBar(context, attrs, defStyleAttr) {

    init {
        //底部导航栏设置4个项目
        //首页
        val locationItem = BottomNavigationItem(R.mipmap.btn_nav_home_press,
                resources.getString(R.string.nav_bar_assert_location))
                .setInactiveIconResource(R.mipmap.btn_nav_home_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal)
        //类别
        val categoryItem = BottomNavigationItem(R.mipmap.btn_nav_category_press,
                resources.getString(R.string.nav_bar_assert_category))
                .setInactiveIconResource(R.mipmap.btn_nav_category_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal)

        //部门
        val departmentItem = BottomNavigationItem(R.mipmap.btn_nav_msg_press,
                resources.getString(R.string.nav_bar_assert_department))
                .setInactiveIconResource(R.mipmap.btn_nav_msg_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal)
        //管理人
        val managerItem = BottomNavigationItem(R.mipmap.btn_nav_user_press,
                resources.getString(R.string.nav_bar_assert_manager))
                .setInactiveIconResource(R.mipmap.btn_nav_user_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal)
        //设置底部导航栏模式，背景风格及颜色
        setMode(BottomNavigationBar.MODE_FIXED)
        setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
        setBarBackgroundColor(R.color.common_white)
        //增加项目到导航栏,并设置默认位置，初始化
        addItem(locationItem).addItem(categoryItem).addItem(departmentItem).addItem(managerItem)
                .setFirstSelectedPosition(0).initialise()

    }
}