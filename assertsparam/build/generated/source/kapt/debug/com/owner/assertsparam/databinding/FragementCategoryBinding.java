package com.owner.assertsparam.databinding;
import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
public abstract class FragementCategoryBinding extends ViewDataBinding {
    @NonNull
    public final android.widget.TextView mAddSecondCgTv;
    @NonNull
    public final com.kennyc.view.MultiStateView mMultiStateTopView;
    @NonNull
    public final com.kennyc.view.MultiStateView mMultiStateView;
    @NonNull
    public final android.widget.ImageView mSecondCategoryIv;
    @NonNull
    public final android.support.v7.widget.RecyclerView mSecondCategoryRv;
    @NonNull
    public final android.support.v7.widget.RecyclerView mTopCategoryRv;
    // variables
    protected com.owner.assertsparam.data.CategoryInfo mCategory;
    protected com.owner.assertsparam.viewmodel.CategoryFgViewModel mCategoryVM;
    protected FragementCategoryBinding(@Nullable android.databinding.DataBindingComponent bindingComponent, @Nullable android.view.View root_, int localFieldCount
        , android.widget.TextView mAddSecondCgTv1
        , com.kennyc.view.MultiStateView mMultiStateTopView1
        , com.kennyc.view.MultiStateView mMultiStateView1
        , android.widget.ImageView mSecondCategoryIv1
        , android.support.v7.widget.RecyclerView mSecondCategoryRv1
        , android.support.v7.widget.RecyclerView mTopCategoryRv1
    ) {
        super(bindingComponent, root_, localFieldCount);
        this.mAddSecondCgTv = mAddSecondCgTv1;
        this.mMultiStateTopView = mMultiStateTopView1;
        this.mMultiStateView = mMultiStateView1;
        this.mSecondCategoryIv = mSecondCategoryIv1;
        this.mSecondCategoryRv = mSecondCategoryRv1;
        this.mTopCategoryRv = mTopCategoryRv1;
    }
    //getters and abstract setters
    public abstract void setCategory(@Nullable com.owner.assertsparam.data.CategoryInfo Category);
    @Nullable
    public com.owner.assertsparam.data.CategoryInfo getCategory() {
        return mCategory;
    }
    public abstract void setCategoryVM(@Nullable com.owner.assertsparam.viewmodel.CategoryFgViewModel CategoryVM);
    @Nullable
    public com.owner.assertsparam.viewmodel.CategoryFgViewModel getCategoryVM() {
        return mCategoryVM;
    }
    @NonNull
    public static FragementCategoryBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragementCategoryBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragementCategoryBinding bind(@NonNull android.view.View view) {
        return null;
    }
    @NonNull
    public static FragementCategoryBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
    @NonNull
    public static FragementCategoryBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
    @NonNull
    public static FragementCategoryBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
}