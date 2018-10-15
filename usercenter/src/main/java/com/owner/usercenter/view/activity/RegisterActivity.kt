package com.owner.usercenter.view.activity

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.Observable
import android.os.Bundle
import android.widget.Toast
import com.avos.avoscloud.AVException
import com.avos.avoscloud.AVUser
import com.avos.avoscloud.LogInCallback
import com.owner.usercenter.databinding.ActivityRegisterBinding
import com.owner.baselibrary.ext.enabled
import com.owner.baselibrary.view.activity.BaseActivity
import com.owner.provideslib.exception.ExceptionMsg
import com.owner.usercenter.R
import com.owner.usercenter.common.UserConstant
import com.owner.usercenter.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity : BaseActivity<ActivityRegisterBinding, RegisterViewModel>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        binding.vm = viewModel
        initView()
        //通过ViewModel中数据的变化驱动视图显示内容
        with(viewModel.result) {
            addOnPropertyChangedCallback(object :Observable.OnPropertyChangedCallback(){
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    when (get()) {
                        UserConstant.RESULT_INIT_VALUE->{}
                        UserConstant.ACTION_SUCCESS -> finish()
                        else->toast(ExceptionMsg.getError(get()))
                    }
                    set(UserConstant.RESULT_INIT_VALUE)//需要还原，防止连续发生同样的问题时，数据没有变化，不能触发事件
                }
            })
        }
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
