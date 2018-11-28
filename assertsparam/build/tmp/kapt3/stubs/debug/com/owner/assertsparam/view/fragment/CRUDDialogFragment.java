package com.owner.assertsparam.view.fragment;

import java.lang.System;

/**
 * * 用于弹出增改删操作的对话框，初始化了TakePhoto组件，只要需要这些操作的Fragment就需要继承该类
 * * Created by Liuyong on 2018-11-21.It's AMSystem
 * *@description:
 */
@kotlin.Metadata(mv = {1, 1, 11}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000\u00a4\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\f\b\u0001\u0010\u0003*\u0006\u0012\u0002\b\u00030\u00042\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u00052\u00020\u00062\u00020\u0007B\u0005\u00a2\u0006\u0002\u0010\bJ\b\u0010\u001e\u001a\u00020\u001fH\u0002J\u001c\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020#0!2\u0006\u0010$\u001a\u00020%H\u0002J\u0012\u0010&\u001a\u00020\u001f2\b\u0010\'\u001a\u0004\u0018\u00010(H\u0002J\u0011\u0010)\u001a\u00020*2\u0006\u0010\t\u001a\u00020\nH\u0096\u0002J\"\u0010+\u001a\u00020\u001f2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020-2\b\u0010/\u001a\u0004\u0018\u000100H\u0016J\u0012\u00101\u001a\u00020\u001f2\b\u0010\'\u001a\u0004\u0018\u00010(H\u0016J-\u00102\u001a\u00020\u001f2\u0006\u0010,\u001a\u00020-2\u000e\u00103\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000f042\u0006\u00105\u001a\u000206H\u0016\u00a2\u0006\u0002\u00107J\u0010\u00108\u001a\u00020\u001f2\u0006\u00109\u001a\u00020(H\u0016J*\u0010:\u001a\u00020\u001f2\u0006\u0010;\u001a\u00020\u00192\u0006\u0010$\u001a\u00020%2\u0012\u0010<\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001f0=J*\u0010>\u001a\u00020\u001f2\u0006\u0010?\u001a\u00020\u000f2\u0006\u0010@\u001a\u00020\u00192\u0012\u0010<\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001f0=J*\u0010A\u001a\u00020\u001f2\u0006\u0010@\u001a\u00020\u00192\u0006\u0010$\u001a\u00020%2\u0012\u0010B\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001f0=J\b\u0010C\u001a\u00020\u001fH\u0016J\u001c\u0010D\u001a\u00020\u001f2\b\u0010E\u001a\u0004\u0018\u00010F2\b\u0010G\u001a\u0004\u0018\u00010\u000fH\u0016J\u0012\u0010H\u001a\u00020\u001f2\b\u0010E\u001a\u0004\u0018\u00010FH\u0016R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R \u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082.\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\u00020\u0019X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d\u00a8\u0006I"}, d2 = {"Lcom/owner/assertsparam/view/fragment/CRUDDialogFragment;", "T", "Landroid/databinding/ViewDataBinding;", "B", "Lcom/owner/baselibrary/viewmodel/BaseViewModel;", "Lcom/owner/baselibrary/view/fragment/BaseFragment;", "Lcom/jph/takephoto/app/TakePhoto$TakeResultListener;", "Lcom/jph/takephoto/permission/InvokeListener;", "()V", "invokeParam", "Lcom/jph/takephoto/model/InvokeParam;", "mAlertView", "Lcom/bigkoo/alertview/AlertView;", "mCategoryImage", "Landroid/arch/lifecycle/MutableLiveData;", "", "getMCategoryImage", "()Landroid/arch/lifecycle/MutableLiveData;", "setMCategoryImage", "(Landroid/arch/lifecycle/MutableLiveData;)V", "mTakePhoto", "Lcom/jph/takephoto/app/TakePhoto;", "mTempFile", "Ljava/io/File;", "tempCategory", "Lcom/owner/assertsparam/data/CategoryInfo;", "getTempCategory", "()Lcom/owner/assertsparam/data/CategoryInfo;", "setTempCategory", "(Lcom/owner/assertsparam/data/CategoryInfo;)V", "createTempFile", "", "initDialog", "Lkotlin/Pair;", "Landroid/view/View;", "Landroid/widget/EditText;", "hasImage", "", "initTakePhoto", "savedInstanceState", "Landroid/os/Bundle;", "invoke", "Lcom/jph/takephoto/permission/PermissionManager$TPermissionType;", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "onRequestPermissionsResult", "permissions", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onSaveInstanceState", "outState", "popupAddDialog", "parent", "confirm", "Lkotlin/Function1;", "popupDeleteDialog", "title", "category", "popupUpdateDialog", "action", "takeCancel", "takeFail", "result", "Lcom/jph/takephoto/model/TResult;", "msg", "takeSuccess", "assertsparam_debug"})
public class CRUDDialogFragment<T extends android.databinding.ViewDataBinding, B extends com.owner.baselibrary.viewmodel.BaseViewModel<?>> extends com.owner.baselibrary.view.fragment.BaseFragment<T, B> implements com.jph.takephoto.app.TakePhoto.TakeResultListener, com.jph.takephoto.permission.InvokeListener {
    @org.jetbrains.annotations.NotNull()
    public com.owner.assertsparam.data.CategoryInfo tempCategory;
    @org.jetbrains.annotations.NotNull()
    private android.arch.lifecycle.MutableLiveData<java.lang.String> mCategoryImage;
    private com.jph.takephoto.app.TakePhoto mTakePhoto;
    private java.io.File mTempFile;
    private com.jph.takephoto.model.InvokeParam invokeParam;
    private com.bigkoo.alertview.AlertView mAlertView;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final com.owner.assertsparam.data.CategoryInfo getTempCategory() {
        return null;
    }
    
    public final void setTempCategory(@org.jetbrains.annotations.NotNull()
    com.owner.assertsparam.data.CategoryInfo p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.arch.lifecycle.MutableLiveData<java.lang.String> getMCategoryImage() {
        return null;
    }
    
    public final void setMCategoryImage(@org.jetbrains.annotations.NotNull()
    android.arch.lifecycle.MutableLiveData<java.lang.String> p0) {
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    /**
     * * 初始化TakePhoto组件
     */
    private final void initTakePhoto(android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void takeSuccess(@org.jetbrains.annotations.Nullable()
    com.jph.takephoto.model.TResult result) {
    }
    
    @java.lang.Override()
    public void takeCancel() {
    }
    
    @java.lang.Override()
    public void takeFail(@org.jetbrains.annotations.Nullable()
    com.jph.takephoto.model.TResult result, @org.jetbrains.annotations.Nullable()
    java.lang.String msg) {
    }
    
    @java.lang.Override()
    public void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    @java.lang.Override()
    public void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    /**
     * * TakePhoto对权限设置针对6.0 和7.0版动态权限的获取
     */
    @java.lang.Override()
    public void onRequestPermissionsResult(int requestCode, @org.jetbrains.annotations.NotNull()
    java.lang.String[] permissions, @org.jetbrains.annotations.NotNull()
    int[] grantResults) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.jph.takephoto.permission.PermissionManager.TPermissionType invoke(@org.jetbrains.annotations.NotNull()
    com.jph.takephoto.model.InvokeParam invokeParam) {
        return null;
    }
    
    /**
     * * 为照像创建临时文件
     */
    private final void createTempFile() {
    }
    
    /**
     * * 初始化对话框内容
     */
    private final kotlin.Pair<android.view.View, android.widget.EditText> initDialog(boolean hasImage) {
        return null;
    }
    
    /**
     * * 弹出增加窗口
     *     * @title 标题
     *     * @parent 上一级
     *     * @hasImage 是否有图片
     *     * @action 确认独立行为
     */
    public final void popupAddDialog(@org.jetbrains.annotations.NotNull()
    com.owner.assertsparam.data.CategoryInfo parent, boolean hasImage, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.owner.assertsparam.data.CategoryInfo, kotlin.Unit> confirm) {
    }
    
    /**
     * * 修改窗口
     */
    public final void popupUpdateDialog(@org.jetbrains.annotations.NotNull()
    com.owner.assertsparam.data.CategoryInfo category, boolean hasImage, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.owner.assertsparam.data.CategoryInfo, kotlin.Unit> action) {
    }
    
    /**
     * * 删除窗口
     */
    public final void popupDeleteDialog(@org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    com.owner.assertsparam.data.CategoryInfo category, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.owner.assertsparam.data.CategoryInfo, kotlin.Unit> confirm) {
    }
    
    public CRUDDialogFragment() {
        super();
    }
}