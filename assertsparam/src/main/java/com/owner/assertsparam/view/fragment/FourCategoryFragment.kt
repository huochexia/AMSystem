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
package com.owner.assertsparam.view.fragment

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.owner.assertsparam.databinding.FragmentFourCategoryBinding
import com.owner.assertsparam.viewmodel.FourCgViewModel
import com.owner.baselibrary.view.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_four_category.*

/**
 *
 * Created by Liuyong on 2018-11-17.It's AMSystem
 *@description:
 */
class FourCategoryFragment : BaseFragment<FragmentFourCategoryBinding, FourCgViewModel>() {

    companion object {
        fun newInstance(): FourCategoryFragment = FourCategoryFragment().apply {
            return this
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(FourCgViewModel::class.java)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentFourCategoryBinding.inflate(inflater, container, false)

        binding.fourVM = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mFourDetailHDB.getTitleView().text = "分类明细"
    }
}