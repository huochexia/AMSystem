package com.owner.usercenter.databinding;
import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
public abstract class ActivityResetPwdBinding extends ViewDataBinding {
    @NonNull
    public final android.widget.Button mConfirmBtn;
    @NonNull
    public final com.owner.baselibrary.widgets.HeaderBar mHeaderBar;
    @NonNull
    public final android.widget.EditText mPwdConfirmEt;
    @NonNull
    public final android.widget.EditText mPwdEt;
    // variables
    protected com.owner.usercenter.viewmodel.ResetPwdViewModel mVm;
    protected ActivityResetPwdBinding(@Nullable android.databinding.DataBindingComponent bindingComponent, @Nullable android.view.View root_, int localFieldCount
        , android.widget.Button mConfirmBtn1
        , com.owner.baselibrary.widgets.HeaderBar mHeaderBar1
        , android.widget.EditText mPwdConfirmEt1
        , android.widget.EditText mPwdEt1
    ) {
        super(bindingComponent, root_, localFieldCount);
        this.mConfirmBtn = mConfirmBtn1;
        this.mHeaderBar = mHeaderBar1;
        this.mPwdConfirmEt = mPwdConfirmEt1;
        this.mPwdEt = mPwdEt1;
    }
    //getters and abstract setters
    public abstract void setVm(@Nullable com.owner.usercenter.viewmodel.ResetPwdViewModel Vm);
    @Nullable
    public com.owner.usercenter.viewmodel.ResetPwdViewModel getVm() {
        return mVm;
    }
    @NonNull
    public static ActivityResetPwdBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityResetPwdBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityResetPwdBinding bind(@NonNull android.view.View view) {
        return null;
    }
    @NonNull
    public static ActivityResetPwdBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
    @NonNull
    public static ActivityResetPwdBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
    @NonNull
    public static ActivityResetPwdBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
}