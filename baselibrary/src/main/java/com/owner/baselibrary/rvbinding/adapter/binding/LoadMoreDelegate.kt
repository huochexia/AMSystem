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

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 *
 * Created by Liuyong on 2018-10-05.It's MVVMKotlinMall
 *@description:
 */
open class LoadMoreDelegate(private val loadMoreSubject: OnLoadMore) {

    fun attach(recyclerView: RecyclerView) {
        //只有LinearLayoutManager才可实现加载更多
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        recyclerView.addOnScrollListener(EndlessScrollListener(layoutManager, loadMoreSubject))
    }

    companion object {
        class EndlessScrollListener(val layoutManager: LinearLayoutManager, private val loadMoreSubject: OnLoadMore)
            : RecyclerView.OnScrollListener() {
            val VISIBLE_THRESHOLD = 6
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy < 0) {
                    return
                }
                val itemCount = layoutManager.itemCount
                val lastVisiblePosition = layoutManager.findLastCompletelyVisibleItemPosition()
                val isBottom = (lastVisiblePosition >= itemCount - VISIBLE_THRESHOLD)
                if (isBottom) {
                    loadMoreSubject.onLoadMore()
                }
            }
        }
    }
}