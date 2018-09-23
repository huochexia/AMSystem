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
package com.owner.usercenter.data

import android.content.Context
import android.widget.Toast
import com.avos.avoscloud.*
import com.owner.baselibrary.common.AMSystemApp
import com.owner.baselibrary.data.respository.BaseRepository
import io.reactivex.Observable

/**
 *
 * Created by Liuyong on 2018-09-22.It's AMSystem
 *@description:
 */
class UserRepository :BaseRepository {
    /*
       注册
     */

    fun register(context: Context,mobile:String,pwd:String) {
        var user = AVUser()
        user.username = mobile
        user.setPassword(pwd)
        user.signUpInBackground(object : SignUpCallback() {
            override fun done(p0: AVException?) {
                if (p0 == null) {
                    Toast.makeText(context,"注册成功！", Toast.LENGTH_SHORT).show()
                } else {
                    when (p0.code) {
                        202 -> Toast.makeText(context,"该用户已存在！", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}