package com.owner.assertsparam.databinding;
import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
public abstract class LayoutFourCategoryItemBinding extends ViewDataBinding {
    @NonNull
    public final android.widget.Button mDeleteFourTv;
    @NonNull
    public final android.widget.LinearLayout mEditFourCgll;
    @NonNull
    public final android.widget.ImageView mFourCategoryIv;
    @NonNull
    public final android.widget.TextView mFourCategoryNameTv;
    @NonNull
    public final android.widget.LinearLayout mFourCgll;
    @NonNull
    public final android.widget.Button mUpdateFourTv;
    // variables
    protected com.owner.assertsparam.viewmodel.FourthCategoryViewModel mFourthVM;
    protected com.owner.assertsparam.data.CategoryInfo mFour;
    protected LayoutFourCategoryItemBinding(@Nullable android.databinding.DataBindingComponent bindingComponent, @Nullable android.view.View root_, int localFieldCount
        , android.widget.Button mDeleteFourTv1
        , android.widget.LinearLayout mEditFourCgll1
        , android.widget.ImageView mFourCategoryIv1
        , android.widget.TextView mFourCategoryNameTv1
        , android.widget.LinearLayout mFourCgll1
        , android.widget.Button mUpdateFourTv1
    ) {
        super(bindingComponent, root_, localFieldCount);
        this.mDeleteFourTv = mDeleteFourTv1;
        this.mEditFourCgll = mEditFourCgll1;
        this.mFourCategoryIv = mFourCategoryIv1;
        this.mFourCategoryNameTv = mFourCategoryNameTv1;
        this.mFourCgll = mFourCgll1;
        this.mUpdateFourTv = mUpdateFourTv1;
    }
    //getters and abstract setters
    public abstract void setFourthVM(@Nullable com.owner.assertsparam.viewmodel.FourthCategoryViewModel FourthVM);
    @Nullable
    public com.owner.assertsparam.viewmodel.FourthCategoryViewModel getFourthVM() {
        return mFourthVM;
    }
    public abstract void setFour(@Nullable com.owner.assertsparam.data.CategoryInfo Four);
    @Nullable
    public com.owner.assertsparam.data.CategoryInfo getFour() {
        return mFour;
    }
    @NonNull
    public static LayoutFourCategoryItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static LayoutFourCategoryItemBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static LayoutFourCategoryItemBinding bind(@NonNull android.view.View view) {
        return null;
    }
    @NonNull
    public static LayoutFourCategoryItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
    @NonNull
    public static LayoutFourCategoryItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
    @NonNull
    public static LayoutFourCategoryItemBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
}