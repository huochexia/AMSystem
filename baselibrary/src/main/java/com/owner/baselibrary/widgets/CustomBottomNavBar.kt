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
package com.owner.baselibrary.widgets

import android.content.Context
import android.util.AttributeSet
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.ashokvarma.bottomnavigation.ShapeBadgeItem
import com.ashokvarma.bottomnavigation.TextBadgeItem
import com.owner.baselibrary.R

/**
 *底部导航,通过继承谷歌官方提供的BottomNavigation类，定制自己的底部导航栏。
 *  同时为购物车和消息项目增加了角标的设置。
 * Created by Liuyong on 2018-10-09.It's AMSystem
 *@description:
 */
class CustomBottomNavBar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BottomNavigationBar(context, attrs, defStyleAttr) {
    //给资产加文本角标，用于标识新增加资产的数量
    private val mAssertBade: TextBadgeItem by lazy { TextBadgeItem() }
    //消息上加图形角标，当有新消息时提醒
    private val mMsgBadge: ShapeBadgeItem by lazy { ShapeBadgeItem() }

    init {
        //底部导航栏设置4个项目
        //首页
        val homeItem = BottomNavigationItem(R.mipmap.btn_nav_home_press,
                resources.getString(R.string.nav_bar_home))
                .setInactiveIconResource(R.mipmap.btn_nav_home_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal)
        //资产
        val assertItem = BottomNavigationItem(R.mipmap.btn_nav_category_press,
                resources.getString(R.string.nav_bar_assert))
                .setInactiveIconResource(R.mipmap.btn_nav_category_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal)
                .setBadgeItem(mAssertBade)

        //消息
        val msgItem = BottomNavigationItem(R.mipmap.btn_nav_msg_press,
                resources.getString(R.string.nav_bar_msg))
                .setInactiveIconResource(R.mipmap.btn_nav_msg_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal)
        mMsgBadge.setShape(ShapeBadgeItem.SHAPE_OVAL)
        msgItem.setBadgeItem(mMsgBadge)
        //我的
        val userItem = BottomNavigationItem(R.mipmap.btn_nav_user_press,
                resources.getString(R.string.nav_bar_user))
                .setInactiveIconResource(R.mipmap.btn_nav_user_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal)
        //设置底部导航栏模式，背景风格及颜色
        setMode(BottomNavigationBar.MODE_FIXED)
        setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
        setBarBackgroundColor(R.color.common_white)
        //增加项目到导航栏,并设置默认位置，初始化
        addItem(homeItem).addItem(assertItem).addItem(msgItem).addItem(userItem)
                .setFirstSelectedPosition(0).initialise()
    }

    /**
     * 动态显示角标,当有新资产时显示角标
     */
    fun checkAssertBadge(count: Int) {
        if (count == 0) {
            mAssertBade.hide()
        } else {
            mAssertBade.show()
            mAssertBade.setText("$count")
        }
    }

    /**
     * 当有新消息时显示角标
     */
    fun checkMsgBadge(isVisiable: Boolean) {
        if (isVisiable) {
            mMsgBadge.show()
        } else {
            mMsgBadge.hide()
        }

    }
}