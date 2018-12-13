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
package com.owner.assetsparam.model.datasource

import com.owner.assetsparam.data.CategoryInfo
import com.owner.assetsparam.data.Manager
import com.owner.assetsparam.model.network.entites.GetCategoryList
import com.owner.assetsparam.model.network.entites.QueryManagerResp
import io.reactivex.*
import okhttp3.Response

/**
 * 数据源接口，资产分类数据管理主要入口
 * Created by Liuyong on 2018-12-13.It's AMSystem
 *@description:
 */
interface CategoryDataSource {
    /*
      创建分类
     */
    fun createCategory(className:String,name: String, parentId: String, imageUrl: String): Flowable<CategoryInfo>

    /*
      按父类ID查找子分类
     */
    fun getCategory(className: String,parentId: String): Flowable<List<CategoryInfo>>


    /*
     删除分类
     */
    fun deleteCategory(className: String,objectId: String): Single<Any>

    /*
      更新分类
     */
    fun updateCategory(className: String,category: CategoryInfo): Single<Any>

}