package com.owner.usercenter.databinding;
import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
public abstract class ActivityForgetPwdBinding extends ViewDataBinding {
    @NonNull
    public final com.owner.baselibrary.widgets.HeaderBar mHeaderBar;
    @NonNull
    public final android.widget.EditText mMobileEt;
    @NonNull
    public final android.widget.Button mNextBtn;
    @NonNull
    public final com.owner.baselibrary.widgets.VerifyButton mVerifyCodeBtn;
    @NonNull
    public final android.widget.EditText mVerifyCodeEt;
    // variables
    protected com.owner.usercenter.viewmodel.ForgetPwdViewModel mVm;
    protected ActivityForgetPwdBinding(@Nullable android.databinding.DataBindingComponent bindingComponent, @Nullable android.view.View root_, int localFieldCount
        , com.owner.baselibrary.widgets.HeaderBar mHeaderBar1
        , android.widget.EditText mMobileEt1
        , android.widget.Button mNextBtn1
        , com.owner.baselibrary.widgets.VerifyButton mVerifyCodeBtn1
        , android.widget.EditText mVerifyCodeEt1
    ) {
        super(bindingComponent, root_, localFieldCount);
        this.mHeaderBar = mHeaderBar1;
        this.mMobileEt = mMobileEt1;
        this.mNextBtn = mNextBtn1;
        this.mVerifyCodeBtn = mVerifyCodeBtn1;
        this.mVerifyCodeEt = mVerifyCodeEt1;
    }
    //getters and abstract setters
    public abstract void setVm(@Nullable com.owner.usercenter.viewmodel.ForgetPwdViewModel Vm);
    @Nullable
    public com.owner.usercenter.viewmodel.ForgetPwdViewModel getVm() {
        return mVm;
    }
    @NonNull
    public static ActivityForgetPwdBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityForgetPwdBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityForgetPwdBinding bind(@NonNull android.view.View view) {
        return null;
    }
    @NonNull
    public static ActivityForgetPwdBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
    @NonNull
    public static ActivityForgetPwdBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
    @NonNull
    public static ActivityForgetPwdBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
}