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
package com.owner.baselibrary.rvbinding.adapter.binding

import com.example.baselibrary.bindings.function.Function

/**
 *
 * Created by Liuyong on 2018-10-05.It's MVVMKotlinMall
 *@description:
 */
class Linker(val matcher: Function<Any, Boolean>, val layoutId: Int) {

    companion object {
        fun of(matcher: Function<Any, Boolean>, layoutId: Int): Linker {
            return Linker(matcher, layoutId)
        }
    }

}