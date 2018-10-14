package com.owner.amsystem.view.activity

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.owner.amsystem.R
import com.owner.amsystem.databinding.ActivitySettingBinding
import com.owner.amsystem.viewmodel.SettingViewModel
import com.owner.baselibrary.view.activity.BaseActivity
import com.owner.usercenter.utils.UserUtils
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : BaseActivity<ActivitySettingBinding, SettingViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(SettingViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_setting)
        binding.setvm = viewModel

        mLogoutBtn.setOnClickListener {
            UserUtils.putUserInfo(null)
            finish()
        }
    }

}
