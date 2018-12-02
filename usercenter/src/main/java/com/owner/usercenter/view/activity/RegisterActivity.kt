package com.owner.usercenter.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.owner.baselibrary.ext.enabled
import com.owner.baselibrary.utils.NetWorkUtils
import com.owner.baselibrary.view.Interface.Presenter
import com.owner.baselibrary.view.activity.BaseActivity
import com.owner.provideslib.exception.ExceptionMsg
import com.owner.provideslib.router.RouterPath
import com.owner.usercenter.R
import com.owner.usercenter.common.UserConstant
import com.owner.usercenter.databinding.ActivityRegisterBinding
import com.owner.usercenter.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

@Route(path = RouterPath.UserCenter.PATH_USER_REGISTER)
class RegisterActivity : BaseActivity<ActivityRegisterBinding, RegisterViewModel>(), Presenter {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        binding.apply {
            vm = viewModel.apply {
                userId.observe(this@RegisterActivity, Observer {
                    val intent = Intent()
                    intent.putExtra("userID", it)
                    setResult(2, intent)
                    finish()
                })
            }
            presenter = this@RegisterActivity
        }
        
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

    override fun onClick(view: View) {
        when (view.id) {
            R.id.mRegisterBtn -> register()
        }
    }

    private fun register() {
        if (NetWorkUtils.isNetWorkAvailable(this)) {
            if (viewModel.comparePwd()) {
                viewModel.register().compose(bindToLifecycle())
                        .subscribe({
                            if (!it.isSuccess()) {
                                toast(ExceptionMsg.getError(it.code))
                            }
                        }, {
                            toast(it.message.toString())
                        })
            } else {
                toast(ExceptionMsg.getError(UserConstant.TWO_PASSWORD_NO_SAME))
            }
        } else {
            toast(ExceptionMsg.getError(UserConstant.NET_NO))
        }
    }

}
