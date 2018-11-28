package com.owner.assertsparam.view.adapter;

import java.lang.System;

/**
 * * 二级分类适配器
 * * @topCategory:一级分类，通过它的Id，筛选它的子类
 * * Created by Administrator on 2018/10/25 0025
 */
@kotlin.Metadata(mv = {1, 1, 11}, bv = {1, 0, 2}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0017B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\b\u0010\f\u001a\u00020\rH\u0016J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\rH\u0016J\u0018\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\rH\u0016J\u0006\u0010\u0016\u001a\u00020\u000fR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/owner/assertsparam/view/adapter/SecondCgAdapter;", "Landroid/support/v7/widget/RecyclerView$Adapter;", "Lcom/owner/assertsparam/view/adapter/SecondCgAdapter$SecondViewHolder;", "topCategory", "Lcom/owner/assertsparam/data/CategoryInfo;", "mCategoryVM", "Lcom/owner/assertsparam/viewmodel/CategoryFgViewModel;", "(Lcom/owner/assertsparam/data/CategoryInfo;Lcom/owner/assertsparam/viewmodel/CategoryFgViewModel;)V", "getMCategoryVM", "()Lcom/owner/assertsparam/viewmodel/CategoryFgViewModel;", "secondCgList", "", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "ViewType", "updateList", "SecondViewHolder", "assertsparam_debug"})
public final class SecondCgAdapter extends android.support.v7.widget.RecyclerView.Adapter<com.owner.assertsparam.view.adapter.SecondCgAdapter.SecondViewHolder> {
    private final java.util.List<com.owner.assertsparam.data.CategoryInfo> secondCgList = null;
    private final com.owner.assertsparam.data.CategoryInfo topCategory = null;
    @org.jetbrains.annotations.NotNull()
    private final com.owner.assertsparam.viewmodel.CategoryFgViewModel mCategoryVM = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.owner.assertsparam.view.adapter.SecondCgAdapter.SecondViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int ViewType) {
        return null;
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.owner.assertsparam.view.adapter.SecondCgAdapter.SecondViewHolder holder, int position) {
    }
    
    /**
     * * 因为传入Adapter的参数是ViewModel，而不是数据列表，对ViewModel中数据列表的变更不会影响到
     *     * Adapter中的列表，所以需要对外公开一个更新列表的方法
     */
    public final void updateList() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.owner.assertsparam.viewmodel.CategoryFgViewModel getMCategoryVM() {
        return null;
    }
    
    public SecondCgAdapter(@org.jetbrains.annotations.NotNull()
    com.owner.assertsparam.data.CategoryInfo topCategory, @org.jetbrains.annotations.NotNull()
    com.owner.assertsparam.viewmodel.CategoryFgViewModel mCategoryVM) {
        super();
    }
    
    /**
     * * ViewHolder 嵌套类
     */
    @kotlin.Metadata(mv = {1, 1, 11}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 \"2\u00020\u0001:\u0001\"B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dJ\u0018\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u0010\u0010\u001f\u001a\u00020\u00192\u0006\u0010 \u001a\u00020!H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u001a\u0010\f\u001a\u00020\rX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017\u00a8\u0006#"}, d2 = {"Lcom/owner/assertsparam/view/adapter/SecondCgAdapter$SecondViewHolder;", "Landroid/support/v7/widget/RecyclerView$ViewHolder;", "context", "Landroid/content/Context;", "mBinding", "error/NonExistentClass", "(Landroid/content/Context;Lerror/NonExistentClass;)V", "getContext", "()Landroid/content/Context;", "getMBinding", "()Lerror/NonExistentClass;", "Lerror/NonExistentClass;", "moreView", "Lcom/owner/assertsparam/viewmodel/ThirdCgMoreView;", "getMoreView", "()Lcom/owner/assertsparam/viewmodel/ThirdCgMoreView;", "setMoreView", "(Lcom/owner/assertsparam/viewmodel/ThirdCgMoreView;)V", "thirdCgAdapter", "Lcom/owner/assertsparam/view/adapter/ThirdCgAdapter;", "getThirdCgAdapter", "()Lcom/owner/assertsparam/view/adapter/ThirdCgAdapter;", "setThirdCgAdapter", "(Lcom/owner/assertsparam/view/adapter/ThirdCgAdapter;)V", "bindData", "", "category", "Lcom/owner/assertsparam/data/CategoryInfo;", "viewModel", "Lcom/owner/assertsparam/viewmodel/CategoryFgViewModel;", "setThirdCgList", "setVisibleEditLL", "visible", "", "Companion", "assertsparam_debug"})
    public static final class SecondViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        public com.owner.assertsparam.viewmodel.ThirdCgMoreView moreView;
        @org.jetbrains.annotations.NotNull()
        public com.owner.assertsparam.view.adapter.ThirdCgAdapter thirdCgAdapter;
        @org.jetbrains.annotations.NotNull()
        private final android.content.Context context = null;
        @org.jetbrains.annotations.NotNull()
        private final error.NonExistentClass mBinding = null;
        public static final com.owner.assertsparam.view.adapter.SecondCgAdapter.SecondViewHolder.Companion Companion = null;
        
        @org.jetbrains.annotations.NotNull()
        public final com.owner.assertsparam.viewmodel.ThirdCgMoreView getMoreView() {
            return null;
        }
        
        public final void setMoreView(@org.jetbrains.annotations.NotNull()
        com.owner.assertsparam.viewmodel.ThirdCgMoreView p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.owner.assertsparam.view.adapter.ThirdCgAdapter getThirdCgAdapter() {
            return null;
        }
        
        public final void setThirdCgAdapter(@org.jetbrains.annotations.NotNull()
        com.owner.assertsparam.view.adapter.ThirdCgAdapter p0) {
        }
        
        public final void bindData(@org.jetbrains.annotations.NotNull()
        com.owner.assertsparam.data.CategoryInfo category, @org.jetbrains.annotations.NotNull()
        com.owner.assertsparam.viewmodel.CategoryFgViewModel viewModel) {
        }
        
        /**
         * * 构造三级分类的适配器
         */
        private final void setThirdCgList(com.owner.assertsparam.data.CategoryInfo category, com.owner.assertsparam.viewmodel.CategoryFgViewModel viewModel) {
        }
        
        /**
         * 设置编辑界面的可见性
         */
        private final void setVisibleEditLL(boolean visible) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.content.Context getContext() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final error.NonExistentClass getMBinding() {
            return null;
        }
        
        public SecondViewHolder(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        error.NonExistentClass mBinding) {
            super(null);
        }
        
        @kotlin.Metadata(mv = {1, 1, 11}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\u00a8\u0006\t"}, d2 = {"Lcom/owner/assertsparam/view/adapter/SecondCgAdapter$SecondViewHolder$Companion;", "", "()V", "create", "Lcom/owner/assertsparam/view/adapter/SecondCgAdapter$SecondViewHolder;", "inflater", "Landroid/view/LayoutInflater;", "parent", "Landroid/view/ViewGroup;", "assertsparam_debug"})
        public static final class Companion {
            
            @org.jetbrains.annotations.NotNull()
            public final com.owner.assertsparam.view.adapter.SecondCgAdapter.SecondViewHolder create(@org.jetbrains.annotations.NotNull()
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