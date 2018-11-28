package com.owner.assertsparam.view.activity;

import java.lang.System;

/**
 * *  登记资产的参数，必选项类别和管理人
 * * Created by Liuyong on 2018-11-14.It's AMSystem
 * *@description:
 */
@kotlin.Metadata(mv = {1, 1, 11}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0010\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u0018H\u0002J\b\u0010\u001f\u001a\u00020\u0018H\u0002J\"\u0010 \u001a\u00020\u00182\u0006\u0010!\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020\u001a2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014J\u0012\u0010#\u001a\u00020\u00182\b\u0010$\u001a\u0004\u0018\u00010%H\u0014R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0012\u0010\n\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0016\u00a8\u0006&"}, d2 = {"Lcom/owner/assertsparam/view/activity/RegisterAssertArgActivity;", "Lcom/owner/baselibrary/view/activity/BaseActivity;", "error/NonExistentClass", "Lcom/owner/assertsparam/viewmodel/ArgumentViewModel;", "()V", "categoryFragment", "Lcom/owner/assertsparam/view/fragment/CategoryFragment;", "getCategoryFragment", "()Lcom/owner/assertsparam/view/fragment/CategoryFragment;", "categoryFragment$delegate", "Lkotlin/Lazy;", "mStack", "Ljava/util/Stack;", "Landroid/support/v4/app/Fragment;", "managerFragment", "Lcom/owner/assertsparam/view/fragment/ManagerFragment;", "getManagerFragment", "()Lcom/owner/assertsparam/view/fragment/ManagerFragment;", "managerFragment$delegate", "titles", "", "", "[Ljava/lang/String;", "changeFragment", "", "position", "", "getFourth", "data", "Landroid/content/Intent;", "initBottomNav", "initFragment", "onActivityResult", "requestCode", "resultCode", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "assertsparam_debug"})
public final class RegisterAssertArgActivity extends com.owner.baselibrary.view.activity.BaseActivity<error.NonExistentClass, com.owner.assertsparam.viewmodel.ArgumentViewModel> {
    private final java.util.Stack<android.support.v4.app.Fragment> mStack = null;
    private final java.lang.String[] titles = null;
    private final kotlin.Lazy categoryFragment$delegate = null;
    private final kotlin.Lazy managerFragment$delegate = null;
    private java.util.HashMap _$_findViewCache;
    
    private final com.owner.assertsparam.view.fragment.CategoryFragment getCategoryFragment() {
        return null;
    }
    
    private final com.owner.assertsparam.view.fragment.ManagerFragment getManagerFragment() {
        return null;
    }
    
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
    
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    /**
     * * 得到四级分类选择结果，并暂存在共享ViewModel中
     */
    private final void getFourth(android.content.Intent data) {
    }
    
    public RegisterAssertArgActivity() {
        super();
    }
}