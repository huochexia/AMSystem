package com.owner.assertsparam.databinding;
import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
public abstract class FragmentFourCategoryBinding extends ViewDataBinding {
    @NonNull
    public final android.support.v7.widget.RecyclerView mFourDetailRV;
    @NonNull
    public final com.kennyc.view.MultiStateView mStateView;
    // variables
    protected com.owner.assertsparam.viewmodel.FourthCategoryViewModel mFourVM;
    protected FragmentFourCategoryBinding(@Nullable android.databinding.DataBindingComponent bindingComponent, @Nullable android.view.View root_, int localFieldCount
        , android.support.v7.widget.RecyclerView mFourDetailRV1
        , com.kennyc.view.MultiStateView mStateView1
    ) {
        super(bindingComponent, root_, localFieldCount);
        this.mFourDetailRV = mFourDetailRV1;
        this.mStateView = mStateView1;
    }
    //getters and abstract setters
    public abstract void setFourVM(@Nullable com.owner.assertsparam.viewmodel.FourthCategoryViewModel FourVM);
    @Nullable
    public com.owner.assertsparam.viewmodel.FourthCategoryViewModel getFourVM() {
        return mFourVM;
    }
    @NonNull
    public static FragmentFourCategoryBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentFourCategoryBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentFourCategoryBinding bind(@NonNull android.view.View view) {
        return null;
    }
    @NonNull
    public static FragmentFourCategoryBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
    @NonNull
    public static FragmentFourCategoryBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
    @NonNull
    public static FragmentFourCategoryBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
}