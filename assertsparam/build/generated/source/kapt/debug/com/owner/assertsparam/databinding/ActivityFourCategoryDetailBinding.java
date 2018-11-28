package com.owner.assertsparam.databinding;
import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
public abstract class ActivityFourCategoryDetailBinding extends ViewDataBinding {
    @NonNull
    public final com.owner.baselibrary.widgets.HeaderBar mFourDetailHDB;
    @NonNull
    public final android.widget.FrameLayout mFragmentContainer;
    // variables
    protected com.owner.assertsparam.viewmodel.FourthCategoryViewModel mFourVM;
    protected ActivityFourCategoryDetailBinding(@Nullable android.databinding.DataBindingComponent bindingComponent, @Nullable android.view.View root_, int localFieldCount
        , com.owner.baselibrary.widgets.HeaderBar mFourDetailHDB1
        , android.widget.FrameLayout mFragmentContainer1
    ) {
        super(bindingComponent, root_, localFieldCount);
        this.mFourDetailHDB = mFourDetailHDB1;
        this.mFragmentContainer = mFragmentContainer1;
    }
    //getters and abstract setters
    public abstract void setFourVM(@Nullable com.owner.assertsparam.viewmodel.FourthCategoryViewModel FourVM);
    @Nullable
    public com.owner.assertsparam.viewmodel.FourthCategoryViewModel getFourVM() {
        return mFourVM;
    }
    @NonNull
    public static ActivityFourCategoryDetailBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityFourCategoryDetailBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityFourCategoryDetailBinding bind(@NonNull android.view.View view) {
        return null;
    }
    @NonNull
    public static ActivityFourCategoryDetailBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
    @NonNull
    public static ActivityFourCategoryDetailBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
    @NonNull
    public static ActivityFourCategoryDetailBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
}