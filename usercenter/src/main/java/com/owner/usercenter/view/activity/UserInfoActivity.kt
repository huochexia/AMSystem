package com.owner.usercenter.view.activity

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.owner.baselibrary.view.activity.BaseActivity
import com.owner.provideslib.router.RouterPath
import com.owner.usercenter.R
import com.owner.usercenter.databinding.ActivityUserInfoBinding
import com.owner.usercenter.viewmodel.UserInfoViewModel


@Route(path = RouterPath.UserCenter.PATH_USER_INFO)
class UserInfoActivity : BaseActivity<ActivityUserInfoBinding, UserInfoViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(UserInfoViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_info)
        binding.infovm = viewModel
    }
}
