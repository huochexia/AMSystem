package com.owner.assertsparam.view.adapter;

import java.lang.System;

/**
 * * 三级分类适配器
 * * @secondCategory:二级分类，通过它的Id从列表中筛选出它的子分类
 * * Created by Administrator on 2018/10/25 0025
 */
@kotlin.Metadata(mv = {1, 1, 11}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001bB\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000fH\u0016J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u000fH\u0016J\u0018\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u000fH\u0016J\u0006\u0010\u0019\u001a\u00020\u0013J\u0006\u0010\u001a\u001a\u00020\u0013R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/owner/assertsparam/view/adapter/ThirdCgAdapter;", "Landroid/support/v7/widget/RecyclerView$Adapter;", "Lcom/owner/assertsparam/view/adapter/ThirdCgAdapter$ThirdViewHolder;", "secondCategory", "Lcom/owner/assertsparam/data/CategoryInfo;", "mCategoryVM", "Lcom/owner/assertsparam/viewmodel/CategoryFgViewModel;", "thirdCgMore", "Lcom/owner/assertsparam/viewmodel/ThirdCgMoreView;", "(Lcom/owner/assertsparam/data/CategoryInfo;Lcom/owner/assertsparam/viewmodel/CategoryFgViewModel;Lcom/owner/assertsparam/viewmodel/ThirdCgMoreView;)V", "thirdCgAllList", "", "", "thirdCgSubList", "getItemCount", "", "getItemViewType", "position", "onBindViewHolder", "", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "showAllList", "showSubList", "ThirdViewHolder", "assertsparam_debug"})
public final class ThirdCgAdapter extends android.support.v7.widget.RecyclerView.Adapter<com.owner.assertsparam.view.adapter.ThirdCgAdapter.ThirdViewHolder> {
    private java.util.List<java.lang.Object> thirdCgAllList;
    private java.util.List<java.lang.Object> thirdCgSubList;
    private final com.owner.assertsparam.data.CategoryInfo secondCategory = null;
    private final com.owner.assertsparam.viewmodel.CategoryFgViewModel mCategoryVM = null;
    private final com.owner.assertsparam.viewmodel.ThirdCgMoreView thirdCgMore = null;
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.owner.assertsparam.view.adapter.ThirdCgAdapter.ThirdViewHolder holder, int position) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.owner.assertsparam.view.adapter.ThirdCgAdapter.ThirdViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public int getItemViewType(int position) {
        return 0;
    }
    
    /**
     * * 显示全部内容
     */
    public final void showAllList() {
    }
    
    /**
     * * 显示部分内容
     */
    public final void showSubList() {
    }
    
    public ThirdCgAdapter(@org.jetbrains.annotations.NotNull()
    com.owner.assertsparam.data.CategoryInfo secondCategory, @org.jetbrains.annotations.NotNull()
    com.owner.assertsparam.viewmodel.CategoryFgViewModel mCategoryVM, @org.jetbrains.annotations.NotNull()
    com.owner.assertsparam.viewmodel.ThirdCgMoreView thirdCgMore) {
        super();
    }
    
    /**
     * * ViewHolder
     */
    @kotlin.Metadata(mv = {1, 1, 11}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0010\u0010\r\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u000eH\u0002R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/owner/assertsparam/view/adapter/ThirdCgAdapter$ThirdViewHolder;", "Landroid/support/v7/widget/RecyclerView$ViewHolder;", "mBinding", "Landroid/databinding/ViewDataBinding;", "(Landroid/databinding/ViewDataBinding;)V", "getMBinding", "()Landroid/databinding/ViewDataBinding;", "bindData", "", "data", "", "viewModel", "Lcom/owner/assertsparam/viewmodel/CategoryFgViewModel;", "setVisibleEditLL", "Lcom/owner/assertsparam/data/CategoryInfo;", "Companion", "assertsparam_debug"})
    public static final class ThirdViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final android.databinding.ViewDataBinding mBinding = null;
        public static final com.owner.assertsparam.view.adapter.ThirdCgAdapter.ThirdViewHolder.Companion Companion = null;
        
        public final void bindData(@org.jetbrains.annotations.NotNull()
        java.lang.Object data, @org.jetbrains.annotations.NotNull()
        com.owner.assertsparam.viewmodel.CategoryFgViewModel viewModel) {
        }
        
        private final void setVisibleEditLL(com.owner.assertsparam.data.CategoryInfo data) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.databinding.ViewDataBinding getMBinding() {
            return null;
        }
        
        public ThirdViewHolder(@org.jetbrains.annotations.NotNull()
        android.databinding.ViewDataBinding mBinding) {
            super(null);
        }
        
        @kotlin.Metadata(mv = {1, 1, 11}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n\u00a8\u0006\u000b"}, d2 = {"Lcom/owner/assertsparam/view/adapter/ThirdCgAdapter$ThirdViewHolder$Companion;", "", "()V", "create", "Lcom/owner/assertsparam/view/adapter/ThirdCgAdapter$ThirdViewHolder;", "inflater", "Landroid/view/LayoutInflater;", "parent", "Landroid/view/ViewGroup;", "viewType", "", "assertsparam_debug"})
        public static final class Companion {
            
            @org.jetbrains.annotations.NotNull()
            public final com.owner.assertsparam.view.adapter.ThirdCgAdapter.ThirdViewHolder create(@org.jetbrains.annotations.NotNull()
            android.view.LayoutInflater inflater, @org.jetbrains.annotations.NotNull()
            android.view.ViewGroup parent, int viewType) {
                return null;
            }
            
            private Companion() {
                super();
            }
        }
    }
}