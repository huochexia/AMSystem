package com.owner.usercenter.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.Observable
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.owner.baselibrary.ext.enabled
import com.owner.baselibrary.view.activity.BaseActivity
import com.owner.provideslib.exception.ExceptionMsg
import com.owner.provideslib.router.RouterPath
import com.owner.usercenter.R
import com.owner.usercenter.common.UserConstant
import com.owner.usercenter.databinding.ActivityLoginBinding
import com.owner.usercenter.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

@Route(path = RouterPath.UserCenter.PATH_USER_LOGIN)
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        binding = DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
        binding.vm = viewModel
        viewModel.result.observe(this, Observer {
            toast(it ?: "错误信息不明确！")
        })
        initView()
        //该视图属于嵌套，所以不能用绑定事件
        binding.mHeaderBar.getRightView().setOnClickListener(this)
        //该事件只是一个跳转，所以直接在视图中实现，没有使用绑定
        binding.mForgetPwdTv.setOnClickListener(this)
    }

    /**
     * 初始化图
     */
    private fun initView() {
        //从本地获取上次登录成功的手机号
        mLoginBtn.enabled(mMobileEt) { isEnable() }
        mLoginBtn.enabled(mPwdEt) { isEnable() }
        if (binding.mHeaderBar.getRightView() != null)
            binding.mHeaderBar.getRightView().visibility = View.VISIBLE

    }

    /*
     *判断输入是否完成
     */
    private fun isEnable(): Boolean {
        return !mMobileEt.text.isNullOrEmpty() &&
                !mPwdEt.text.isNullOrEmpty()

    }

    override fun onClick(v: View) {
        when (v) {
            binding.mHeaderBar.getRightView() -> startActivity<RegisterActivity>()
            binding.mForgetPwdTv -> {
                startActivity<ForgetPwdActivity>()
            }
        }
    }

}
