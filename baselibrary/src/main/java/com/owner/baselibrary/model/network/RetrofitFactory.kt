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
package com.owner.baselibrary.model.network

import com.owner.baselibrary.common.AppContext
import com.owner.baselibrary.common.BaseConstant
import com.owner.baselibrary.ext.ensureDir
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * 用于创建网络访问对象的工厂
 * Created by Liuyong on 2018-09-24.It's AMSystem
 *@description:
 */
class RetrofitFactory private constructor() {

    companion object {
        val instance: RetrofitFactory by lazy {
            RetrofitFactory()
        }
    }

//    private val interceptor: Interceptor
    private val retrofit: Retrofit

    private val cacheFile by lazy {
        File(AppContext.cacheDir, "webServiceApi").apply {
            ensureDir()
        }
    }

    init {
//        //通用拦截
//        interceptor = Interceptor { chain ->
//            val request = chain.request()
//                    .newBuilder()
//                    .addHeader(BaseConstant.APP_ID_NAME, BaseConstant.APP_ID_VALUE)
//                    .addHeader(BaseConstant.CLIENT_KEY_NAME, BaseConstant.CLIENT_KEY_VALUE)
//                    .addHeader("Content_Type", "application/json")
//                    .addHeader("charset", "UTF-8")
//                    .build()
//            chain.proceed(request)
//        }
        retrofit = Retrofit.Builder()
                .baseUrl(BaseConstant.SERVER_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(initClient())
                .build()

    }

    /*
      OKHttp创建
     */
    private fun initClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(initLogInterceptor())
                .addNetworkInterceptor(initNetWorkInterceptor())
                .cache(Cache(cacheFile, 1024 * 1024))
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build()
    }

    /*
      网络数据拦截器,为了我们能够接收400返回之后的数据，因为LeanCloud错误返回的固定格式{“code”:201,
      “error”:“error message”}所以我们需要修改response的响应码，让我们可以获取到返回的数据，所以
      就需要添加一下okhttp的网络拦截器，在里面判断一下response的code是不是400，如果是的话，就把
      response.body().string()和response.contentType()取出来保存，然后返回一个response.newBuilder()
      .code(200).body(ResponseBody.create(mediaType,content)).build()，这样就可以把400返回的数据返回成功
     */
    private fun initNetWorkInterceptor(): Interceptor {
        return Interceptor { chain ->
            //增加LeanCloud要求的请求头
            val request = chain.request().newBuilder()
                    .addHeader(BaseConstant.APP_ID_NAME, BaseConstant.APP_ID_VALUE)
                    .addHeader(BaseConstant.CLIENT_KEY_NAME, BaseConstant.CLIENT_KEY_VALUE)
                    .addHeader("Content_Type", "application/json")
                    .addHeader("charset", "UTF-8")
                    .build()
            val response = chain.proceed(request)
            //处理如果LeanCloud返回失败时的结果
            if (!response.isSuccessful) {
                val mediaType = response.body()?.contentType()
                val content = response.body()?.string()
                response.newBuilder().code(200)
                        .body(ResponseBody.create(mediaType,content))
                        .build()
            } else {
                response
            }
        }
    }

    /*
      日志拦截器
     */
    private fun initLogInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    /*
       实例化具体Api接口
     */
    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }


}