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
package com.owner.baselibrary.ext

import android.content.Context
import com.owner.baselibrary.common.AppContext
import java.lang.IllegalArgumentException
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty
import kotlin.reflect.jvm.jvmName

/**
 *
 * Created by Liuyong on 2018-09-25.It's AMSystem
 *@description:
 */

inline  fun <reified R,T> R.pref(default:T) = Preference(AppContext,"",default,R::class.jvmName)

class Preference<T>(val context: Context, val name: String, val default: T, val prefName: String = "default_ams")
    : ReadWriteProperty<Any?, T> {

    private val prefs by lazy {
        context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
    }

    /*
      如果构造方法的参数name为空，则使用变量名做为属性名
    */
    private fun findProperName(property: KProperty<*>) = if (name.isEmpty()) property.name else name

    /*
      通过构造方法的default参数得到属性类型，调用相应获取属性值的方法
     */
    private fun findPreference(key: String): T {
        return when (default) {
            is Long -> prefs.getLong(key, default)
            is Int -> prefs.getInt(key, default)
            is Boolean -> prefs.getBoolean(key, default)
            is String -> prefs.getString(key, default)
            else -> throw IllegalArgumentException("Unsupported type.")
        } as T
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return findPreference(findProperName(property))
    }

    /*
      通过参数valuer类型指定属性类型，调用相应方法写入属性值
     */
    private fun putPreference(key: String, value: T) {
        with(prefs.edit()) {
            when (value) {
                is Long -> putLong(key, value)
                is Int -> putInt(key, value)
                is Boolean -> putBoolean(key, value)
                is String -> putString(key, value)
                else -> throw IllegalArgumentException("Unsupported type.")
            }
        }.apply()
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putPreference(findProperName(property), value)
    }

}