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
package com.owner.baselibrary.model.network.service

import com.owner.baselibrary.model.network.RetrofitFactory
import com.owner.baselibrary.model.network.entites.UploadImageReq
import com.owner.baselibrary.model.network.entites.UploadImageResp
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*
import java.io.File

/**
 *  上传图像
 * Created by Liuyong on 2018-10-16.It's AMSystem
 *@description:
 */

interface UploadTakePhoto {


    @Headers("Content-Type: image/png")
    @POST("1.1/files/{filename}")
    fun uploadImage(@Path("filename") filename:String, @Body req:UploadImageReq ): Observable<UploadImageResp>
}

object BaseService : UploadTakePhoto by  RetrofitFactory.instance.create(UploadTakePhoto::class.java)
