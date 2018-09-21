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
package com.owner.baselibrary.widgets

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.owner.baselibrary.R
import kotlinx.android.synthetic.main.layout_header_bar.view.*

/**
 *自定义视图：标题栏
 * Created by Liuyong on 2018-09-18.It's AMSystem
 *@description:
 */
class HeaderBar  @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr){

    private var isShowBack = true
    private var titleText: String? = null
    private var rightText: String? = null
    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.HeaderBar)
        isShowBack = typedArray.getBoolean(R.styleable.HeaderBar_isShowBack, true)
        titleText = typedArray.getString(R.styleable.HeaderBar_titleText)
        rightText = typedArray.getString(R.styleable.HeaderBar_rightText)

        initView()
        typedArray.recycle()
    }

    private fun initView() {
        View.inflate(context,R.layout.layout_header_bar,this)
        mLeftIv.visibility = if(isShowBack)View.VISIBLE else View.GONE

        titleText?.let {
            mTitleTv.text = it
        }

        rightText?.let {
            mRightTv.text = it
        }
        mLeftIv.setOnClickListener {
            if (context is Activity) {
                (context as Activity).finish()
            }
        }
    }
    /*
    对外提供右边的TextView组件
     */
    fun getRightView() : TextView {
        return mRightTv
    }

    /*
    对外提供左边的组件
     */
    fun getLeftView(): ImageView {
        return mLeftIv
    }
}