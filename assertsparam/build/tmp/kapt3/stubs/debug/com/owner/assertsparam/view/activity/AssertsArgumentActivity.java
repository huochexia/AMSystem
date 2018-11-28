package com.owner.assertsparam.view.activity;

import java.lang.System;

@com.alibaba.android.arouter.facade.annotation.Route(path = "/assertsparam/main")
@kotlin.Metadata(mv = {1, 1, 11}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0004B\u0005\u00a2\u0006\u0002\u0010\u0005J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0002J\b\u0010\"\u001a\u00020\u001fH\u0002J\b\u0010#\u001a\u00020\u001fH\u0002J\"\u0010$\u001a\u00020\u001f2\u0006\u0010%\u001a\u00020!2\u0006\u0010&\u001a\u00020!2\b\u0010\'\u001a\u0004\u0018\u00010(H\u0014J\u0012\u0010)\u001a\u00020\u001f2\b\u0010*\u001a\u0004\u0018\u00010+H\u0015J\u0018\u0010,\u001a\u00020\u001f2\u0006\u0010-\u001a\u00020\u001c2\u0006\u0010.\u001a\u00020/H\u0016R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001b\u0010\f\u001a\u00020\u00078BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000e\u0010\u000b\u001a\u0004\b\r\u0010\tR\u001b\u0010\u000f\u001a\u00020\u00078BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0011\u0010\u000b\u001a\u0004\b\u0010\u0010\tR\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0015\u001a\u00020\u00168BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0019\u0010\u000b\u001a\u0004\b\u0017\u0010\u0018R\u0016\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bX\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u001d\u00a8\u00060"}, d2 = {"Lcom/owner/assertsparam/view/activity/AssertsArgumentActivity;", "Lcom/owner/baselibrary/view/activity/BaseActivity;", "Landroid/databinding/ViewDataBinding;", "Lcom/owner/assertsparam/viewmodel/ArgumentViewModel;", "Lcom/owner/assertsparam/Interface/QueryAssertsInfo;", "()V", "categoryFragment", "Lcom/owner/assertsparam/view/fragment/CategoryFragment;", "getCategoryFragment", "()Lcom/owner/assertsparam/view/fragment/CategoryFragment;", "categoryFragment$delegate", "Lkotlin/Lazy;", "departmentFragment", "getDepartmentFragment", "departmentFragment$delegate", "locationFragment", "getLocationFragment", "locationFragment$delegate", "mStack", "Ljava/util/Stack;", "Landroid/support/v4/app/Fragment;", "managerFragment", "Lcom/owner/assertsparam/view/fragment/ManagerFragment;", "getManagerFragment", "()Lcom/owner/assertsparam/view/fragment/ManagerFragment;", "managerFragment$delegate", "titles", "", "", "[Ljava/lang/String;", "changeFragment", "", "position", "", "initBottomNav", "initFragment", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "queryAssert", "tablename", "condition", "", "assertsparam_debug"})
public final class AssertsArgumentActivity extends com.owner.baselibrary.view.activity.BaseActivity<android.databinding.ViewDataBinding, com.owner.assertsparam.viewmodel.ArgumentViewModel> implements com.owner.assertsparam.Interface.QueryAssertsInfo {
    private final java.lang.String[] titles = null;
    private final java.util.Stack<android.support.v4.app.Fragment> mStack = null;
    private final kotlin.Lazy locationFragment$delegate = null;
    private final kotlin.Lazy categoryFragment$delegate = null;
    private final kotlin.Lazy departmentFragment$delegate = null;
    private final kotlin.Lazy managerFragment$delegate = null;
    private java.util.HashMap _$_findViewCache;
    
    @java.lang.Override()
    public void queryAssert(@org.jetbrains.annotations.NotNull()
    java.lang.String tablename, @org.jetbrains.annotations.NotNull()
    java.lang.Object condition) {
    }
    
    private final com.owner.assertsparam.view.fragment.CategoryFragment getLocationFragment() {
        return null;
    }
    
    private final com.owner.assertsparam.view.fragment.CategoryFragment getCategoryFragment() {
        return null;
    }
    
    private final com.owner.assertsparam.view.fragment.CategoryFragment getDepartmentFragment() {
        return null;
    }
    
    private final com.owner.assertsparam.view.fragment.ManagerFragment getManagerFragment() {
        return null;
    }
    
    @android.annotation.SuppressLint(value = {"CheckResult"})
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initFragment() {
    }
    
    private final void initBottomNav() {
    }
    
    private final void changeFragment(int position) {
    }
    
    /**
     * * 得到管理人员增加返回结果
     */
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    public AssertsArgumentActivity() {
        super();
    }
}