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

import com.owner.amsystem.model.repository.MainRepository
import com.owner.baselibrary.viewmodel.BaseViewModel

/**
 *
 * Created by Liuyong on 2018-10-10.It's AMSystem
 *@description:
 */
class HomeViewModel:BaseViewModel<MainRepository>() {
    //模拟Banner数据
    val HOME_BANNER_ONE = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502876108368&di=cd9725c81901f6d7499edd76cf2e68e5&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F17%2F37%2F20%2F80Q58PICe3W_1024.jpg"
    val HOME_BANNER_TWO = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502882008108&di=d0cf4a8536aefa5df791716c1053ca66&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01e9495812c7f9a84a0d304fbc135b.jpg"
    val HOME_BANNER_THREE = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502876281925&di=f33e7ef8be268e90ffbffd315f5fb0a3&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F013e1b57d2731c0000018c1beeca11.jpg%40900w_1l_2o_100sh.jpg"
    val HOME_BANNER_FOUR = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1503471047&di=679d7a6c499f59d1b0dcd56b62a9aa6c&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.90sheji.com%2Fdianpu_cover%2F11%2F14%2F64%2F55%2F94ibannercsn_1200.jpg"

    val banner = listOf(HOME_BANNER_ONE,HOME_BANNER_TWO,HOME_BANNER_THREE,HOME_BANNER_FOUR)
    //模拟Topic数据
    val HOME_TOPIC_ONE = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502881866380&di=d252e1e8dd3a5a836fe360b02531f917&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01f5ce56e112ef6ac72531cb37bec4.png%40900w_1l_2o_100sh.jpg"
    val HOME_TOPIC_TWO = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502881904494&di=7a16834200a70469e1d3b6a4ab04c514&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F010d11554baebf000001bf721352ac.jpg"
    val HOME_TOPIC_THREE = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502876222250&di=aa3290c84822ba5570f19cb76e1012af&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F0146d25768b5a10000018c1b00cf27.jpg%40900w_1l_2o_100sh.jpg"
    val HOME_TOPIC_FOUR = "http://img.zcool.cn/community/01796c58772f66a801219c77e4fc04.png@1280w_1l_2o_100sh.png"
    val HOME_TOPIC_FIVE = "http://img.zcool.cn/community/0154805791ffeb0000012e7edba495.jpg@900w_1l_2o_100sh.jpg"

    val topic = listOf(HOME_TOPIC_ONE,HOME_TOPIC_TWO,HOME_TOPIC_THREE,HOME_TOPIC_FOUR,HOME_TOPIC_FIVE)

}