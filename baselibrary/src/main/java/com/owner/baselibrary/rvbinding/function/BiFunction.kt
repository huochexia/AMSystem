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
package com.owner.baselibrary.rvbinding.function

/**
 *定义这个接口，主要是因为Android的ApI24以上才支持这个接口
 * Created by Liuyong on 2018-10-05.It's MVVMKotlinMall
 *@description:
 */
interface BiFunction<T,U,R> {
    fun apply(value:T,value2:U):R
}