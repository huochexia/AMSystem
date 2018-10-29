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
import com.owner.assertsparam.model.network.entites.CreateCgReq
import com.owner.assertsparam.model.network.entites.CreateCgResp
import com.owner.baselibrary.model.network.RetrofitFactory
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

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
    fun createCategory(@Body request: CreateCgReq):Observable<CreateCgResp>
    /*
       获取分类
     */
    @GET("1.1/classes/Category")
    fun getCategory(@Query("parentId") parentId:String):Observable<Response<List<CategoryInfo>?>>

}

object AssertsParamService : AssertsParamApi by RetrofitFactory.instance.create(AssertsParamApi::class.java)