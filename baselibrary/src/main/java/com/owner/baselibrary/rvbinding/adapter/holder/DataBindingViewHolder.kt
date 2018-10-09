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
package com.owner.baselibrary.rvbinding.adapter.holder

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView

/**
 *  ViewHolder模板类,一个属性[binding]，ViewDataBinding类型
 * Created by Liuyong on 2018-10-05.It's MVVMKotlinMall
 *@description:
 */
open class DataBindingViewHolder<T:ViewDataBinding>(val binding:T) : RecyclerView.ViewHolder(binding.root) {

}