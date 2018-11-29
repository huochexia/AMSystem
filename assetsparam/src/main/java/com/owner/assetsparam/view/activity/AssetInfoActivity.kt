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
package com.owner.assetsparam.view.activity

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.owner.assetsparam.R
import com.owner.assetsparam.databinding.ActivityAssetInfoBinding
import com.owner.assetsparam.viewmodel.AssetInfoViewModel
import com.owner.assetsparam.viewmodel.AssetInfoViewModelFactory
import com.owner.baselibrary.view.activity.BaseActivity
import com.owner.provideslib.router.RouterPath

/**
 *
 * Created by Liuyong on 2018-11-29.It's AMSystem
 *@description:
 */
@Route(path = RouterPath.Asset.PATH_ASSET_INFO)
class AssetInfoActivity:BaseActivity<ActivityAssetInfoBinding,AssetInfoViewModel>() {

    @JvmField
    @Autowired
    var isEdited = false


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        // ARouter会自动对字段进行赋值，无需主动获取
        ARouter.getInstance().inject(this)

        viewModel = ViewModelProviders.of(this,AssetInfoViewModelFactory(isEdited))
                .get(AssetInfoViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_asset_info)
        binding.assetVM = viewModel

    }
}