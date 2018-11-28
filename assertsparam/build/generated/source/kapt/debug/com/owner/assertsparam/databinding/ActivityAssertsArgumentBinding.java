package com.owner.assertsparam.databinding;
import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
public abstract class ActivityAssertsArgumentBinding extends ViewDataBinding {
    @NonNull
    public final com.owner.assertsparam.widgets.AssertsParamBottomNavBar mAssertParamBNav;
    @NonNull
    public final android.widget.FrameLayout mFgContainer;
    @NonNull
    public final com.owner.baselibrary.widgets.HeaderBar mHeaderBar;
    // variables
    protected com.owner.assertsparam.viewmodel.ArgumentViewModel mAssertsParaVm;
    protected ActivityAssertsArgumentBinding(@Nullable android.databinding.DataBindingComponent bindingComponent, @Nullable android.view.View root_, int localFieldCount
        , com.owner.assertsparam.widgets.AssertsParamBottomNavBar mAssertParamBNav1
        , android.widget.FrameLayout mFgContainer1
        , com.owner.baselibrary.widgets.HeaderBar mHeaderBar1
    ) {
        super(bindingComponent, root_, localFieldCount);
        this.mAssertParamBNav = mAssertParamBNav1;
        this.mFgContainer = mFgContainer1;
        this.mHeaderBar = mHeaderBar1;
    }
    //getters and abstract setters
    public abstract void setAssertsParaVm(@Nullable com.owner.assertsparam.viewmodel.ArgumentViewModel AssertsParaVm);
    @Nullable
    public com.owner.assertsparam.viewmodel.ArgumentViewModel getAssertsParaVm() {
        return mAssertsParaVm;
    }
    @NonNull
    public static ActivityAssertsArgumentBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityAssertsArgumentBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityAssertsArgumentBinding bind(@NonNull android.view.View view) {
        return null;
    }
    @NonNull
    public static ActivityAssertsArgumentBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
    @NonNull
    public static ActivityAssertsArgumentBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
    @NonNull
    public static ActivityAssertsArgumentBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
}