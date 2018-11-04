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
package com.owner.assertsparam.model.network.entites

import com.owner.assertsparam.data.CategoryInfo

/**
 * 生成分类对象的网络请求体和响应体
 * Created by Liuyong on 2018-10-28.It's AMSystem
 *@description:
 */
/*
生成分类对象
 */
data class CreateCgReq(val name:String,
                       val parentId:String,
                       val imageUrl:String="")

data class CreateCgResp(val code:Int,
                        val error:String?,
                        val objectId:String,
                        val name:String,
                        val parentId: String,
                        val imageUrl: String="")
/*
  获取分类对象，响应体，对应Json中的results字段
 */

data class QueryCategoryReq(val parentId: String)
data class QueryCategoryResp (val results:MutableList<CategoryInfo>)