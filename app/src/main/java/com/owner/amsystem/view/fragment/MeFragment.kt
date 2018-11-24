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

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.owner.amsystem.R
import com.owner.amsystem.databinding.FragmentMeBinding
import com.owner.amsystem.viewmodel.MeViewModel
import com.owner.baselibrary.ext.loadWithGlide
import com.owner.baselibrary.view.fragment.BaseFragment
import com.owner.provideslib.common.isLogined
import kotlinx.android.synthetic.main.fragment_me.*

/**
 *
 * Created by Liuyong on 2018-10-10.It's AMSystem
 *@description:
 */
class MeFragment : BaseFragment<FragmentMeBinding, MeViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MeViewModel::class.java)}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMeBinding.inflate(inflater, container, false)
        binding.mevm = viewModel

        initViewModel()
        return binding.root
    }

    private fun initViewModel() {
        //观察头像的变化
        viewModel.avatar.observe(this, Observer {

            if (!isLogined())
                mUserAvatarIv.setImageResource(R.drawable.icon_default_user)
            else {
                val username = viewModel.username.value
                mUserAvatarIv.loadWithGlide(it,username!!.first())
            }
        })
        //观察用户名的变化
        viewModel.username.observe(this, Observer {
            if (!isLogined()) {
                mUserNameTv.text = getString(R.string.un_login_text)
            } else {
                mUserNameTv.text = it
            }
        })
    }

    override fun onStart() {
        super.onStart()
        //从本地获取数据
        viewModel.getSPData()
    }

}