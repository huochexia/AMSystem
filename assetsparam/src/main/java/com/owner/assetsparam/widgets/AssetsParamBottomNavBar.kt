package com.owner.assetsparam.widgets

import android.content.Context
import android.util.AttributeSet
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.owner.assetsparam.R


/**
 * 资产参数设置模块底部导航栏
 * Created by Administrator on 2018/10/19 0019
 */
class AssetsParamBottomNavBar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BottomNavigationBar(context, attrs, defStyleAttr) {

    val locationItem: BottomNavigationItem = BottomNavigationItem(R.mipmap.btn_nav_home_press,
            resources.getString(R.string.nav_bar_assert_location))
            .setInactiveIconResource(R.mipmap.btn_nav_home_normal)
            .setActiveColorResource(R.color.common_blue)
            .setInActiveColorResource(R.color.text_normal)
    val categoryItem: BottomNavigationItem = BottomNavigationItem(R.mipmap.btn_nav_category_press,
            resources.getString(R.string.nav_bar_assert_category))
            .setInactiveIconResource(R.mipmap.btn_nav_category_normal)
            .setActiveColorResource(R.color.common_blue)
            .setInActiveColorResource(R.color.text_normal)
    val departmentItem: BottomNavigationItem = BottomNavigationItem(R.mipmap.btn_nav_msg_press,
            resources.getString(R.string.nav_bar_assert_department))
            .setInactiveIconResource(R.mipmap.btn_nav_msg_normal)
            .setActiveColorResource(R.color.common_blue)
            .setInActiveColorResource(R.color.text_normal)
    val managerItem: BottomNavigationItem = BottomNavigationItem(R.mipmap.btn_nav_user_press,
            resources.getString(R.string.nav_bar_assert_manager))
            .setInactiveIconResource(R.mipmap.btn_nav_user_normal)
            .setActiveColorResource(R.color.common_blue)
            .setInActiveColorResource(R.color.text_normal)

    init {
        //设置底部导航栏模式，背景风格及颜色
        setMode(BottomNavigationBar.MODE_FIXED)
        setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
        setBarBackgroundColor(R.color.common_white)
//        //增加项目到导航栏,并设置默认位置，初始化
//        addItem(locationItem).addItem(categoryItem).addItem(departmentItem).addItem(managerItem)
//                .setFirstSelectedPosition(0).initialise()

    }
}