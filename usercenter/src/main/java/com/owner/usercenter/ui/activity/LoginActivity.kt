package com.owner.usercenter.ui.activity

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.Observable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.avos.avoscloud.AVOSCloud
import com.owner.amsystem.R
import com.owner.amsystem.databinding.ActivityLoginBinding
import com.owner.baselibrary.common.AMSystemApp
import com.owner.baselibrary.common.AppManager
import com.owner.baselibrary.common.BaseConstant
import com.owner.baselibrary.ext.enabled
import com.owner.baselibrary.ui.activity.BaseActivity
import com.owner.usercenter.common.UserConstant
import com.owner.usercenter.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        binding = DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
        binding.vm = viewModel
        initView()
        //该视图属于嵌套，所以不能用绑定事件
        binding.mHeaderBar.getRightView().setOnClickListener(this)
        //该事件只是一个跳转，所以直接在视图中实现，没有使用绑定
        binding.mForgetPwdTv.setOnClickListener(this)

        with(viewModel.result) {
            addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    when (get()) {
                        UserConstant.NET_NOUSER -> toast("网络不可用")
                        210 -> toast("用户名与密码不匹配！")
                        211 -> toast("用户不存在！")
                    }
                    set(-2)//需要还原原值，否则连续出现同一个值无法触发事件
                }
            })
        }
    }

    /**
     * 初始化图
     */
    fun initView() {
        mLoginBtn.enabled(mMobileEt) { isEnable() }
        mLoginBtn.enabled(mPwdEt) { isEnable() }
        if (binding.mHeaderBar.getRightView() != null)
            binding.mHeaderBar.getRightView().visibility = View.VISIBLE

    }

    /*
     *判断输入是否完成
     */
    fun isEnable(): Boolean {
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

    /*
        点击返回的初始时间
      */
    private var pressTime: Long = 0

    override fun onBackPressed() {

        var time = System.currentTimeMillis()
        if (time - pressTime > 2000) {
            toast("再按一次退出应用程序")
            pressTime = time
        } else {
            AppManager.instance.exitApp(this)
        }

    }
}
