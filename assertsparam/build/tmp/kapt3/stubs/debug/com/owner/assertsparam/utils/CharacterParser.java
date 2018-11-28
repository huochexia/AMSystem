package com.owner.assertsparam.utils;

import java.lang.System;

/**
 * * 汉字转换为拼音
 * *
 */
@kotlin.Metadata(mv = {1, 1, 11}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u000f\u001a\u00020\u0007J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0007H\u0002J\u0010\u0010\u0013\u001a\u00020\u00072\b\u0010\u0012\u001a\u0004\u0018\u00010\u0007R\u0016\u0010\u0003\u001a\n\u0018\u00010\u0004j\u0004\u0018\u0001`\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\u00078F\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\t\u00a8\u0006\u0015"}, d2 = {"Lcom/owner/assertsparam/utils/CharacterParser;", "", "()V", "buffer", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "resource", "", "getResource", "()Ljava/lang/String;", "setResource", "(Ljava/lang/String;)V", "spelling", "getSpelling", "convert", "str", "getChsAscii", "", "chs", "getSelling", "Companion", "assertsparam_debug"})
public final class CharacterParser {
    private java.lang.StringBuilder buffer;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String resource;
    private static final int[] pyvalue = null;
    @org.jetbrains.annotations.NotNull()
    private static java.lang.String[] pystr;
    @org.jetbrains.annotations.NotNull()
    private static final com.owner.assertsparam.utils.CharacterParser instance = null;
    public static final com.owner.assertsparam.utils.CharacterParser.Companion Companion = null;
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getResource() {
        return null;
    }
    
    public final void setResource(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSpelling() {
        return null;
    }
    
    /**
     * * 汉字转成ASCII码 * * @param chs * @return  
     */
    private final int getChsAscii(java.lang.String chs) {
        return 0;
    }
    
    /**
     * * 单字解析 * * @param str * @return  
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String convert(@org.jetbrains.annotations.NotNull()
    java.lang.String str) {
        return null;
    }
    
    /**
     * * 词组解析 * * @param chs * @return  
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSelling(@org.jetbrains.annotations.Nullable()
    java.lang.String chs) {
        return null;
    }
    
    public CharacterParser() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 11}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\"\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010\u000e\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/owner/assertsparam/utils/CharacterParser$Companion;", "", "()V", "instance", "Lcom/owner/assertsparam/utils/CharacterParser;", "getInstance", "()Lcom/owner/assertsparam/utils/CharacterParser;", "pystr", "", "", "getPystr", "()[Ljava/lang/String;", "setPystr", "([Ljava/lang/String;)V", "[Ljava/lang/String;", "pyvalue", "", "assertsparam_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String[] getPystr() {
            return null;
        }
        
        public final void setPystr(@org.jetbrains.annotations.NotNull()
        java.lang.String[] p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.owner.assertsparam.utils.CharacterParser getInstance() {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}