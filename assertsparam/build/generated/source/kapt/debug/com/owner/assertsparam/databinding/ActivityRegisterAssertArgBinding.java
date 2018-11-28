package com.owner.assertsparam.databinding;
import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
public abstract class ActivityRegisterAssertArgBinding extends ViewDataBinding {
    @NonNull
    public final android.widget.FrameLayout mArgumentContainer;
    @NonNull
    public final com.owner.assertsparam.widgets.AssertsParamBottomNavBar mAssertArgumentBNav;
    @NonNull
    public final com.owner.baselibrary.widgets.HeaderBar mRegisterHBar;
    // variables
    protected com.owner.assertsparam.viewmodel.ArgumentViewModel mVm;
    protected ActivityRegisterAssertArgBinding(@Nullable android.databinding.DataBindingComponent bindingComponent, @Nullable android.view.View root_, int localFieldCount
        , android.widget.FrameLayout mArgumentContainer1
        , com.owner.assertsparam.widgets.AssertsParamBottomNavBar mAssertArgumentBNav1
        , com.owner.baselibrary.widgets.HeaderBar mRegisterHBar1
    ) {
        super(bindingComponent, root_, localFieldCount);
        this.mArgumentContainer = mArgumentContainer1;
        this.mAssertArgumentBNav = mAssertArgumentBNav1;
        this.mRegisterHBar = mRegisterHBar1;
    }
    //getters and abstract setters
    public abstract void setVm(@Nullable com.owner.assertsparam.viewmodel.ArgumentViewModel Vm);
    @Nullable
    public com.owner.assertsparam.viewmodel.ArgumentViewModel getVm() {
        return mVm;
    }
    @NonNull
    public static ActivityRegisterAssertArgBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityRegisterAssertArgBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityRegisterAssertArgBinding bind(@NonNull android.view.View view) {
        return null;
    }
    @NonNull
    public static ActivityRegisterAssertArgBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
    @NonNull
    public static ActivityRegisterAssertArgBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
    @NonNull
    public static ActivityRegisterAssertArgBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
}