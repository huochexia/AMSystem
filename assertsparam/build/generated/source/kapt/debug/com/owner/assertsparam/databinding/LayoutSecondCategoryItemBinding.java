package com.owner.assertsparam.databinding;
import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
public abstract class LayoutSecondCategoryItemBinding extends ViewDataBinding {
    @NonNull
    public final android.widget.ImageView mDeleteSecondCgIv;
    @NonNull
    public final android.widget.RelativeLayout mEditSecondCgll;
    @NonNull
    public final android.widget.CheckBox mSecondCB;
    @NonNull
    public final android.widget.TextView mSecondCategoryNameTv;
    @NonNull
    public final android.support.v7.widget.RecyclerView mThirdCategoryRv;
    @NonNull
    public final android.widget.ImageView mUpdateSecondCgIv;
    // variables
    protected com.owner.assertsparam.data.CategoryInfo mCategory;
    protected com.owner.assertsparam.viewmodel.CategoryFgViewModel mCategoryVM;
    protected com.owner.assertsparam.viewmodel.ThirdCgMoreView mThirdCgMore;
    protected LayoutSecondCategoryItemBinding(@Nullable android.databinding.DataBindingComponent bindingComponent, @Nullable android.view.View root_, int localFieldCount
        , android.widget.ImageView mDeleteSecondCgIv1
        , android.widget.RelativeLayout mEditSecondCgll1
        , android.widget.CheckBox mSecondCB1
        , android.widget.TextView mSecondCategoryNameTv1
        , android.support.v7.widget.RecyclerView mThirdCategoryRv1
        , android.widget.ImageView mUpdateSecondCgIv1
    ) {
        super(bindingComponent, root_, localFieldCount);
        this.mDeleteSecondCgIv = mDeleteSecondCgIv1;
        this.mEditSecondCgll = mEditSecondCgll1;
        this.mSecondCB = mSecondCB1;
        this.mSecondCategoryNameTv = mSecondCategoryNameTv1;
        this.mThirdCategoryRv = mThirdCategoryRv1;
        this.mUpdateSecondCgIv = mUpdateSecondCgIv1;
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
    public abstract void setThirdCgMore(@Nullable com.owner.assertsparam.viewmodel.ThirdCgMoreView ThirdCgMore);
    @Nullable
    public com.owner.assertsparam.viewmodel.ThirdCgMoreView getThirdCgMore() {
        return mThirdCgMore;
    }
    @NonNull
    public static LayoutSecondCategoryItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static LayoutSecondCategoryItemBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static LayoutSecondCategoryItemBinding bind(@NonNull android.view.View view) {
        return null;
    }
    @NonNull
    public static LayoutSecondCategoryItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
    @NonNull
    public static LayoutSecondCategoryItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
    @NonNull
    public static LayoutSecondCategoryItemBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
}