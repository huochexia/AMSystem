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
package com.owner.assetsparam.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * 所有分类的父类信息
 * @name ：类别名称
 * @id ：自身ID值,总分类的Id是0，各级分类ID系统产生
 * @parentId ：所属类别ID
 * @imageUrl：类别图片网络地址，默认为空字符串。因为一级，二级类别没有图片
 * @isSelected:是否被选择，这个属性不存储于网络
 * Created by Liuyong on 2018-10-21.It's AMSystem
 *@description:
 */
@Parcelize
 class CategoryInfo(var objectId: String,
                        var name: String,
                        var parentId: String="",
                        var imageUrl: String = "",
                        var isSelected: Boolean = false,//选择状态
                        var isLongOnClick: Boolean = false,//长按状态
                        var hasChild: Boolean = false) //是否有子类
    : Parcelable

