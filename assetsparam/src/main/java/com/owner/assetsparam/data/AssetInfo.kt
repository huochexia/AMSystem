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

/**
 * @state :资产状态，正常，待接收，报修，报废等
 * Created by Liuyong on 2018-11-29.It's AMSystem
 *@description:
 */
data class AssetInfo(val objectId:String="",
                     val category: CategoryInfo = CategoryInfo("", ""),
                     val location:CategoryInfo =CategoryInfo("",""),
                     val number: Double = 0.0,
                     val unit: String = "",
                     val price: Double = 0.0,
                     val receiveDate: String = "",
                     val comment: String = "",
                     val manager: Manager = Manager(),
                     val receiver: Manager = Manager(),
                     val state:Int = 0)
