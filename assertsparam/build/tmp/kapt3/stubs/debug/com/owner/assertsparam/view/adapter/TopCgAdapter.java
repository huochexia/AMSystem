package com.owner.assertsparam.view.adapter;

import java.lang.System;

/**
 * * Adapter,除了传入数据列表以外，还需要传入CategoryFgViewModel对象，因为项目布局中要引入该对象。布局
 * * 中引入这个对象是为了实现点击项目时视图响应该点击事件。点击事件处理项目选中后的变化
 * * Created by Administrator on 2018/10/22 0022
 */
@kotlin.Metadata(mv = {1, 1, 11}, bv = {1, 0, 2}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0013B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\b\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\nH\u0016J\u0018\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\nH\u0016R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/owner/assertsparam/view/adapter/TopCgAdapter;", "Landroid/support/v7/widget/RecyclerView$Adapter;", "Lcom/owner/assertsparam/view/adapter/TopCgAdapter$TopCgViewHolder;", "mCategoryVM", "Lcom/owner/assertsparam/viewmodel/CategoryFgViewModel;", "(Lcom/owner/assertsparam/viewmodel/CategoryFgViewModel;)V", "dataList", "", "Lcom/owner/assertsparam/data/CategoryInfo;", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "TopCgViewHolder", "assertsparam_debug"})
public final class TopCgAdapter extends android.support.v7.widget.RecyclerView.Adapter<com.owner.assertsparam.view.adapter.TopCgAdapter.TopCgViewHolder> {
    private java.util.List<com.owner.assertsparam.data.CategoryInfo> dataList;
    private final com.owner.assertsparam.viewmodel.CategoryFgViewModel mCategoryVM = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.owner.assertsparam.view.adapter.TopCgAdapter.TopCgViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.owner.assertsparam.view.adapter.TopCgAdapter.TopCgViewHolder holder, int position) {
    }
    
    public TopCgAdapter(@org.jetbrains.annotations.NotNull()
    com.owner.assertsparam.viewmodel.CategoryFgViewModel mCategoryVM) {
        super();
    }
    
    /**
     * * ViewHolder
     */
    @kotlin.Metadata(mv = {1, 1, 11}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0010\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u0010\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0005\u00a8\u0006\u0010"}, d2 = {"Lcom/owner/assertsparam/view/adapter/TopCgAdapter$TopCgViewHolder;", "Landroid/support/v7/widget/RecyclerView$ViewHolder;", "mBinding", "error/NonExistentClass", "(Lerror/NonExistentClass;)V", "Lerror/NonExistentClass;", "bindToData", "", "topCategory", "Lcom/owner/assertsparam/data/CategoryInfo;", "categoryFgViewModel", "Lcom/owner/assertsparam/viewmodel/CategoryFgViewModel;", "setVisibleEditLL", "visible", "", "Companion", "assertsparam_debug"})
    public static final class TopCgViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        private final error.NonExistentClass mBinding = null;
        public static final com.owner.assertsparam.view.adapter.TopCgAdapter.TopCgViewHolder.Companion Companion = null;
        
        public final void bindToData(@org.jetbrains.annotations.NotNull()
        com.owner.assertsparam.data.CategoryInfo topCategory, @org.jetbrains.annotations.NotNull()
        com.owner.assertsparam.viewmodel.CategoryFgViewModel categoryFgViewModel) {
        }
        
        private final void setVisibleEditLL(boolean visible) {
        }
        
        public TopCgViewHolder(@org.jetbrains.annotations.NotNull()
        error.NonExistentClass mBinding) {
            super(null);
        }
        
        @kotlin.Metadata(mv = {1, 1, 11}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\u00a8\u0006\t"}, d2 = {"Lcom/owner/assertsparam/view/adapter/TopCgAdapter$TopCgViewHolder$Companion;", "", "()V", "create", "Lcom/owner/assertsparam/view/adapter/TopCgAdapter$TopCgViewHolder;", "inflater", "Landroid/view/LayoutInflater;", "parent", "Landroid/view/ViewGroup;", "assertsparam_debug"})
        public static final class Companion {
            
            @org.jetbrains.annotations.NotNull()
            public final com.owner.assertsparam.view.adapter.TopCgAdapter.TopCgViewHolder create(@org.jetbrains.annotations.NotNull()
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