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
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.owner.assetsparam.R
import com.owner.assetsparam.data.CategoryInfo
import com.owner.assetsparam.databinding.ActivityFourCategoryDetailBinding
import com.owner.assetsparam.view.fragment.FourCategoryFragment
import com.owner.assetsparam.viewmodel.FourthCategoryViewModel
import com.owner.assetsparam.viewmodel.FourthCategoryViewModelFactory
import com.owner.baselibrary.ext.addFragment
import com.owner.baselibrary.view.activity.BaseActivity
import com.owner.provideslib.router.RouterPath
import kotlinx.android.synthetic.main.activity_four_category_detail.*

/**
 *  四级分类明细,通过ARouter带参数的方式，将相关数据传入, 通过传入的isEdited值决定当前是编辑还是选择
 *  。通过isQuery的值决定是否是查询状态。tableName决定对哪个分类表进行操作，thirdCg是父类
 * Created by Liuyong on 2018-11-17.It's AMSystem
 *@description:
 */
@Route(path = RouterPath.Asset.PATH_ASSETSPARAM_FOUR)
class FourCategoryDetailActivity : BaseActivity<ActivityFourCategoryDetailBinding, FourthCategoryViewModel>() {
    @JvmField
    @Autowired
    var tableName = ""
    @JvmField
    @Autowired
    var isEdited = false
    @JvmField
    @Autowired
    var isQuery = false
    @JvmField
    @Autowired
    var thirdCg :CategoryInfo? = null

    private val fragment by lazy { FourCategoryFragment.newInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ARouter会自动对字段进行赋值，无需主动获取
        ARouter.getInstance().inject(this)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_four_category_detail)

        viewModel = ViewModelProviders.of(this, FourthCategoryViewModelFactory(tableName, isEdited, isQuery, thirdCg!!))
                .get(FourthCategoryViewModel::class.java)

        binding.fourVM = viewModel
        initHeadBar()
        addFragment(fragment, R.id.mFragmentContainer)
    }

    /**
     * 初始化HeadBar
     */
    private fun initHeadBar() {
        mFourDetailHDB.getTitleView().text = thirdCg?.name+"明细"
        if (!isEdited) {
            mFourDetailHDB.getRightView().visibility = View.VISIBLE
            mFourDetailHDB.getRightView().text = "完成"
            mFourDetailHDB.getRightView().setOnClickListener {
                val bundle = Bundle()
                bundle.putParcelable("categoryInfo", viewModel.currentSelected)
                bundle.putString("tableName", tableName)

                val result = Intent()
                result.putExtra("fourthCg", bundle)
                setResult(1, result)
                finish()
            }
        }

    }


}