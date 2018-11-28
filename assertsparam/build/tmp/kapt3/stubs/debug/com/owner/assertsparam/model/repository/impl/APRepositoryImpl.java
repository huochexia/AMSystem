package com.owner.assertsparam.model.repository.impl;

import java.lang.System;

/**
 * * Created by Administrator on 2018/10/19 0019
 */
@kotlin.Metadata(mv = {1, 1, 11}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J.\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0007H\u0016J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0007H\u0016J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0004H\u0016J\u001e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0016J\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u00042\u0006\u0010\u0014\u001a\u00020\u0007H\u0016J\u0018\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0005H\u0016\u00a8\u0006\u0017"}, d2 = {"Lcom/owner/assertsparam/model/repository/impl/APRepositoryImpl;", "Lcom/owner/assertsparam/model/repository/AssertsParamRepository;", "()V", "createCategory", "Lio/reactivex/Observable;", "Lcom/owner/assertsparam/data/CategoryInfo;", "className", "", "name", "parentId", "imageUrl", "deleteCategory", "Lio/reactivex/Completable;", "objectId", "getAllManager", "Lcom/owner/assertsparam/model/network/entites/QueryManagerResp;", "getCategory", "Lcom/owner/assertsparam/model/network/entites/GetCategoryList;", "getManager", "Lcom/owner/assertsparam/data/Manager;", "userId", "updateCategory", "category", "assertsparam_debug"})
public final class APRepositoryImpl implements com.owner.assertsparam.model.repository.AssertsParamRepository {
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Observable<com.owner.assertsparam.data.Manager> getManager(@org.jetbrains.annotations.NotNull()
    java.lang.String userId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Observable<com.owner.assertsparam.model.network.entites.QueryManagerResp> getAllManager() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Observable<com.owner.assertsparam.model.network.entites.GetCategoryList> getCategory(@org.jetbrains.annotations.NotNull()
    java.lang.String className, @org.jetbrains.annotations.NotNull()
    java.lang.String parentId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Observable<com.owner.assertsparam.data.CategoryInfo> createCategory(@org.jetbrains.annotations.NotNull()
    java.lang.String className, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String parentId, @org.jetbrains.annotations.NotNull()
    java.lang.String imageUrl) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Completable deleteCategory(@org.jetbrains.annotations.NotNull()
    java.lang.String className, @org.jetbrains.annotations.NotNull()
    java.lang.String objectId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Completable updateCategory(@org.jetbrains.annotations.NotNull()
    java.lang.String className, @org.jetbrains.annotations.NotNull()
    com.owner.assertsparam.data.CategoryInfo category) {
        return null;
    }
    
    public APRepositoryImpl() {
        super();
    }
}