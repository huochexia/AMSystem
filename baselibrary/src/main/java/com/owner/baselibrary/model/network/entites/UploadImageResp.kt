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
package com.owner.baselibrary.model.network.entites

import java.io.File

/**
 * 上传图像的请求对象和网络响应对象
 * Created by Liuyong on 2018-10-16.It's AMSystem
 *@description:
 */
data class UploadImageReq(val imageFile: File, val _type: String = "file")

data class UploadImageResp(
        val code: Int = 0,
        val error: String?,
        val url: String?,
        val name: String?) {
    fun isSuccess(): Boolean = code == 0 && url.isNullOrEmpty().not()
}