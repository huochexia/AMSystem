package com.owner.assertsparam.viewmodel;

import java.lang.System;

/**
 * *@typeName :分类的名称。该参数的意义是区分种类。因为所有分类的结构是一样的，只是名称不一样，这个名称
 * * 对应数据库中表。因为使用RESTFULL，所以只需要区分表名即可。只要能够指定表名，就可以从不同表中获取相应
 * * 的分类对象。
 * * @isEdited :用于决定是否可以对数据进行编辑，当为true时，可进行增改删。当为false时不可编辑处于选择状态
 * * @isQuery :当为真是当前状态处于查询，点击后得到查询结果。此时的isEdited应该是true
 * * Created by Liuyong on 2018-10-20.It's AMSystem
 * *@description:
 */
@kotlin.Metadata(mv = {1, 1, 11}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010!\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0014\u0018\u0000 e2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001eB\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\bJ\u000e\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020\fJ\u0006\u0010E\u001a\u00020CJ\u000e\u0010F\u001a\u00020C2\u0006\u0010G\u001a\u00020HJ\u000e\u0010I\u001a\u00020C2\u0006\u0010J\u001a\u00020\fJ\u000e\u0010K\u001a\u00020C2\u0006\u0010L\u001a\u00020\fJ\u000e\u0010M\u001a\u00020C2\u0006\u0010L\u001a\u00020\fJ\u000e\u0010N\u001a\u00020C2\u0006\u0010O\u001a\u00020PJ\u0014\u0010Q\u001a\b\u0012\u0004\u0012\u00020\f0R2\u0006\u0010S\u001a\u00020\fJ\u0014\u0010T\u001a\b\u0012\u0004\u0012\u00020\f072\u0006\u0010U\u001a\u00020\fJ\u0010\u0010V\u001a\u00020C2\u0006\u0010S\u001a\u00020\fH\u0002J\u000e\u0010W\u001a\u00020\u00062\u0006\u0010S\u001a\u00020\fJ\u000e\u0010X\u001a\u00020C2\u0006\u0010S\u001a\u00020\fJ\u0010\u0010Y\u001a\u00020C2\u0006\u0010S\u001a\u00020\fH\u0002J\b\u0010Z\u001a\u00020CH\u0002J\u000e\u0010[\u001a\u00020C2\u0006\u0010L\u001a\u00020\fJ\u000e\u0010\\\u001a\u00020C2\u0006\u0010S\u001a\u00020\fJ\u0010\u0010]\u001a\u00020C2\u0006\u0010S\u001a\u00020\fH\u0002J\u001e\u0010^\u001a\u00020C2\u0006\u0010S\u001a\u00020\f2\f\u0010_\u001a\b\u0012\u0004\u0012\u00020\f07H\u0002J\u000e\u0010`\u001a\u00020C2\u0006\u0010S\u001a\u00020\fJ\u000e\u0010a\u001a\u00020C2\u0006\u0010L\u001a\u00020\fJ\u000e\u0010b\u001a\u00020C2\u0006\u0010L\u001a\u00020\fJ\u000e\u0010c\u001a\u00020C2\u0006\u0010d\u001a\u00020\fR,\u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0011\u001a\u00020\u00048FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R \u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u000e\"\u0004\b\u0018\u0010\u0010R,\u0010\u0019\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u000e\"\u0004\b\u001b\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u001cR\u0011\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u001cR&\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u00068\u0007@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001c\"\u0004\b\u001f\u0010 R&\u0010!\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\f8\u0007@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R&\u0010\'\u001a\u00020&2\u0006\u0010\u001d\u001a\u00020&8\u0007@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u0010\u0010,\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R&\u0010-\u001a\u00020&2\u0006\u0010\u001d\u001a\u00020&8\u0007@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b.\u0010)\"\u0004\b/\u0010+R,\u00100\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020&0\u000b0\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u000e\"\u0004\b2\u0010\u0010R,\u00103\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u000e\"\u0004\b5\u0010\u0010R \u00106\u001a\b\u0012\u0004\u0012\u00020\f07X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R \u0010<\u001a\b\u0012\u0004\u0012\u00020\f0\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\u000e\"\u0004\b>\u0010\u0010R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010?\u001a\b\u0012\u0004\u0012\u00020\f07X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b@\u00109\"\u0004\bA\u0010;\u00a8\u0006f"}, d2 = {"Lcom/owner/assertsparam/viewmodel/CategoryFgViewModel;", "Lcom/owner/baselibrary/viewmodel/BaseViewModel;", "Lcom/owner/assertsparam/model/repository/AssertsParamRepository;", "tableName", "", "isEdited", "", "isQuery", "(Ljava/lang/String;ZZ)V", "action", "Landroid/arch/lifecycle/MutableLiveData;", "Lkotlin/Pair;", "Lcom/owner/assertsparam/data/CategoryInfo;", "getAction", "()Landroid/arch/lifecycle/MutableLiveData;", "setAction", "(Landroid/arch/lifecycle/MutableLiveData;)V", "categoryName", "getCategoryName", "()Ljava/lang/String;", "setCategoryName", "(Ljava/lang/String;)V", "expandList", "getExpandList", "setExpandList", "gotoQueryAsserts", "getGotoQueryAsserts", "setGotoQueryAsserts", "()Z", "value", "isVisibleTop", "setVisibleTop", "(Z)V", "mCurrentTopCategory", "getMCurrentTopCategory", "()Lcom/owner/assertsparam/data/CategoryInfo;", "setMCurrentTopCategory", "(Lcom/owner/assertsparam/data/CategoryInfo;)V", "", "mSecondViewState", "getMSecondViewState", "()I", "setMSecondViewState", "(I)V", "mSelectedCategory", "mTopViewState", "getMTopViewState", "setMTopViewState", "refreshList", "getRefreshList", "setRefreshList", "saveSelectedCategory", "getSaveSelectedCategory", "setSaveSelectedCategory", "secondAndThirdCgList", "", "getSecondAndThirdCgList", "()Ljava/util/List;", "setSecondAndThirdCgList", "(Ljava/util/List;)V", "selectedFourthCg", "getSelectedFourthCg", "setSelectedFourthCg", "topCgList", "getTopCgList", "setTopCgList", "addCategory", "", "newCg", "addSecondAlert", "addThirdAlert", "second", "Lcom/owner/assertsparam/data/Footer;", "addTopAlert", "top", "deleteAlert", "category", "deleteCategory", "expandThirdCategory", "moreView", "Lcom/owner/assertsparam/viewmodel/ThirdCgMoreView;", "getChildList", "", "item", "getSubCategory", "parent", "isAgainClick", "itemLongClick", "itemOnClick", "loadSecondCategory", "loadTopCategory", "restoreState", "selectedCategory", "setClickState", "setLongClickState", "list", "thirdItemClick", "updateAlert", "updateCategory", "updateThirdAlert", "third", "Companion", "assertsparam_debug"})
public final class CategoryFgViewModel extends com.owner.baselibrary.viewmodel.BaseViewModel<com.owner.assertsparam.model.repository.AssertsParamRepository> {
    @org.jetbrains.annotations.NotNull()
    private java.lang.String categoryName;
    @org.jetbrains.annotations.NotNull()
    private android.arch.lifecycle.MutableLiveData<kotlin.Pair<java.lang.String, com.owner.assertsparam.data.CategoryInfo>> action;
    @org.jetbrains.annotations.NotNull()
    private android.arch.lifecycle.MutableLiveData<kotlin.Pair<java.lang.String, com.owner.assertsparam.data.CategoryInfo>> saveSelectedCategory;
    @org.jetbrains.annotations.NotNull()
    private android.arch.lifecycle.MutableLiveData<kotlin.Pair<java.lang.String, com.owner.assertsparam.data.CategoryInfo>> gotoQueryAsserts;
    @org.jetbrains.annotations.NotNull()
    private android.arch.lifecycle.MutableLiveData<java.lang.Boolean> expandList;
    @org.jetbrains.annotations.NotNull()
    private android.arch.lifecycle.MutableLiveData<kotlin.Pair<java.lang.String, java.lang.Integer>> refreshList;
    @org.jetbrains.annotations.NotNull()
    private android.arch.lifecycle.MutableLiveData<com.owner.assertsparam.data.CategoryInfo> selectedFourthCg;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.owner.assertsparam.data.CategoryInfo> topCgList;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.owner.assertsparam.data.CategoryInfo> secondAndThirdCgList;
    private com.owner.assertsparam.data.CategoryInfo mSelectedCategory;
    @org.jetbrains.annotations.NotNull()
    private com.owner.assertsparam.data.CategoryInfo mCurrentTopCategory;
    private boolean isVisibleTop;
    private int mSecondViewState;
    private int mTopViewState;
    private final java.lang.String tableName = null;
    private final boolean isEdited = false;
    private final boolean isQuery = false;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_SELECTED_ACTION = "select";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_UPDATE_ACTION = "update";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_DELETE_ACTION = "delete";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_ADD_ACTION = "add";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_ADD_THIRD_ACTION = "add_third";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_UPDATE_THIRD_ACTION = "update_third";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_REFRESH_LIST = "refresh";
    public static final com.owner.assertsparam.viewmodel.CategoryFgViewModel.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCategoryName() {
        return null;
    }
    
    public final void setCategoryName(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.arch.lifecycle.MutableLiveData<kotlin.Pair<java.lang.String, com.owner.assertsparam.data.CategoryInfo>> getAction() {
        return null;
    }
    
    public final void setAction(@org.jetbrains.annotations.NotNull()
    android.arch.lifecycle.MutableLiveData<kotlin.Pair<java.lang.String, com.owner.assertsparam.data.CategoryInfo>> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.arch.lifecycle.MutableLiveData<kotlin.Pair<java.lang.String, com.owner.assertsparam.data.CategoryInfo>> getSaveSelectedCategory() {
        return null;
    }
    
    public final void setSaveSelectedCategory(@org.jetbrains.annotations.NotNull()
    android.arch.lifecycle.MutableLiveData<kotlin.Pair<java.lang.String, com.owner.assertsparam.data.CategoryInfo>> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.arch.lifecycle.MutableLiveData<kotlin.Pair<java.lang.String, com.owner.assertsparam.data.CategoryInfo>> getGotoQueryAsserts() {
        return null;
    }
    
    public final void setGotoQueryAsserts(@org.jetbrains.annotations.NotNull()
    android.arch.lifecycle.MutableLiveData<kotlin.Pair<java.lang.String, com.owner.assertsparam.data.CategoryInfo>> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.arch.lifecycle.MutableLiveData<java.lang.Boolean> getExpandList() {
        return null;
    }
    
    public final void setExpandList(@org.jetbrains.annotations.NotNull()
    android.arch.lifecycle.MutableLiveData<java.lang.Boolean> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.arch.lifecycle.MutableLiveData<kotlin.Pair<java.lang.String, java.lang.Integer>> getRefreshList() {
        return null;
    }
    
    public final void setRefreshList(@org.jetbrains.annotations.NotNull()
    android.arch.lifecycle.MutableLiveData<kotlin.Pair<java.lang.String, java.lang.Integer>> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.arch.lifecycle.MutableLiveData<com.owner.assertsparam.data.CategoryInfo> getSelectedFourthCg() {
        return null;
    }
    
    public final void setSelectedFourthCg(@org.jetbrains.annotations.NotNull()
    android.arch.lifecycle.MutableLiveData<com.owner.assertsparam.data.CategoryInfo> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.owner.assertsparam.data.CategoryInfo> getTopCgList() {
        return null;
    }
    
    public final void setTopCgList(@org.jetbrains.annotations.NotNull()
    java.util.List<com.owner.assertsparam.data.CategoryInfo> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.owner.assertsparam.data.CategoryInfo> getSecondAndThirdCgList() {
        return null;
    }
    
    public final void setSecondAndThirdCgList(@org.jetbrains.annotations.NotNull()
    java.util.List<com.owner.assertsparam.data.CategoryInfo> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @android.databinding.Bindable()
    public final com.owner.assertsparam.data.CategoryInfo getMCurrentTopCategory() {
        return null;
    }
    
    public final void setMCurrentTopCategory(@org.jetbrains.annotations.NotNull()
    com.owner.assertsparam.data.CategoryInfo value) {
    }
    
    @android.databinding.Bindable()
    public final boolean isVisibleTop() {
        return false;
    }
    
    public final void setVisibleTop(boolean value) {
    }
    
    @android.databinding.Bindable()
    public final int getMSecondViewState() {
        return 0;
    }
    
    public final void setMSecondViewState(int value) {
    }
    
    @android.databinding.Bindable()
    public final int getMTopViewState() {
        return 0;
    }
    
    public final void setMTopViewState(int value) {
    }
    
    /**
     * * 一级分类点击用于加载二、三级分类。二、三级分类点击事件分情况处理。如果是查询状态，调用查询接口；
     *     * 如果是非查询状态，只用于刷新列表，取消长按状态。
     */
    public final void itemOnClick(@org.jetbrains.annotations.NotNull()
    com.owner.assertsparam.data.CategoryInfo item) {
    }
    
    /**
     * * 如果三级分类有子类，则进入下一级。否则再判断
     */
    public final void thirdItemClick(@org.jetbrains.annotations.NotNull()
    com.owner.assertsparam.data.CategoryInfo item) {
    }
    
    /**
     * * 选择二、三级分类，第一次选择，第二次取消选择
     */
    public final void selectedCategory(@org.jetbrains.annotations.NotNull()
    com.owner.assertsparam.data.CategoryInfo item) {
    }
    
    private final void isAgainClick(com.owner.assertsparam.data.CategoryInfo item) {
    }
    
    /**
     * *  从二三级分类列表中过滤出某个二级分类的子类
     *     *
     */
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.owner.assertsparam.data.CategoryInfo> getChildList(@org.jetbrains.annotations.NotNull()
    com.owner.assertsparam.data.CategoryInfo item) {
        return null;
    }
    
    /**
     * * 设置分类选择时的状态
     */
    private final void setClickState(com.owner.assertsparam.data.CategoryInfo item) {
    }
    
    /**
     * * 还原分类信息状态
     */
    public final void restoreState(@org.jetbrains.annotations.NotNull()
    com.owner.assertsparam.data.CategoryInfo category) {
    }
    
    /**
     * * 列表项目长按事件,如果可编辑状态时返回true
     */
    public final boolean itemLongClick(@org.jetbrains.annotations.NotNull()
    com.owner.assertsparam.data.CategoryInfo item) {
        return false;
    }
    
    /**
     * * 设置分类项目长按时状态
     */
    private final void setLongClickState(com.owner.assertsparam.data.CategoryInfo item, java.util.List<com.owner.assertsparam.data.CategoryInfo> list) {
    }
    
    /**
     * * 展开和收缩三级分类列表
     */
    public final void expandThirdCategory(@org.jetbrains.annotations.NotNull()
    com.owner.assertsparam.viewmodel.ThirdCgMoreView moreView) {
    }
    
    /**
     * * 获取子分类
     */
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.owner.assertsparam.data.CategoryInfo> getSubCategory(@org.jetbrains.annotations.NotNull()
    com.owner.assertsparam.data.CategoryInfo parent) {
        return null;
    }
    
    /**
     * * 加载一级分类列表
     */
    private final void loadTopCategory() {
    }
    
    /**
     * * 加载一级分类的二级以及其下的三级分类。
     *     * 原理：分别产生两个列表流，一个是二级分类，然后利用二级分类得到三级分类，最后合并两个列表流
     *     * @item: 一级分类
     */
    private final void loadSecondCategory(com.owner.assertsparam.data.CategoryInfo item) {
    }
    
    /**
     * * 发送增加一级分类请求
     *     *
     */
    public final void addTopAlert(@org.jetbrains.annotations.NotNull()
    com.owner.assertsparam.data.CategoryInfo top) {
    }
    
    /**
     * * 发送增加二级分类请求
     */
    public final void addSecondAlert() {
    }
    
    /**
     * * 发送增加三级分类请求
     */
    public final void addThirdAlert(@org.jetbrains.annotations.NotNull()
    com.owner.assertsparam.data.Footer second) {
    }
    
    /**
     * * 对数据库执行保存操作,保存成功后返回新增对象。对新增对象区分，一级分类加入topCgList中，刷新列表。
     *     * 二级分类加入secondAndThirdCgList中，刷新二级列表。
     *     *
     */
    public final void addCategory(@org.jetbrains.annotations.NotNull()
    com.owner.assertsparam.data.CategoryInfo newCg) {
    }
    
    /**
     * * 启动修改一级或二级分类对话框
     */
    public final void updateAlert(@org.jetbrains.annotations.NotNull()
    com.owner.assertsparam.data.CategoryInfo category) {
    }
    
    /**
     * * 启动修改三级分类对话框
     */
    public final void updateThirdAlert(@org.jetbrains.annotations.NotNull()
    com.owner.assertsparam.data.CategoryInfo third) {
    }
    
    /**
     * * 对数据库执行修改操作
     */
    public final void updateCategory(@org.jetbrains.annotations.NotNull()
    com.owner.assertsparam.data.CategoryInfo category) {
    }
    
    /**
     * * 启动删除类别对话框
     */
    public final void deleteAlert(@org.jetbrains.annotations.NotNull()
    com.owner.assertsparam.data.CategoryInfo category) {
    }
    
    /**
     * *  对数据库执行删除操作
     */
    public final void deleteCategory(@org.jetbrains.annotations.NotNull()
    com.owner.assertsparam.data.CategoryInfo category) {
    }
    
    public final boolean isEdited() {
        return false;
    }
    
    public final boolean isQuery() {
        return false;
    }
    
    public CategoryFgViewModel(@org.jetbrains.annotations.NotNull()
    java.lang.String tableName, boolean isEdited, boolean isQuery) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 11}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/owner/assertsparam/viewmodel/CategoryFgViewModel$Companion;", "", "()V", "KEY_ADD_ACTION", "", "KEY_ADD_THIRD_ACTION", "KEY_DELETE_ACTION", "KEY_REFRESH_LIST", "KEY_SELECTED_ACTION", "KEY_UPDATE_ACTION", "KEY_UPDATE_THIRD_ACTION", "assertsparam_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}