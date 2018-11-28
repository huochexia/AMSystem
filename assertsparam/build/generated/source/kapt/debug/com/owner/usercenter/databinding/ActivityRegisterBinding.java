package com.owner.usercenter.databinding;
import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
public abstract class ActivityRegisterBinding extends ViewDataBinding {
    @NonNull
    public final com.owner.baselibrary.widgets.HeaderBar mHeaderBar;
    @NonNull
    public final android.widget.EditText mMobileEt;
    @NonNull
    public final android.widget.EditText mPwdConfirmEt;
    @NonNull
    public final android.widget.EditText mPwdEt;
    @NonNull
    public final android.widget.Button mRegisterBtn;
    @NonNull
    public final android.widget.EditText mVerifyCodeEt;
    // variables
    protected com.owner.usercenter.viewmodel.RegisterViewModel mVm;
    protected ActivityRegisterBinding(@Nullable android.databinding.DataBindingComponent bindingComponent, @Nullable android.view.View root_, int localFieldCount
        , com.owner.baselibrary.widgets.HeaderBar mHeaderBar1
        , android.widget.EditText mMobileEt1
        , android.widget.EditText mPwdConfirmEt1
        , android.widget.EditText mPwdEt1
        , android.widget.Button mRegisterBtn1
        , android.widget.EditText mVerifyCodeEt1
    ) {
        super(bindingComponent, root_, localFieldCount);
        this.mHeaderBar = mHeaderBar1;
        this.mMobileEt = mMobileEt1;
        this.mPwdConfirmEt = mPwdConfirmEt1;
        this.mPwdEt = mPwdEt1;
        this.mRegisterBtn = mRegisterBtn1;
        this.mVerifyCodeEt = mVerifyCodeEt1;
    }
    //getters and abstract setters
    public abstract void setVm(@Nullable com.owner.usercenter.viewmodel.RegisterViewModel Vm);
    @Nullable
    public com.owner.usercenter.viewmodel.RegisterViewModel getVm() {
        return mVm;
    }
    @NonNull
    public static ActivityRegisterBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityRegisterBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityRegisterBinding bind(@NonNull android.view.View view) {
        return null;
    }
    @NonNull
    public static ActivityRegisterBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
    @NonNull
    public static ActivityRegisterBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
    @NonNull
    public static ActivityRegisterBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
}