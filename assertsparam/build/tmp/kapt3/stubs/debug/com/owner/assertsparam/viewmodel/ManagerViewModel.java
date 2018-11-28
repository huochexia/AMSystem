package com.owner.assertsparam.viewmodel;

import java.lang.System;

/**
 * *
 * * Created by Liuyong on 2018-10-30.It's AMSystem
 * *@description:
 */
@kotlin.Metadata(mv = {1, 1, 11}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u000b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010&\u001a\u00020\'J\b\u0010(\u001a\u00020\'H\u0002J\u000e\u0010)\u001a\u00020\'2\u0006\u0010*\u001a\u00020\tJ\u000e\u0010+\u001a\u00020\'2\u0006\u0010,\u001a\u00020\tJ\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0017J\u0010\u0010.\u001a\u00020\'2\u0006\u0010/\u001a\u00020\u000fH\u0002J\u000e\u00100\u001a\u00020\'2\u0006\u0010/\u001a\u00020\u000fJ\u0010\u00101\u001a\u00020\'2\u0006\u0010/\u001a\u00020\u000fH\u0002R \u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R \u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u000b\"\u0004\b\u0012\u0010\rR\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0013R\u0011\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R&\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u00198\u0007@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R \u0010 \u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u000b\"\u0004\b\"\u0010\rR \u0010#\u001a\b\u0012\u0004\u0012\u00020\u000f0\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u000b\"\u0004\b%\u0010\r\u00a8\u00062"}, d2 = {"Lcom/owner/assertsparam/viewmodel/ManagerViewModel;", "Lcom/owner/baselibrary/viewmodel/BaseViewModel;", "Lcom/owner/assertsparam/model/repository/AssertsParamRepository;", "isEdited", "", "isQuery", "(ZZ)V", "action", "Landroid/arch/lifecycle/MutableLiveData;", "", "getAction", "()Landroid/arch/lifecycle/MutableLiveData;", "setAction", "(Landroid/arch/lifecycle/MutableLiveData;)V", "currentSelected", "Lcom/owner/assertsparam/data/Manager;", "gotoQueryAsserts", "getGotoQueryAsserts", "setGotoQueryAsserts", "()Z", "mComparator", "Lcom/owner/assertsparam/utils/PinyinComparator;", "mManagerList", "", "value", "", "mManagerViewState", "getMManagerViewState", "()I", "setMManagerViewState", "(I)V", "mSortList", "refresh", "getRefresh", "setRefresh", "selectedManager", "getSelectedManager", "setSelectedManager", "addManager", "", "fillData", "filterData", "filterStr", "getManager", "userId", "getSortList", "isAgainClick", "user", "itemOnClick", "setSelectedState", "assertsparam_debug"})
public final class ManagerViewModel extends com.owner.baselibrary.viewmodel.BaseViewModel<com.owner.assertsparam.model.repository.AssertsParamRepository> {
    private com.owner.assertsparam.utils.PinyinComparator mComparator;
    private java.util.List<com.owner.assertsparam.data.Manager> mManagerList;
    private java.util.List<com.owner.assertsparam.data.Manager> mSortList;
    private com.owner.assertsparam.data.Manager currentSelected;
    @org.jetbrains.annotations.NotNull()
    private android.arch.lifecycle.MutableLiveData<com.owner.assertsparam.data.Manager> selectedManager;
    @org.jetbrains.annotations.NotNull()
    private android.arch.lifecycle.MutableLiveData<com.owner.assertsparam.data.Manager> gotoQueryAsserts;
    @org.jetbrains.annotations.NotNull()
    private android.arch.lifecycle.MutableLiveData<java.lang.String> refresh;
    @org.jetbrains.annotations.NotNull()
    private android.arch.lifecycle.MutableLiveData<java.lang.String> action;
    private int mManagerViewState;
    private final boolean isEdited = false;
    private final boolean isQuery = false;
    
    @org.jetbrains.annotations.NotNull()
    public final android.arch.lifecycle.MutableLiveData<com.owner.assertsparam.data.Manager> getSelectedManager() {
        return null;
    }
    
    public final void setSelectedManager(@org.jetbrains.annotations.NotNull()
    android.arch.lifecycle.MutableLiveData<com.owner.assertsparam.data.Manager> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.arch.lifecycle.MutableLiveData<com.owner.assertsparam.data.Manager> getGotoQueryAsserts() {
        return null;
    }
    
    public final void setGotoQueryAsserts(@org.jetbrains.annotations.NotNull()
    android.arch.lifecycle.MutableLiveData<com.owner.assertsparam.data.Manager> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.arch.lifecycle.MutableLiveData<java.lang.String> getRefresh() {
        return null;
    }
    
    public final void setRefresh(@org.jetbrains.annotations.NotNull()
    android.arch.lifecycle.MutableLiveData<java.lang.String> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.arch.lifecycle.MutableLiveData<java.lang.String> getAction() {
        return null;
    }
    
    public final void setAction(@org.jetbrains.annotations.NotNull()
    android.arch.lifecycle.MutableLiveData<java.lang.String> p0) {
    }
    
    @android.databinding.Bindable()
    public final int getMManagerViewState() {
        return 0;
    }
    
    public final void setMManagerViewState(int value) {
    }
    
    /**
     * * 将原始列表进行排序后，复制到用于显示的列表中，保存原始列表用于查找。查询是在原表中进行，显示
     *     * 的是查找结果。 因为显示用的列表在过程中会不断变化。
     */
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.owner.assertsparam.data.Manager> getSortList() {
        return null;
    }
    
    private final void fillData() {
    }
    
    /**
     * * 通过Id获取用户
     */
    public final void getManager(@org.jetbrains.annotations.NotNull()
    java.lang.String userId) {
    }
    
    /**
     * * 根据输入框中的值来过滤数据并更新RecyclerView
     *     *
     *     * @param filterStr
     */
    public final void filterData(@org.jetbrains.annotations.NotNull()
    java.lang.String filterStr) {
    }
    
    /**
     * *  点击事件，判断当前视图状态分别进行处理，如果是可编辑，则进行编辑界面；如果是查询，则调用查询
     *     *  接口启动查询；如果是选择，则显示选择状态
     */
    public final void itemOnClick(@org.jetbrains.annotations.NotNull()
    com.owner.assertsparam.data.Manager user) {
    }
    
    private final void setSelectedState(com.owner.assertsparam.data.Manager user) {
    }
    
    private final void isAgainClick(com.owner.assertsparam.data.Manager user) {
    }
    
    /**
     * * 增加管理人
     */
    public final void addManager() {
    }
    
    public final boolean isEdited() {
        return false;
    }
    
    public final boolean isQuery() {
        return false;
    }
    
    public ManagerViewModel(boolean isEdited, boolean isQuery) {
        super();
    }
}