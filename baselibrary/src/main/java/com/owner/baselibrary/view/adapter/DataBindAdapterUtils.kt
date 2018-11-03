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
package com.owner.baselibrary.view.adapter

import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import com.kennyc.view.MultiStateView
import com.owner.baselibrary.ext.loadUrl
import com.owner.baselibrary.utils.GlideUtils

/**
 *
 * Created by Liuyong on 2018-10-10.It's AMSystem
 *@description:
 */
class DataBindAdapterUtils {
    companion object {
        @BindingAdapter(value = ["imageUrl", "placeholder"], requireAll = false)
        @JvmStatic
        fun setImageUrl(imageView: ImageView, url: String?, placeholder: Drawable) {
            if (url.isNullOrEmpty()) {
                imageView.setImageDrawable(placeholder)
            } else {
               imageView.loadUrl(url!!)
            }
        }

        @BindingAdapter(value = ["textvalue", "placeholder"], requireAll = false)
        @JvmStatic
        fun setTextValue(textView: TextView, value: String?, default: String?) {
            if (value.isNullOrEmpty()) {
                textView.text = default
            } else {
                textView.text = value
            }
        }

        /*
           变换多状态视图界面
         */
        @BindingAdapter("msv_viewState")
        @JvmStatic
        fun setViewState(view: MultiStateView, state: Int) {
            view.viewState = state
        }
    }
}