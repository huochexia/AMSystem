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
package com.owner.amsystem.view.fragment

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.launcher.ARouter
import com.owner.amsystem.databinding.FragmentAssertBinding
import com.owner.amsystem.viewmodel.AssetViewModel
import com.owner.assetsparam.view.activity.RegisterAssetActivity
import com.owner.baselibrary.view.fragment.BaseFragment
import com.owner.provideslib.common.isLogined
import com.owner.provideslib.router.RouterPath
import kotlinx.android.synthetic.main.fragment_assert.*
import org.jetbrains.anko.support.v4.startActivity

/**
 *
 * Created by Liuyong on 2018-10-10.It's AMSystem
 *@description:
 */
class AssetFragment :BaseFragment<FragmentAssertBinding,AssetViewModel>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AssetViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAssertBinding.inflate(inflater,container,false)
        binding.assertvm = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mSelectArgBtn.setOnClickListener {
            if (isLogined()) {
                startActivity<RegisterAssetActivity>()
            } else {
                ARouter.getInstance().build(RouterPath.UserCenter.PATH_USER_LOGIN).navigation()
            }
        }
    }

}