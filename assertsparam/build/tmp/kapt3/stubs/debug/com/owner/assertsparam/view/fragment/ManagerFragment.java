package com.owner.assertsparam.view.fragment;

import java.lang.System;

/**
 * *
 * * Created by Liuyong on 2018-10-30.It's AMSystem
 * *@description:
 */
@kotlin.Metadata(mv = {1, 1, 11}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 .2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001.B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u001a\u001a\u00020\u001bH\u0002J\b\u0010\u001c\u001a\u00020\u001bH\u0002J\"\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001f2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\u0012\u0010#\u001a\u00020\u001b2\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J&\u0010&\u001a\u0004\u0018\u00010\'2\u0006\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010+2\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\u001a\u0010,\u001a\u00020\u001b2\u0006\u0010-\u001a\u00020\'2\b\u0010$\u001a\u0004\u0018\u00010%H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006/"}, d2 = {"Lcom/owner/assertsparam/view/fragment/ManagerFragment;", "Lcom/owner/baselibrary/view/fragment/BaseFragment;", "error/NonExistentClass", "Lcom/owner/assertsparam/viewmodel/ManagerViewModel;", "()V", "isEdited", "", "isQuery", "mAdapter", "Lcom/owner/assertsparam/view/adapter/ManagerAdapter;", "getMAdapter", "()Lcom/owner/assertsparam/view/adapter/ManagerAdapter;", "setMAdapter", "(Lcom/owner/assertsparam/view/adapter/ManagerAdapter;)V", "mDecoration", "Lcom/owner/assertsparam/utils/TitleItemDecoration;", "managerLL", "Landroid/support/v7/widget/LinearLayoutManager;", "queryInterface", "Lcom/owner/assertsparam/Interface/QueryAssertsInfo;", "getQueryInterface", "()Lcom/owner/assertsparam/Interface/QueryAssertsInfo;", "setQueryInterface", "(Lcom/owner/assertsparam/Interface/QueryAssertsInfo;)V", "sharedViewModel", "Lcom/owner/assertsparam/viewmodel/ArgumentViewModel;", "initViewModel", "", "loadManagerList", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", "view", "Companion", "assertsparam_debug"})
public final class ManagerFragment extends com.owner.baselibrary.view.fragment.BaseFragment<error.NonExistentClass, com.owner.assertsparam.viewmodel.ManagerViewModel> {
    @org.jetbrains.annotations.NotNull()
    public com.owner.assertsparam.Interface.QueryAssertsInfo queryInterface;
    @org.jetbrains.annotations.NotNull()
    public com.owner.assertsparam.view.adapter.ManagerAdapter mAdapter;
    private android.support.v7.widget.LinearLayoutManager managerLL;
    private com.owner.assertsparam.utils.TitleItemDecoration mDecoration;
    private com.owner.assertsparam.viewmodel.ArgumentViewModel sharedViewModel;
    private boolean isEdited;
    private boolean isQuery;
    public static final int SELECT_MANAGER_REQUEST_CODE = 101;
    public static final com.owner.assertsparam.view.fragment.ManagerFragment.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final com.owner.assertsparam.Interface.QueryAssertsInfo getQueryInterface() {
        return null;
    }
    
    public final void setQueryInterface(@org.jetbrains.annotations.NotNull()
    com.owner.assertsparam.Interface.QueryAssertsInfo p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.owner.assertsparam.view.adapter.ManagerAdapter getMAdapter() {
        return null;
    }
    
    public final void setMAdapter(@org.jetbrains.annotations.NotNull()
    com.owner.assertsparam.view.adapter.ManagerAdapter p0) {
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initViewModel() {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    /**
     * * 加载管理员列表
     */
    private final void loadManagerList() {
    }
    
    @java.lang.Override()
    public void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    public ManagerFragment() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 11}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/owner/assertsparam/view/fragment/ManagerFragment$Companion;", "", "()V", "SELECT_MANAGER_REQUEST_CODE", "", "newInstance", "Lcom/owner/assertsparam/view/fragment/ManagerFragment;", "isEdited", "", "isQuery", "assertsparam_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final com.owner.assertsparam.view.fragment.ManagerFragment newInstance(boolean isEdited, boolean isQuery) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}