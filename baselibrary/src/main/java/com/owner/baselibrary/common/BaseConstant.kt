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
package com.owner.baselibrary.common

/**
 * 基本常量
 * Created by Liuyong on 2018-09-15.It's AMSystem
 *@description:
 */
class BaseConstant {
    companion object {
        /*
        Api访问网址
         */
        const val BASE_URL = ""
        /*
         SharedPreferences所有表表名
         */
        const val TABLE_PREFS = "ams_table"

        //Token Key,在做网络请求时，这个值通常是放在Header当中的，我们retrofit工厂是在base中，
        // 所以将它设在base中
        const val KEY_SP_TOKEN = "token"
    }
}