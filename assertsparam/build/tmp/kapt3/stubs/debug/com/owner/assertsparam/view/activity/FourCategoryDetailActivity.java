package com.owner.assertsparam.view.activity;

import java.lang.System;

/**
 * *  四级分类明细,通过ARouter带参数的方式，将相关数据传入, 通过传入的isEdited值决定当前是编辑还是选择
 * *  。通过isQuery的值决定是否是查询状态。tableName决定对哪个分类表进行操作，thirdCg是父类
 * * Created by Liuyong on 2018-11-17.It's AMSystem
 * *@description:
 */
@com.alibaba.android.arouter.facade.annotation.Route(path = "/assertsparam/four")
@kotlin.Metadata(mv = {1, 1, 11}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\u0012\u0010\u0014\u001a\u00020\u00132\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0012\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\r\u001a\u00020\f8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u000e\u001a\u00020\u000f8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0010\u001a\u00020\u00118\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/owner/assertsparam/view/activity/FourCategoryDetailActivity;", "Lcom/owner/baselibrary/view/activity/BaseActivity;", "error/NonExistentClass", "Lcom/owner/assertsparam/viewmodel/FourthCategoryViewModel;", "()V", "fragment", "Lcom/owner/assertsparam/view/fragment/FourCategoryFragment;", "getFragment", "()Lcom/owner/assertsparam/view/fragment/FourCategoryFragment;", "fragment$delegate", "Lkotlin/Lazy;", "isEdited", "", "isQuery", "tableName", "", "thirdCg", "Lcom/owner/assertsparam/data/CategoryInfo;", "initHeadBar", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "assertsparam_debug"})
public final class FourCategoryDetailActivity extends com.owner.baselibrary.view.activity.BaseActivity<error.NonExistentClass, com.owner.assertsparam.viewmodel.FourthCategoryViewModel> {
    @org.jetbrains.annotations.NotNull()
    @com.alibaba.android.arouter.facade.annotation.Autowired()
    public java.lang.String tableName;
    @com.alibaba.android.arouter.facade.annotation.Autowired()
    public boolean isEdited;
    @com.alibaba.android.arouter.facade.annotation.Autowired()
    public boolean isQuery;
    @org.jetbrains.annotations.NotNull()
    @com.alibaba.android.arouter.facade.annotation.Autowired()
    public com.owner.assertsparam.data.CategoryInfo thirdCg;
    private final kotlin.Lazy fragment$delegate = null;
    private java.util.HashMap _$_findViewCache;
    
    private final com.owner.assertsparam.view.fragment.FourCategoryFragment getFragment() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    /**
     * * 初始化HeadBar
     */
    private final void initHeadBar() {
    }
    
    public FourCategoryDetailActivity() {
        super();
    }
}