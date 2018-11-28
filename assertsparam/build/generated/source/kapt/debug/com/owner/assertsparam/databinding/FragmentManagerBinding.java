package com.owner.assertsparam.databinding;
import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
public abstract class FragmentManagerBinding extends ViewDataBinding {
    @NonNull
    public final android.support.design.widget.FloatingActionButton mAddFAB;
    @NonNull
    public final com.owner.baselibrary.widgets.ClearEditText mFilterEt;
    @NonNull
    public final android.support.v7.widget.RecyclerView mManagerRcv;
    @NonNull
    public final com.kennyc.view.MultiStateView mMultiStateView;
    @NonNull
    public final android.widget.LinearLayout mSearchll;
    @NonNull
    public final com.owner.charsidebar.WaveSideBar mSideBar;
    // variables
    protected com.owner.assertsparam.viewmodel.ManagerViewModel mManagerVm;
    protected FragmentManagerBinding(@Nullable android.databinding.DataBindingComponent bindingComponent, @Nullable android.view.View root_, int localFieldCount
        , android.support.design.widget.FloatingActionButton mAddFAB1
        , com.owner.baselibrary.widgets.ClearEditText mFilterEt1
        , android.support.v7.widget.RecyclerView mManagerRcv1
        , com.kennyc.view.MultiStateView mMultiStateView1
        , android.widget.LinearLayout mSearchll1
        , com.owner.charsidebar.WaveSideBar mSideBar1
    ) {
        super(bindingComponent, root_, localFieldCount);
        this.mAddFAB = mAddFAB1;
        this.mFilterEt = mFilterEt1;
        this.mManagerRcv = mManagerRcv1;
        this.mMultiStateView = mMultiStateView1;
        this.mSearchll = mSearchll1;
        this.mSideBar = mSideBar1;
    }
    //getters and abstract setters
    public abstract void setManagerVm(@Nullable com.owner.assertsparam.viewmodel.ManagerViewModel ManagerVm);
    @Nullable
    public com.owner.assertsparam.viewmodel.ManagerViewModel getManagerVm() {
        return mManagerVm;
    }
    @NonNull
    public static FragmentManagerBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentManagerBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentManagerBinding bind(@NonNull android.view.View view) {
        return null;
    }
    @NonNull
    public static FragmentManagerBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
    @NonNull
    public static FragmentManagerBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
    @NonNull
    public static FragmentManagerBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
}