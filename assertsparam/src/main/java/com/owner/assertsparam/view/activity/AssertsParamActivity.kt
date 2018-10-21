package com.owner.assertsparam.view.activity

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.databinding.ViewDataBinding
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.owner.assertsparam.R
import com.owner.assertsparam.view.fragment.CategoryFragment
import com.owner.assertsparam.viewmodel.AssertsParaViewModel
import com.owner.baselibrary.ext.addFragment
import com.owner.baselibrary.view.activity.BaseActivity
import com.owner.baselibrary.viewmodel.BaseViewModel

class AssertsParamActivity : BaseActivity<ViewDataBinding,BaseViewModel<*>>() {

    private val fragment = CategoryFragment()

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asserts_param)
        viewModel = ViewModelProviders.of(this).get(AssertsParaViewModel::class.java)

        addFragment(fragment,R.id.mFgContainer)

    }
}
