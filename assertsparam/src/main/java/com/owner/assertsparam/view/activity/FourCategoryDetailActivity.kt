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
package com.owner.assertsparam.view.activity

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.owner.assertsparam.R
import com.owner.assertsparam.databinding.ActivityFourCategoryDetailBinding
import com.owner.assertsparam.view.fragment.FourCategoryFragment
import com.owner.assertsparam.viewmodel.FourCgViewModel
import com.owner.baselibrary.ext.addFragment
import com.owner.baselibrary.view.activity.BaseActivity
import com.owner.provideslib.router.RouterPath

/**
 *  四级分类明细
 * Created by Liuyong on 2018-11-17.It's AMSystem
 *@description:
 */
@Route(path = RouterPath.AssertsParam.PATH_ASSERTSPARAM_FOUR)
class FourCategoryDetailActivity : BaseActivity<ActivityFourCategoryDetailBinding, FourCgViewModel>() {

    private val fragment by lazy { FourCategoryFragment.newInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_four_category_detail)

        viewModel = ViewModelProviders.of(this).get(FourCgViewModel::class.java)

        binding.fourVM = viewModel

        addFragment(fragment,R.id.mFragmentContainer)
    }
}