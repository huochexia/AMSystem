package com.owner.usercenter.ui.activity

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.owner.usercenter.databinding.ActivityForgetPwdBinding
import com.owner.baselibrary.ext.enabled
import com.owner.baselibrary.ui.activity.BaseActivity
import com.owner.usercenter.R
import com.owner.usercenter.viewmodel.ForgetPwdViewModel
import kotlinx.android.synthetic.main.activity_forget_pwd.*

class ForgetPwdActivity : BaseActivity<ActivityForgetPwdBinding, ForgetPwdViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ForgetPwdViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_forget_pwd)
        binding.vm = viewModel
        initView()
    }

    private fun initView() {
        mNextBtn.enabled(mMobileEt) { isBtnEnable() }
        mNextBtn.enabled(mVerifyCodeEt) { isBtnEnable() }
    }

    private fun isBtnEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not() &&
                mVerifyCodeEt.text.isNullOrEmpty().not()

    }
}
