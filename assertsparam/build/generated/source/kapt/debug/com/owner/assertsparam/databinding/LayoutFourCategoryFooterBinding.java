package com.owner.assertsparam.databinding;
import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
public abstract class LayoutFourCategoryFooterBinding extends ViewDataBinding {
    @NonNull
    public final android.widget.ImageView mFourFooterIv;
    // variables
    protected com.owner.assertsparam.viewmodel.FourthCategoryViewModel mFourthVM;
    protected com.owner.assertsparam.data.Footer mFour;
    protected LayoutFourCategoryFooterBinding(@Nullable android.databinding.DataBindingComponent bindingComponent, @Nullable android.view.View root_, int localFieldCount
        , android.widget.ImageView mFourFooterIv1
    ) {
        super(bindingComponent, root_, localFieldCount);
        this.mFourFooterIv = mFourFooterIv1;
    }
    //getters and abstract setters
    public abstract void setFourthVM(@Nullable com.owner.assertsparam.viewmodel.FourthCategoryViewModel FourthVM);
    @Nullable
    public com.owner.assertsparam.viewmodel.FourthCategoryViewModel getFourthVM() {
        return mFourthVM;
    }
    public abstract void setFour(@Nullable com.owner.assertsparam.data.Footer Four);
    @Nullable
    public com.owner.assertsparam.data.Footer getFour() {
        return mFour;
    }
    @NonNull
    public static LayoutFourCategoryFooterBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static LayoutFourCategoryFooterBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static LayoutFourCategoryFooterBinding bind(@NonNull android.view.View view) {
        return null;
    }
    @NonNull
    public static LayoutFourCategoryFooterBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
    @NonNull
    public static LayoutFourCategoryFooterBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
    @NonNull
    public static LayoutFourCategoryFooterBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
}