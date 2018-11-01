package com.owner.usercenter.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.Observable
import android.os.Bundle
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.avos.avoscloud.AVException
import com.avos.avoscloud.AVUser
import com.avos.avoscloud.LogInCallback
import com.owner.usercenter.databinding.ActivityRegisterBinding
import com.owner.baselibrary.ext.enabled
import com.owner.baselibrary.view.activity.BaseActivity
import com.owner.provideslib.exception.ExceptionMsg
import com.owner.provideslib.router.RouterPath
import com.owner.usercenter.R
import com.owner.usercenter.common.UserConstant
import com.owner.usercenter.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast
@Route(path = RouterPath.UserCenter.PATH_USER_REGISTER)
class RegisterActivity : BaseActivity<ActivityRegisterBinding, RegisterViewModel>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        binding.vm = viewModel
        viewModel.error.observe(this, Observer {
            toast(it?:"错误信息不明确！")
        })
        initView()

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
    private fun isBtnEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not() &&
                mPwdEt.text.isNullOrEmpty().not() &&
                mPwdConfirmEt.text.isNullOrEmpty().not()
    }


}
