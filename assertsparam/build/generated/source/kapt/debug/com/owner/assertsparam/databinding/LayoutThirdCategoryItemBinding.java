package com.owner.assertsparam.databinding;
import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
public abstract class LayoutThirdCategoryItemBinding extends ViewDataBinding {
    @NonNull
    public final android.widget.Button mDeleteThirdTv;
    @NonNull
    public final android.widget.LinearLayout mEditThirdCgll;
    @NonNull
    public final android.widget.ImageView mThirdCategoryIv;
    @NonNull
    public final android.widget.TextView mThirdCategoryNameTv;
    @NonNull
    public final android.widget.LinearLayout mThirdCgll;
    @NonNull
    public final android.widget.Button mUpdateThirdTv;
    // variables
    protected com.owner.assertsparam.viewmodel.CategoryFgViewModel mCategoryFgVM;
    protected com.owner.assertsparam.data.CategoryInfo mData;
    protected LayoutThirdCategoryItemBinding(@Nullable android.databinding.DataBindingComponent bindingComponent, @Nullable android.view.View root_, int localFieldCount
        , android.widget.Button mDeleteThirdTv1
        , android.widget.LinearLayout mEditThirdCgll1
        , android.widget.ImageView mThirdCategoryIv1
        , android.widget.TextView mThirdCategoryNameTv1
        , android.widget.LinearLayout mThirdCgll1
        , android.widget.Button mUpdateThirdTv1
    ) {
        super(bindingComponent, root_, localFieldCount);
        this.mDeleteThirdTv = mDeleteThirdTv1;
        this.mEditThirdCgll = mEditThirdCgll1;
        this.mThirdCategoryIv = mThirdCategoryIv1;
        this.mThirdCategoryNameTv = mThirdCategoryNameTv1;
        this.mThirdCgll = mThirdCgll1;
        this.mUpdateThirdTv = mUpdateThirdTv1;
    }
    //getters and abstract setters
    public abstract void setCategoryFgVM(@Nullable com.owner.assertsparam.viewmodel.CategoryFgViewModel CategoryFgVM);
    @Nullable
    public com.owner.assertsparam.viewmodel.CategoryFgViewModel getCategoryFgVM() {
        return mCategoryFgVM;
    }
    public abstract void setData(@Nullable com.owner.assertsparam.data.CategoryInfo Data);
    @Nullable
    public com.owner.assertsparam.data.CategoryInfo getData() {
        return mData;
    }
    @NonNull
    public static LayoutThirdCategoryItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static LayoutThirdCategoryItemBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static LayoutThirdCategoryItemBinding bind(@NonNull android.view.View view) {
        return null;
    }
    @NonNull
    public static LayoutThirdCategoryItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
    @NonNull
    public static LayoutThirdCategoryItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
    @NonNull
    public static LayoutThirdCategoryItemBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
}