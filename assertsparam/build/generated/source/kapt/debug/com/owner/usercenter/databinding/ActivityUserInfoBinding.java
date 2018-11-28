package com.owner.usercenter.databinding;
import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
public abstract class ActivityUserInfoBinding extends ViewDataBinding {
    @NonNull
    public final android.widget.ImageView mArrowIv;
    @NonNull
    public final android.widget.RadioButton mGenderFemaleRb;
    @NonNull
    public final android.widget.RadioButton mGenderMaleRb;
    @NonNull
    public final com.owner.baselibrary.widgets.HeaderBar mHeaderBar;
    @NonNull
    public final cn.carbs.android.avatarimageview.library.AvatarImageView mUserAvatarIv;
    @NonNull
    public final android.widget.RelativeLayout mUserIconView;
    @NonNull
    public final android.widget.TextView mUserMobileTv;
    @NonNull
    public final android.widget.EditText mUserNameEt;
    @NonNull
    public final android.widget.EditText mUserSignEt;
    // variables
    protected com.owner.usercenter.viewmodel.UserInfoViewModel mInfovm;
    protected ActivityUserInfoBinding(@Nullable android.databinding.DataBindingComponent bindingComponent, @Nullable android.view.View root_, int localFieldCount
        , android.widget.ImageView mArrowIv1
        , android.widget.RadioButton mGenderFemaleRb1
        , android.widget.RadioButton mGenderMaleRb1
        , com.owner.baselibrary.widgets.HeaderBar mHeaderBar1
        , cn.carbs.android.avatarimageview.library.AvatarImageView mUserAvatarIv1
        , android.widget.RelativeLayout mUserIconView1
        , android.widget.TextView mUserMobileTv1
        , android.widget.EditText mUserNameEt1
        , android.widget.EditText mUserSignEt1
    ) {
        super(bindingComponent, root_, localFieldCount);
        this.mArrowIv = mArrowIv1;
        this.mGenderFemaleRb = mGenderFemaleRb1;
        this.mGenderMaleRb = mGenderMaleRb1;
        this.mHeaderBar = mHeaderBar1;
        this.mUserAvatarIv = mUserAvatarIv1;
        this.mUserIconView = mUserIconView1;
        this.mUserMobileTv = mUserMobileTv1;
        this.mUserNameEt = mUserNameEt1;
        this.mUserSignEt = mUserSignEt1;
    }
    //getters and abstract setters
    public abstract void setInfovm(@Nullable com.owner.usercenter.viewmodel.UserInfoViewModel Infovm);
    @Nullable
    public com.owner.usercenter.viewmodel.UserInfoViewModel getInfovm() {
        return mInfovm;
    }
    @NonNull
    public static ActivityUserInfoBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityUserInfoBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityUserInfoBinding bind(@NonNull android.view.View view) {
        return null;
    }
    @NonNull
    public static ActivityUserInfoBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
    @NonNull
    public static ActivityUserInfoBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
    @NonNull
    public static ActivityUserInfoBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
}