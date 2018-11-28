package com.owner.assertsparam.view.adapter;

import java.lang.System;

/**
 * *
 * * Created by Liuyong on 2018-10-30.It's AMSystem
 * *@description:
 */
@kotlin.Metadata(mv = {1, 1, 11}, bv = {1, 0, 2}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0018B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\b\u0010\t\u001a\u00020\nH\u0016J\u000e\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nJ\u000e\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\nJ\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\nH\u0016J\u0018\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\nH\u0016J\u0014\u0010\u0016\u001a\u00020\u00102\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\b0\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/owner/assertsparam/view/adapter/ManagerAdapter;", "Landroid/support/v7/widget/RecyclerView$Adapter;", "Lcom/owner/assertsparam/view/adapter/ManagerAdapter$ManagerViewHolder;", "mManagerVM", "Lcom/owner/assertsparam/viewmodel/ManagerViewModel;", "(Lcom/owner/assertsparam/viewmodel/ManagerViewModel;)V", "managerList", "", "Lcom/owner/assertsparam/data/Manager;", "getItemCount", "", "getPositionForSection", "section", "getSectionForPosition", "position", "onBindViewHolder", "", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "updateList", "list", "ManagerViewHolder", "assertsparam_debug"})
public final class ManagerAdapter extends android.support.v7.widget.RecyclerView.Adapter<com.owner.assertsparam.view.adapter.ManagerAdapter.ManagerViewHolder> {
    private java.util.List<com.owner.assertsparam.data.Manager> managerList;
    private final com.owner.assertsparam.viewmodel.ManagerViewModel mManagerVM = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.owner.assertsparam.view.adapter.ManagerAdapter.ManagerViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.owner.assertsparam.view.adapter.ManagerAdapter.ManagerViewHolder holder, int position) {
    }
    
    public final void updateList(@org.jetbrains.annotations.NotNull()
    java.util.List<com.owner.assertsparam.data.Manager> list) {
    }
    
    /**
     * * 根据当前位置获取分类的首字母的char ascii值
     */
    public final int getSectionForPosition(int position) {
        return 0;
    }
    
    /**
     * * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public final int getPositionForSection(int section) {
        return 0;
    }
    
    public ManagerAdapter(@org.jetbrains.annotations.NotNull()
    com.owner.assertsparam.viewmodel.ManagerViewModel mManagerVM) {
        super();
    }
    
    /**
     * * ViewHolder
     */
    @kotlin.Metadata(mv = {1, 1, 11}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rR\u0013\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u000f"}, d2 = {"Lcom/owner/assertsparam/view/adapter/ManagerAdapter$ManagerViewHolder;", "Landroid/support/v7/widget/RecyclerView$ViewHolder;", "mBinding", "error/NonExistentClass", "(Lerror/NonExistentClass;)V", "getMBinding", "()Lerror/NonExistentClass;", "Lerror/NonExistentClass;", "bindData", "", "manager", "Lcom/owner/assertsparam/data/Manager;", "vm", "Lcom/owner/assertsparam/viewmodel/ManagerViewModel;", "Companion", "assertsparam_debug"})
    public static final class ManagerViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final error.NonExistentClass mBinding = null;
        public static final com.owner.assertsparam.view.adapter.ManagerAdapter.ManagerViewHolder.Companion Companion = null;
        
        public final void bindData(@org.jetbrains.annotations.NotNull()
        com.owner.assertsparam.data.Manager manager, @org.jetbrains.annotations.NotNull()
        com.owner.assertsparam.viewmodel.ManagerViewModel vm) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final error.NonExistentClass getMBinding() {
            return null;
        }
        
        public ManagerViewHolder(@org.jetbrains.annotations.NotNull()
        error.NonExistentClass mBinding) {
            super(null);
        }
        
        @kotlin.Metadata(mv = {1, 1, 11}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\u00a8\u0006\t"}, d2 = {"Lcom/owner/assertsparam/view/adapter/ManagerAdapter$ManagerViewHolder$Companion;", "", "()V", "create", "Lcom/owner/assertsparam/view/adapter/ManagerAdapter$ManagerViewHolder;", "inflater", "Landroid/view/LayoutInflater;", "parent", "Landroid/view/ViewGroup;", "assertsparam_debug"})
        public static final class Companion {
            
            @org.jetbrains.annotations.NotNull()
            public final com.owner.assertsparam.view.adapter.ManagerAdapter.ManagerViewHolder create(@org.jetbrains.annotations.NotNull()
            android.view.LayoutInflater inflater, @org.jetbrains.annotations.NotNull()
            android.view.ViewGroup parent) {
                return null;
            }
            
            private Companion() {
                super();
            }
        }
    }
}