package com.owner.usercenter.view.activity

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.Observable
import android.os.Bundle
import com.owner.usercenter.databinding.ActivityResetPwdBinding
import com.owner.baselibrary.ext.enabled
import com.owner.baselibrary.view.activity.BaseActivity
import com.owner.usercenter.R
import com.owner.usercenter.common.UserConstant
import com.owner.usercenter.viewmodel.ResetPwdViewModel
import kotlinx.android.synthetic.main.activity_reset_pwd.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class ResetPwdActivity : BaseActivity<ActivityResetPwdBinding, ResetPwdViewModel>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ResetPwdViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_reset_pwd)
        binding.vm = viewModel
        initView()
        val bundle = intent.extras
        viewModel.verifyCode = bundle.getString("verifyCode")

        with(viewModel.result) {
            addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    when (get()) {
                        UserConstant.TWO_PASSWORD_NO_SAME -> toast("两次密码不一致")
                        UserConstant.INVALID_VERIFY_CODE ->  toast("验证码无效")
                        UserConstant.ACTION_SUCCESS -> {
                            toast("密码重置成功")
                            startActivity<LoginActivity>()
                        }
                    }
                    //恢复原值，为下一次变化准备
                    set(-1)
                }
            })
        }
    }

    private fun initView() {
        mConfirmBtn.enabled(mPwdEt) { isBtnEnable() }
        mConfirmBtn.enabled(mPwdConfirmEt) { isBtnEnable() }
    }

    private fun isBtnEnable(): Boolean {
        return mPwdEt.text.isNullOrEmpty().not() &&
                mPwdConfirmEt.text.isNullOrEmpty().not()

    }
}
