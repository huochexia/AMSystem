package com.owner.usercenter.ui.activity

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.owner.amsystem.R
import com.owner.amsystem.databinding.ActivityRegisterBinding
import com.owner.baselibrary.ext.enabled
import com.owner.baselibrary.injection.component.ActivityComponent
import com.owner.baselibrary.ui.activity.BaseActivity
import com.owner.usercenter.injection.component.DaggerUserComponent
import com.owner.usercenter.injection.module.UserModule
import com.owner.usercenter.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity<ActivityRegisterBinding, RegisterViewModel>(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        binding.vm = viewModel
        initView()
    }

    /**
     * 增加依赖注入
     */
    override fun initInjection(activityComponent: ActivityComponent) {

        DaggerUserComponent.builder()
                .activityComponent(activityComponent)
                .userModule(UserModule())
                .build()
                .inject(this)

    }

    /**
     * 初始化视图
     */
    private fun initView() {
        mRegisterBtn.enabled(mMobileEt) { isBtnEnable() }
        mRegisterBtn.enabled(mVerifyCodeEt) { isBtnEnable() }
        mRegisterBtn.enabled(mPwdEt) { isBtnEnable() }
        mRegisterBtn.enabled(mPwdConfirmEt) { isBtnEnable() }

    }

    /**
     * 判断四个输入框都不为空时
     */
    fun isBtnEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not() &&
                mVerifyCodeBtn.text.isNullOrEmpty().not() &&
                mPwdEt.text.isNullOrEmpty().not() &&
                mPwdConfirmEt.text.isNullOrEmpty().not()
    }

    /**
     * RegisterView的方法
     */
    fun onRegisterResult(result: String) {

    }

}
