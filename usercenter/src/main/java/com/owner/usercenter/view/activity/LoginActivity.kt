package com.owner.usercenter.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.owner.baselibrary.common.AMSystemApp
import com.owner.baselibrary.ext.enabled
import com.owner.baselibrary.utils.NetWorkUtils
import com.owner.baselibrary.view.Interface.Presenter
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
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(), Presenter {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        binding = DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
                .apply {
                    vm = viewModel
                    presenter = this@LoginActivity
                }
        initView()
    }

    /**
     * 初始化图
     */
    private fun initView() {
        //从本地获取上次登录成功的手机号
        mLoginBtn.enabled(mMobileEt) { isEnable() }
        mLoginBtn.enabled(mPwdEt) { isEnable() }

    }

    /*
     *判断输入是否完成
     */
    private fun isEnable(): Boolean {
        return !mMobileEt.text.isNullOrEmpty() &&
                !mPwdEt.text.isNullOrEmpty()

    }

    private fun login() {
        if (NetWorkUtils.isNetWorkAvailable(AMSystemApp.instance)) {

            viewModel.login().compose(bindToLifecycle())
                    .subscribe({
                        if (!it.isSuccess())
                            toast(ExceptionMsg.getError(it.code))
                    }, {
                        dispatchError(it)
                    })
        } else {
            toast(ExceptionMsg.getError(UserConstant.NET_NO))
        }
    }

    private fun dispatchError(error: Throwable?) {
        error?.let {
            toast(it.message.toString())
        }
    }
    override fun onClick(v: View) {
        when (v.id) {
            R.id.mForgetPwdTv -> {
                startActivity<ForgetPwdActivity>()
            }
            R.id.mLoginBtn -> {
                login()
            }
        }
    }

}
