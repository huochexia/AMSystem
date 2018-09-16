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
package com.owner.baselibrary.injection.module

import android.app.Application
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.owner.baselibrary.common.BaseConstant
import com.owner.baselibrary.injection.qualifier.AppContext
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 提供获得网络数据的对象
 * Created by Liuyong on 2018-09-15.It's AMSystem
 *@description:
 */
@Module
class ApiModule {
    companion object {
        val CACHE_SIZE: Long = 10 * 1024 * 1024 //10MB
    }

    /*
      日志拦截器
     */
    @Provides
    @Singleton
    fun getLoggingInterceptor(): HttpLoggingInterceptor {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    /*
      Gson
     */
    @Provides
    @Singleton
    fun provideGson(): Gson {

        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

    /*
      网络缓存
     */
    @Provides
    @Singleton
    fun provideOkhttpCache(@AppContext application: Application): Cache {
        return Cache(application.cacheDir, CACHE_SIZE)
    }

    /*
      网络缓存拦截器
     */
    @Provides
    @Singleton
    fun provideCacheOverrideInterceptor(): Interceptor {
        return Interceptor { chain ->
            val maxStale = 60 * 60 * 24 * 7 // tolerate 1-week stale
            chain.proceed(chain.request())
                    .newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-age=$maxStale")
                    .build()
        }
    }

    /*
      网络请求Client
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor, cache: Cache,
                            cacheOverrideInterceptor: HttpLoggingInterceptor): OkHttpClient {

        return OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(loggingInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addNetworkInterceptor(cacheOverrideInterceptor)
                .build()
    }

    /*
      Retrofit，这个是最主要的上面的都是为它服务的
     */
    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit {

        return Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BaseConstant.BASE_URL)
                .client(client)
                .build()
    }


}