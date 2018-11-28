package com.owner.assertsparam.model.network.service;

import java.lang.System;

/**
 * *
 * * Created by Liuyong on 2018-10-28.It's AMSystem
 * *@description:
 */
@kotlin.Metadata(mv = {1, 1, 11}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\bH\'J\u001c\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u000b\u001a\u00020\u0006H\'J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0003H\'J\"\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0010\u001a\u00020\u0006H\'J\u0018\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00032\b\b\u0001\u0010\u0013\u001a\u00020\u0006H\'J&\u0010\u0014\u001a\u00020\n2\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u000b\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u0004H\'\u00a8\u0006\u0015"}, d2 = {"Lcom/owner/assertsparam/model/network/service/AssertsParamApi;", "", "createCategory", "Lio/reactivex/Observable;", "Lcom/owner/assertsparam/data/CategoryInfo;", "table", "", "request", "Lcom/owner/assertsparam/model/network/entites/CreateCgReq;", "deleteCategory", "Lio/reactivex/Completable;", "objectId", "getAllManager", "Lcom/owner/assertsparam/model/network/entites/QueryManagerResp;", "getCategory", "Lcom/owner/assertsparam/model/network/entites/GetCategoryList;", "condition", "getManager", "Lcom/owner/assertsparam/data/Manager;", "userId", "updateCategory", "assertsparam_debug"})
public abstract interface AssertsParamApi {
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "1.1/classes/{table}?fetchWhenSave=true")
    public abstract io.reactivex.Observable<com.owner.assertsparam.data.CategoryInfo> createCategory(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Path(value = "table")
    java.lang.String table, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.owner.assertsparam.model.network.entites.CreateCgReq request);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "1.1/classes/{table}")
    public abstract io.reactivex.Observable<com.owner.assertsparam.model.network.entites.GetCategoryList> getCategory(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Path(value = "table")
    java.lang.String table, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "where")
    java.lang.String condition);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.DELETE(value = "1.1/classes/{table}/{id}")
    public abstract io.reactivex.Completable deleteCategory(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Path(value = "table")
    java.lang.String table, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Path(value = "id")
    java.lang.String objectId);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.PUT(value = "1.1/classes/{table}/{id}")
    public abstract io.reactivex.Completable updateCategory(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Path(value = "table")
    java.lang.String table, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Path(value = "id")
    java.lang.String objectId, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.owner.assertsparam.data.CategoryInfo request);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "1.1/users")
    public abstract io.reactivex.Observable<com.owner.assertsparam.model.network.entites.QueryManagerResp> getAllManager();
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "1.1/users/{id}")
    public abstract io.reactivex.Observable<com.owner.assertsparam.data.Manager> getManager(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Path(value = "id")
    java.lang.String userId);
}