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
package com.owner.baselibrary.data.net

import android.app.Application
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.owner.baselibrary.common.BaseConstant
import com.owner.baselibrary.ext.pref
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
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
    private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BaseConstant.SERVER_ADDRESS)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(initClient())
            .build()

    private fun provideInterceptor() : Interceptor{
        var sessionToken by pref("")
        return Interceptor{chain ->
            val request = chain.request()
            chain.proceed(request).newBuilder()
                    .addHeader(BaseConstant.APP_ID_NAME,BaseConstant.APP_ID_VALUE)
                    .addHeader(BaseConstant.CLIENT_KEY_NAME,BaseConstant.CLIENT_KEY_VALUE)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("sessionToken",sessionToken)
                    .build()
        }

    }
    private fun initClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addNetworkInterceptor(provideInterceptor())
                .addInterceptor(initLogInterceptor())
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build()
    }

    private fun initLogInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }


}