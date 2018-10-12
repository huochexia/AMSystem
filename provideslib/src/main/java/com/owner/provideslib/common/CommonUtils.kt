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
package com.owner.provideslib.common

import com.owner.baselibrary.common.BaseConstant
import com.owner.baselibrary.ext.pref
import com.owner.baselibrary.utils.AppPrefsUtils

/**
 *   提供通用方法
 * Created by Liuyong on 2018-10-11.It's AMSystem
 *@description:
 */
/*
判断当前是否是登录状态,通过从本地SharedPreferences中获取sessionToken的值来判断
如果为空则没有登录，如果有值则为登录
 */

    fun isLogined() :Boolean{
      //这里使用获取SharedPreferences中另一个工具类，因为在文件层级的方法中无法使用pref方法
        return AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN).isNotEmpty()
    }
