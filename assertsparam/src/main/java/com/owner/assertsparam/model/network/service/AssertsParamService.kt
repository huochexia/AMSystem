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
package com.owner.assertsparam.model.network.service

import com.owner.assertsparam.data.CategoryInfo
import com.owner.assertsparam.data.Manager
import com.owner.assertsparam.model.network.entites.*
import com.owner.baselibrary.model.network.RetrofitFactory
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

/**
 *
 * Created by Liuyong on 2018-10-28.It's AMSystem
 *@description:
 */

interface AssertsParamApi {
    /*
       创建分类对象
     */
    @POST(value = "1.1/classes/Category?fetchWhenSave=true")
    fun createCategory(@Body request: CreateCgReq):Observable<CategoryInfo>
    /*
       获取分类
     */
    @GET("1.1/classes/Category")
    fun getCategory(@Query("where") condition:String):Observable<QueryCategoryResp>
    /*
      删除分类
     */
    @DELETE(value="1.1/classes/Category/{id}")
    fun deleteCategory(@Path("id") objectId:String):Observable<ResponseBody>
    /*
      修改分类
     */
    @PUT(value = "1.1/classes/Category/{id}")
    fun updateCategory(@Path("id") objectId: String,@Body request: CategoryInfo):Observable<ResponseBody>

    /*
      获取管理人员
     */
    @GET("1.1/users")
    fun getManager():Observable<QueryManagerResp>
}

object AssertsParamService : AssertsParamApi by RetrofitFactory.instance.create(AssertsParamApi::class.java)