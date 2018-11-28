package com.owner.assertsparam.databinding;
import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
public abstract class LayoutManagerItemBinding extends ViewDataBinding {
    @NonNull
    public final android.widget.CheckBox mManagerCB;
    @NonNull
    public final cn.carbs.android.avatarimageview.library.AvatarImageView mUserAvatarIv;
    // variables
    protected com.owner.assertsparam.data.Manager mManager;
    protected com.owner.assertsparam.viewmodel.ManagerViewModel mManagerVM;
    protected LayoutManagerItemBinding(@Nullable android.databinding.DataBindingComponent bindingComponent, @Nullable android.view.View root_, int localFieldCount
        , android.widget.CheckBox mManagerCB1
        , cn.carbs.android.avatarimageview.library.AvatarImageView mUserAvatarIv1
    ) {
        super(bindingComponent, root_, localFieldCount);
        this.mManagerCB = mManagerCB1;
        this.mUserAvatarIv = mUserAvatarIv1;
    }
    //getters and abstract setters
    public abstract void setManager(@Nullable com.owner.assertsparam.data.Manager Manager);
    @Nullable
    public com.owner.assertsparam.data.Manager getManager() {
        return mManager;
    }
    public abstract void setManagerVM(@Nullable com.owner.assertsparam.viewmodel.ManagerViewModel ManagerVM);
    @Nullable
    public com.owner.assertsparam.viewmodel.ManagerViewModel getManagerVM() {
        return mManagerVM;
    }
    @NonNull
    public static LayoutManagerItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static LayoutManagerItemBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static LayoutManagerItemBinding bind(@NonNull android.view.View view) {
        return null;
    }
    @NonNull
    public static LayoutManagerItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
    @NonNull
    public static LayoutManagerItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
    @NonNull
    public static LayoutManagerItemBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
}