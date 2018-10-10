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
package com.owner.amsystem.ui.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.owner.amsystem.databinding.FragmentHomeBinding
import com.owner.amsystem.ui.adapter.TopicAdapter
import com.owner.amsystem.viewmodel.HomeViewModel
import com.owner.baselibrary.ui.fragment.BaseFragment
import com.owner.baselibrary.widgets.BannerImageLoader
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home.*
import me.crosswall.lib.coverflow.CoverFlow

/**
 *
 * Created by Liuyong on 2018-10-10.It's AMSystem
 *@description:
 */
class HomeFragment:BaseFragment<FragmentHomeBinding,HomeViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = HomeViewModel()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        binding.vm = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBanner()
        initTopic()
    }

    /**
     * 初始化Topic画廊
     */
    private fun initTopic() {
        mTopicPager.adapter = TopicAdapter(context!!,viewModel.topic)
        mTopicPager.currentItem = 1
        mTopicPager.offscreenPageLimit = 5

        CoverFlow.Builder()
                .with(mTopicPager)
                .scale(0.3f)
                .pagerMargin(-30f)
                .spaceSize(0f)
                .build()
    }

    /**
     * 初始化Banner
     */
    private fun initBanner() {
        mHomeBanner.setImageLoader(BannerImageLoader())
        mHomeBanner.setImages(viewModel.banner)
        mHomeBanner.setBannerAnimation(Transformer.Accordion)
        mHomeBanner.setDelayTime(2000)
        mHomeBanner.setIndicatorGravity(BannerConfig.CENTER)
        mHomeBanner.start()
    }


}