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

import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView

/**
 *
 * Created by Liuyong on 2018-11-28.It's AMSystem
 *@description:
 */
/**
 *  TextView扩展
 */
private typealias BeforeTextChangedFunction = (text: String, start: Int, count: Int, after: Int) -> Unit
private typealias OnTextChangedFunction = (text: String, start: Int, before: Int, count: Int) -> Unit
private typealias AfterTextChangedFunction = (s: Editable) -> Unit


fun TextView.addOnTextChangeListener(config: TextWatcherConfiguration.() -> Unit) {
    val listener = TextWatcherConfiguration().apply { config() }
    addTextChangedListener(listener)
}


class TextWatcherConfiguration : TextWatcher {

    private var beforeTextChangedCallback: (BeforeTextChangedFunction)? = null
    private var onTextChangedCallback: (OnTextChangedFunction)? = null
    private var afterTextChangedCallback: (AfterTextChangedFunction)? = null

    fun beforeTextChanged(callback: BeforeTextChangedFunction) {
        beforeTextChangedCallback = callback
    }

    fun onTextChanged(callback: OnTextChangedFunction) {
        onTextChangedCallback = callback
    }

    fun afterTextChanged(callback: AfterTextChangedFunction) {
        afterTextChangedCallback = callback
    }

    override fun afterTextChanged(p0: Editable) {
        afterTextChangedCallback?.invoke(p0)
    }

    override fun beforeTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
        beforeTextChangedCallback?.invoke(p0.toString(), p1, p2, p3)
    }

    override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
        onTextChangedCallback?.invoke(p0.toString(), p1, p2, p3)
    }

}