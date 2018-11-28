package com.owner.assertsparam.databinding;
import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
public abstract class LayoutTopCategoryItemBinding extends ViewDataBinding {
    @NonNull
    public final android.widget.LinearLayout mEditCgLl;
    @NonNull
    public final android.widget.TextView mTopCategoryNameTv;
    // variables
    protected com.owner.assertsparam.data.CategoryInfo mCategory;
    protected com.owner.assertsparam.viewmodel.CategoryFgViewModel mCategoryVM;
    protected LayoutTopCategoryItemBinding(@Nullable android.databinding.DataBindingComponent bindingComponent, @Nullable android.view.View root_, int localFieldCount
        , android.widget.LinearLayout mEditCgLl1
        , android.widget.TextView mTopCategoryNameTv1
    ) {
        super(bindingComponent, root_, localFieldCount);
        this.mEditCgLl = mEditCgLl1;
        this.mTopCategoryNameTv = mTopCategoryNameTv1;
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
    public static LayoutTopCategoryItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static LayoutTopCategoryItemBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static LayoutTopCategoryItemBinding bind(@NonNull android.view.View view) {
        return null;
    }
    @NonNull
    public static LayoutTopCategoryItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
    @NonNull
    public static LayoutTopCategoryItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
    @NonNull
    public static LayoutTopCategoryItemBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
}