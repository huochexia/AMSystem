package com.owner.assertsparam.databinding;
import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
public abstract class LayoutThirdCategoryFooterBinding extends ViewDataBinding {
    @NonNull
    public final android.widget.ImageView mThirdCategoryIv;
    // variables
    protected com.owner.assertsparam.viewmodel.CategoryFgViewModel mCategoryFgVM;
    protected com.owner.assertsparam.data.Footer mData;
    protected LayoutThirdCategoryFooterBinding(@Nullable android.databinding.DataBindingComponent bindingComponent, @Nullable android.view.View root_, int localFieldCount
        , android.widget.ImageView mThirdCategoryIv1
    ) {
        super(bindingComponent, root_, localFieldCount);
        this.mThirdCategoryIv = mThirdCategoryIv1;
    }
    //getters and abstract setters
    public abstract void setCategoryFgVM(@Nullable com.owner.assertsparam.viewmodel.CategoryFgViewModel CategoryFgVM);
    @Nullable
    public com.owner.assertsparam.viewmodel.CategoryFgViewModel getCategoryFgVM() {
        return mCategoryFgVM;
    }
    public abstract void setData(@Nullable com.owner.assertsparam.data.Footer Data);
    @Nullable
    public com.owner.assertsparam.data.Footer getData() {
        return mData;
    }
    @NonNull
    public static LayoutThirdCategoryFooterBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static LayoutThirdCategoryFooterBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static LayoutThirdCategoryFooterBinding bind(@NonNull android.view.View view) {
        return null;
    }
    @NonNull
    public static LayoutThirdCategoryFooterBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
    @NonNull
    public static LayoutThirdCategoryFooterBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
    @NonNull
    public static LayoutThirdCategoryFooterBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
}