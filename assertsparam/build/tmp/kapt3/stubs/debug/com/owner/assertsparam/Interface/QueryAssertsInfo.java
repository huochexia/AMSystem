package com.owner.assertsparam.Interface;

import java.lang.System;

/**
 * *
 * * Created by Liuyong on 2018-11-11.It's AMSystem
 * *@description:
 */
@kotlin.Metadata(mv = {1, 1, 11}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0001H&\u00a8\u0006\u0007"}, d2 = {"Lcom/owner/assertsparam/Interface/QueryAssertsInfo;", "", "queryAssert", "", "tablename", "", "condition", "assertsparam_debug"})
public abstract interface QueryAssertsInfo {
    
    public abstract void queryAssert(@org.jetbrains.annotations.NotNull()
    java.lang.String tablename, @org.jetbrains.annotations.NotNull()
    java.lang.Object condition);
}