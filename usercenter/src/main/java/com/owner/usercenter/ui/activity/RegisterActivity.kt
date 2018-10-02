package com.owner.usercenter.ui.activity

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.Observable
import android.os.Bundle
import com.avos.avoscloud.AVException
import com.avos.avoscloud.AVUser
import com.avos.avoscloud.LogInCallback
import com.owner.amsystem.R
import com.owner.amsystem.databinding.ActivityRegisterBinding
import com.owner.baselibrary.common.AppManager
import com.owner.baselibrary.ext.enabled
import com.owner.baselibrary.ui.activity.BaseActivity
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
            addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    when (get()) {
                        UserConstant.NET_NOUSER -> toast("网络不可用")
                        UserConstant.TWO_PASSWORD_NO_SAME -> toast("两次密码不一致")
                        UserConstant.USERNAME_TAKEN -> toast("用户名已存在")
                        UserConstant.ACTION_SUCCESS -> {
                            //注册成功后，直接登录
                            AVUser.logInInBackground(mMobileEt.text.toString(),
                                    mPwdEt.text.toString(),object :LogInCallback<AVUser>(){
                                override fun done(p0: AVUser?, p1: AVException?) {
                                    toast("登录成功")
                                    finish()
//                                    AppManager.instance.finishAllActivity()
//                                    startActivity<>()
                                }
                            })
                        }
                    }
                    //为了保证数据变化驱动，所以要每次变化后还原原值
                    set(-1)
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
