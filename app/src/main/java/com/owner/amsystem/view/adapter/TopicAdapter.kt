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
package com.owner.amsystem.view.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.owner.amsystem.R
import com.owner.baselibrary.utils.GlideUtils
import kotlinx.android.synthetic.main.layout_topic_item.view.*

/**
 *
 * Created by Liuyong on 2018-10-06.It's MVVMKotlinMall
 *@description:
 */
class TopicAdapter(private val context: Context, private val list: List<String>) : PagerAdapter() {
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return this.list.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val rootView = LayoutInflater.from(context).inflate(R.layout.layout_topic_item, null)
        GlideUtils.loadUrlImage(context, list[position], rootView.mTopicIv)
        container.addView(rootView)
        return rootView
    }
}