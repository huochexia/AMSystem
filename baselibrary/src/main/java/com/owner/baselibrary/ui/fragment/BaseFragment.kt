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
package com.owner.baselibrary.ui.fragment

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import com.owner.baselibrary.common.AMSystemApp
import com.owner.baselibrary.viewmodel.BaseViewModel
import com.squareup.leakcanary.RefWatcher

/**
 *基础Fragment模板类，绑定ViewDataBinding和ViewModel两个类。
 * Created by Liuyong on 2018-09-15.It's AMSystem
 *@description:
 */
open class BaseFragment<B:ViewDataBinding,VM:BaseViewModel<*>>: Fragment() {

    protected lateinit var binding:B

    protected lateinit var viewModel:VM

    protected lateinit var refWatcher: RefWatcher


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onDestroy() {
        super.onDestroy()
        //使用RefWatcher监控Fragment
        val refWatcher = AMSystemApp.getRefWatcher(activity!!)
        refWatcher.watch(this)
    }


}