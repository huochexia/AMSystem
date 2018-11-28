package com.owner.assertsparam.model.network.service;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 11}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J#\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\tH\u0097\u0001J\u001d\u0010\n\u001a\u00020\u000b2\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\f\u001a\u00020\u0007H\u0097\u0001J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0004H\u0097\u0001J#\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00042\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\u0011\u001a\u00020\u0007H\u0097\u0001J\u0019\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u00042\b\b\u0001\u0010\u0014\u001a\u00020\u0007H\u0097\u0001J\'\u0010\u0015\u001a\u00020\u000b2\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\f\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\u0005H\u0097\u0001\u00a8\u0006\u0016"}, d2 = {"Lcom/owner/assertsparam/model/network/service/AssertsParamService;", "Lcom/owner/assertsparam/model/network/service/AssertsParamApi;", "()V", "createCategory", "Lio/reactivex/Observable;", "Lcom/owner/assertsparam/data/CategoryInfo;", "table", "", "request", "Lcom/owner/assertsparam/model/network/entites/CreateCgReq;", "deleteCategory", "Lio/reactivex/Completable;", "objectId", "getAllManager", "Lcom/owner/assertsparam/model/network/entites/QueryManagerResp;", "getCategory", "Lcom/owner/assertsparam/model/network/entites/GetCategoryList;", "condition", "getManager", "Lcom/owner/assertsparam/data/Manager;", "userId", "updateCategory", "assertsparam_debug"})
public final class AssertsParamService implements com.owner.assertsparam.model.network.service.AssertsParamApi {
    public static final com.owner.assertsparam.model.network.service.AssertsParamService INSTANCE = null;
    
    private AssertsParamService() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    @retrofit2.http.POST(value = "1.1/classes/{table}?fetchWhenSave=true")
    public io.reactivex.Observable<com.owner.assertsparam.data.CategoryInfo> createCategory(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Path(value = "table")
    java.lang.String table, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.owner.assertsparam.model.network.entites.CreateCgReq request) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    @retrofit2.http.DELETE(value = "1.1/classes/{table}/{id}")
    public io.reactivex.Completable deleteCategory(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Path(value = "table")
    java.lang.String table, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Path(value = "id")
    java.lang.String objectId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    @retrofit2.http.GET(value = "1.1/users")
    public io.reactivex.Observable<com.owner.assertsparam.model.network.entites.QueryManagerResp> getAllManager() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    @retrofit2.http.GET(value = "1.1/classes/{table}")
    public io.reactivex.Observable<com.owner.assertsparam.model.network.entites.GetCategoryList> getCategory(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Path(value = "table")
    java.lang.String table, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "where")
    java.lang.String condition) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    @retrofit2.http.GET(value = "1.1/users/{id}")
    public io.reactivex.Observable<com.owner.assertsparam.data.Manager> getManager(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Path(value = "id")
    java.lang.String userId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    @retrofit2.http.PUT(value = "1.1/classes/{table}/{id}")
    public io.reactivex.Completable updateCategory(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Path(value = "table")
    java.lang.String table, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Path(value = "id")
    java.lang.String objectId, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.owner.assertsparam.data.CategoryInfo request) {
        return null;
    }
}