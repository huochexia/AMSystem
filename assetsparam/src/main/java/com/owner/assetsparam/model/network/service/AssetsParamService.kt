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
package com.owner.assetsparam.model.network.service

import com.owner.assetsparam.data.CategoryInfo
import com.owner.assetsparam.data.Manager
import com.owner.assetsparam.model.network.entites.*
import com.owner.baselibrary.model.network.RetrofitFactory
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.*

/**
 *
 * Created by Liuyong on 2018-10-28.It's AMSystem
 *@description:
 */

interface AssetsParamApi {
    /*
       创建分类对象
     */
    @POST(value = "1.1/classes/{table}?fetchWhenSave=true")
    fun createCategory(@Path("table") table:String, @Body request: CreateCgReq)
            :Observable<CategoryInfo>
    /*
       获取分类列表,得到分类的超类列表
     */
    @GET("1.1/classes/{table}")
    fun getCategory(@Path("table") table:String,@Query("where") condition:String)
            :Observable<GetCategoryList>
    /*
      删除分类,只返回成功与否的结果
     */
    @DELETE(value="1.1/classes/{table}/{id}")
    fun deleteCategory(@Path("table") table:String,@Path("id") objectId:String)
            :Completable
    /*
      修改分类,只返回成功与否的结果
     */
    @PUT(value = "1.1/classes/{table}/{id}")
    fun  updateCategory(@Path("table") table:String,@Path("id") objectId: String,
                       @Body request: CategoryInfo):Completable

    /*
      获取所有管理人员
     */
    @GET("1.1/users")
    fun getAllManager():Observable<QueryManagerResp>
    /*
     获取某个管理员
     */
    @GET("1.1/users/{id}")
    fun getManager(@Path("id") userId:String):Observable<Manager>

}

object AssertsParamService : AssetsParamApi by RetrofitFactory.instance.create(AssetsParamApi::class.java)