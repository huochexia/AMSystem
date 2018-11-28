package com.owner.assertsparam.databinding;
import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
public abstract class LayoutImageOfCategoryBinding extends ViewDataBinding {
    @NonNull
    public final android.widget.LinearLayout linearLayout2;
    @NonNull
    public final android.widget.Button mCameraBtn;
    @NonNull
    public final android.widget.LinearLayout mGetPhotoll;
    @NonNull
    public final android.widget.Button mPictureBtn;
    @NonNull
    public final android.widget.ImageView mPictureIv;
    @NonNull
    public final android.widget.EditText mThirdCgNameEt;
    // variables
    protected com.owner.assertsparam.view.fragment.CRUDDialogFragment mMFragment;
    protected LayoutImageOfCategoryBinding(@Nullable android.databinding.DataBindingComponent bindingComponent, @Nullable android.view.View root_, int localFieldCount
        , android.widget.LinearLayout linearLayout21
        , android.widget.Button mCameraBtn1
        , android.widget.LinearLayout mGetPhotoll1
        , android.widget.Button mPictureBtn1
        , android.widget.ImageView mPictureIv1
        , android.widget.EditText mThirdCgNameEt1
    ) {
        super(bindingComponent, root_, localFieldCount);
        this.linearLayout2 = linearLayout21;
        this.mCameraBtn = mCameraBtn1;
        this.mGetPhotoll = mGetPhotoll1;
        this.mPictureBtn = mPictureBtn1;
        this.mPictureIv = mPictureIv1;
        this.mThirdCgNameEt = mThirdCgNameEt1;
    }
    //getters and abstract setters
    public abstract void setMFragment(@Nullable com.owner.assertsparam.view.fragment.CRUDDialogFragment MFragment);
    @Nullable
    public com.owner.assertsparam.view.fragment.CRUDDialogFragment getMFragment() {
        return mMFragment;
    }
    @NonNull
    public static LayoutImageOfCategoryBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static LayoutImageOfCategoryBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static LayoutImageOfCategoryBinding bind(@NonNull android.view.View view) {
        return null;
    }
    @NonNull
    public static LayoutImageOfCategoryBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
    @NonNull
    public static LayoutImageOfCategoryBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
    @NonNull
    public static LayoutImageOfCategoryBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
}