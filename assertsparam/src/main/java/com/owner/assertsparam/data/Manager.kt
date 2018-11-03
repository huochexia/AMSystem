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
package com.owner.assertsparam.data

import com.owner.baselibrary.utils.PinyinUtils

/**
 *管理员
 * @letters:拼音首字母
 * Created by Liuyong on 2018-10-30.It's AMSystem
 *@description:
 */
data class Manager(val name: String,
                   val department: String,
                   val duty: String = "",
                   val phone: String,
                   val avatar: String = "") {

    var letters: String = PinyinUtils.getFirstSpell(name).toUpperCase().toCharArray()[0].toString()

}