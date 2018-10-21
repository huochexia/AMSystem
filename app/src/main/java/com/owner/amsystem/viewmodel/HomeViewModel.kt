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
package com.owner.amsystem.viewmodel

import android.databinding.ObservableArrayList
import android.databinding.ViewDataBinding
import android.widget.Toast
import com.owner.amsystem.data.DiscountInfo
import com.owner.amsystem.model.repository.MainRepository
import com.owner.baselibrary.viewmodel.BaseViewModel

/**
 *
 * Created by Liuyong on 2018-10-10.It's AMSystem
 *@description:
 */
class HomeViewModel : BaseViewModel<MainRepository>() {
    //模拟Banner数据
    val HOME_BANNER_ONE = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502876108368&di=cd9725c81901f6d7499edd76cf2e68e5&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F17%2F37%2F20%2F80Q58PICe3W_1024.jpg"
    val HOME_BANNER_TWO = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502882008108&di=d0cf4a8536aefa5df791716c1053ca66&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01e9495812c7f9a84a0d304fbc135b.jpg"
    val HOME_BANNER_THREE = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502876281925&di=f33e7ef8be268e90ffbffd315f5fb0a3&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F013e1b57d2731c0000018c1beeca11.jpg%40900w_1l_2o_100sh.jpg"
    val HOME_BANNER_FOUR = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1503471047&di=679d7a6c499f59d1b0dcd56b62a9aa6c&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.90sheji.com%2Fdianpu_cover%2F11%2F14%2F64%2F55%2F94ibannercsn_1200.jpg"

    val banner = listOf(HOME_BANNER_ONE, HOME_BANNER_TWO, HOME_BANNER_THREE, HOME_BANNER_FOUR)
    //模拟Topic数据
    val HOME_TOPIC_ONE = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502881866380&di=d252e1e8dd3a5a836fe360b02531f917&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01f5ce56e112ef6ac72531cb37bec4.png%40900w_1l_2o_100sh.jpg"
    val HOME_TOPIC_TWO = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502881904494&di=7a16834200a70469e1d3b6a4ab04c514&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F010d11554baebf000001bf721352ac.jpg"
    val HOME_TOPIC_THREE = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502876222250&di=aa3290c84822ba5570f19cb76e1012af&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F0146d25768b5a10000018c1b00cf27.jpg%40900w_1l_2o_100sh.jpg"
    val HOME_TOPIC_FOUR = "http://img.zcool.cn/community/01796c58772f66a801219c77e4fc04.png@1280w_1l_2o_100sh.png"
    val HOME_TOPIC_FIVE = "http://img.zcool.cn/community/0154805791ffeb0000012e7edba495.jpg@900w_1l_2o_100sh.jpg"

    val topic = listOf(HOME_TOPIC_ONE, HOME_TOPIC_TWO, HOME_TOPIC_THREE, HOME_TOPIC_FOUR, HOME_TOPIC_FIVE)
    /*
    首页折扣图片
    */
    val HOME_DISCOUNT_ONE = "https://img14.360buyimg.com/n0/jfs/t3157/231/5756125504/98807/97ab361d/588084a1N06efb01d.jpg"
    val HOME_DISCOUNT_TWO = "https://img10.360buyimg.com/n7/jfs/t5905/106/1120548052/61075/6eafa3a5/592f8f7bN763e3d30.jpg"
    val HOME_DISCOUNT_THREE = "https://img10.360buyimg.com/n7/jfs/t5584/99/6135095454/371625/423b9ba5/59681d91N915995a7.jpg"
    val HOME_DISCOUNT_FOUR = "https://img11.360buyimg.com/n7/jfs/t4447/301/1238553109/193354/13c7e995/58db19a7N25101fe4.jpg"
    val HOME_DISCOUNT_FIVE = "https://img14.360buyimg.com/n1/s190x190_jfs/t7525/189/155179632/395056/e200017f/598fb8a4N7800dee6.jpg"

    val InfoList = ObservableArrayList<Any>()

    init {
        val info1 = DiscountInfo(HOME_DISCOUNT_ONE, 100, 110)
        val info2 = DiscountInfo(HOME_DISCOUNT_TWO, 120, 140)
        val info3 = DiscountInfo(HOME_DISCOUNT_THREE, 10, 50)
        val info4 = DiscountInfo(HOME_DISCOUNT_FOUR, 50, 60)
        val info5 = DiscountInfo(HOME_DISCOUNT_FIVE, 140, 170)
        val info10 = DiscountInfo(HOME_DISCOUNT_ONE, 100, 110)
        val info6 = DiscountInfo("", 120, 140)
        val info7 = DiscountInfo(HOME_DISCOUNT_THREE, 10, 50)
        val info8 = DiscountInfo(HOME_DISCOUNT_FOUR, 50, 60)
        val info9 = DiscountInfo(HOME_DISCOUNT_FIVE, 140, 170)

        InfoList.add(info1)
        InfoList.add(info2)
        InfoList.add(info3)
        InfoList.add(info4)
        InfoList.add(info5)
        InfoList.add(info6)
        InfoList.add(info7)
        InfoList.add(info8)
        InfoList.add(info9)
        InfoList.add(info10)
    }

    fun onBindItem(binding: ViewDataBinding, data: Any, position: Int) {
        binding.root.setOnClickListener { v ->
            Toast.makeText(v.context, data.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}