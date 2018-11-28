package com.owner.assertsparam.model.repository;

import java.lang.System;

/**
 * *
 * * Created by Administrator on 2018/10/19 0019
 */
@kotlin.Metadata(mv = {1, 1, 11}, bv = {1, 0, 2}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J.\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H&J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006H&J\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0003H&J\u001e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H&J\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00032\u0006\u0010\u0013\u001a\u00020\u0006H&J\u0018\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u0004H&\u00a8\u0006\u0016"}, d2 = {"Lcom/owner/assertsparam/model/repository/AssertsParamRepository;", "Lcom/owner/baselibrary/model/respository/BaseRepository;", "createCategory", "Lio/reactivex/Observable;", "Lcom/owner/assertsparam/data/CategoryInfo;", "className", "", "name", "parentId", "imageUrl", "deleteCategory", "Lio/reactivex/Completable;", "objectId", "getAllManager", "Lcom/owner/assertsparam/model/network/entites/QueryManagerResp;", "getCategory", "Lcom/owner/assertsparam/model/network/entites/GetCategoryList;", "getManager", "Lcom/owner/assertsparam/data/Manager;", "userId", "updateCategory", "category", "assertsparam_debug"})
public abstract interface AssertsParamRepository extends com.owner.baselibrary.model.respository.BaseRepository {
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Observable<com.owner.assertsparam.data.CategoryInfo> createCategory(@org.jetbrains.annotations.NotNull()
    java.lang.String className, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String parentId, @org.jetbrains.annotations.NotNull()
    java.lang.String imageUrl);
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Observable<com.owner.assertsparam.model.network.entites.GetCategoryList> getCategory(@org.jetbrains.annotations.NotNull()
    java.lang.String className, @org.jetbrains.annotations.NotNull()
    java.lang.String parentId);
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Completable deleteCategory(@org.jetbrains.annotations.NotNull()
    java.lang.String className, @org.jetbrains.annotations.NotNull()
    java.lang.String objectId);
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Completable updateCategory(@org.jetbrains.annotations.NotNull()
    java.lang.String className, @org.jetbrains.annotations.NotNull()
    com.owner.assertsparam.data.CategoryInfo category);
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Observable<com.owner.assertsparam.model.network.entites.QueryManagerResp> getAllManager();
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Observable<com.owner.assertsparam.data.Manager> getManager(@org.jetbrains.annotations.NotNull()
    java.lang.String userId);
}