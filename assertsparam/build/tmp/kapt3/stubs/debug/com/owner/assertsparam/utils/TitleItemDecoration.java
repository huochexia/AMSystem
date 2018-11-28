package com.owner.assertsparam.utils;

import java.lang.System;

/**
 * * 有分类title的 ItemDecoration
 */
@kotlin.Metadata(mv = {1, 1, 11}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \"2\u00020\u0001:\u0001\"B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J8\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\rH\u0002J(\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J \u0010 \u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J \u0010!\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2 = {"Lcom/owner/assertsparam/utils/TitleItemDecoration;", "Landroid/support/v7/widget/RecyclerView$ItemDecoration;", "context", "Landroid/content/Context;", "mData", "", "Lcom/owner/assertsparam/data/Manager;", "(Landroid/content/Context;Ljava/util/List;)V", "mBounds", "Landroid/graphics/Rect;", "mPaint", "Landroid/graphics/Paint;", "mTitleHeight", "", "drawTitle", "", "c", "Landroid/graphics/Canvas;", "left", "right", "child", "Landroid/view/View;", "params", "Landroid/support/v7/widget/RecyclerView$LayoutParams;", "position", "getItemOffsets", "outRect", "view", "parent", "Landroid/support/v7/widget/RecyclerView;", "state", "Landroid/support/v7/widget/RecyclerView$State;", "onDraw", "onDrawOver", "Companion", "assertsparam_debug"})
public final class TitleItemDecoration extends android.support.v7.widget.RecyclerView.ItemDecoration {
    private final android.graphics.Paint mPaint = null;
    private final android.graphics.Rect mBounds = null;
    private final int mTitleHeight = 0;
    private final java.util.List<com.owner.assertsparam.data.Manager> mData = null;
    private static final java.lang.String TAG = "TitleItemDecoration";
    private static final int TITLE_BG_COLOR = 0;
    private static final int TITLE_TEXT_COLOR = 0;
    private static int mTitleTextSize;
    public static final com.owner.assertsparam.utils.TitleItemDecoration.Companion Companion = null;
    
    @java.lang.Override()
    public void onDraw(@org.jetbrains.annotations.NotNull()
    android.graphics.Canvas c, @org.jetbrains.annotations.NotNull()
    android.support.v7.widget.RecyclerView parent, @org.jetbrains.annotations.NotNull()
    android.support.v7.widget.RecyclerView.State state) {
    }
    
    /**
     * * 绘制Title区域背景和文字的方法
     *     * 最先调用，绘制最下层的title
     *     * @param c
     *     * @param left
     *     * @param right
     *     * @param child
     *     * @param params
     *     * @param position
     */
    private final void drawTitle(android.graphics.Canvas c, int left, int right, android.view.View child, android.support.v7.widget.RecyclerView.LayoutParams params, int position) {
    }
    
    /**
     * * 最后调用，绘制最上层的title
     *     * @param c
     *     * @param parent
     *     * @param state
     */
    @java.lang.Override()
    public void onDrawOver(@org.jetbrains.annotations.NotNull()
    android.graphics.Canvas c, @org.jetbrains.annotations.NotNull()
    android.support.v7.widget.RecyclerView parent, @org.jetbrains.annotations.NotNull()
    android.support.v7.widget.RecyclerView.State state) {
    }
    
    @java.lang.Override()
    public void getItemOffsets(@org.jetbrains.annotations.NotNull()
    android.graphics.Rect outRect, @org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.NotNull()
    android.support.v7.widget.RecyclerView parent, @org.jetbrains.annotations.NotNull()
    android.support.v7.widget.RecyclerView.State state) {
    }
    
    public TitleItemDecoration(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.util.List<com.owner.assertsparam.data.Manager> mData) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 11}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/owner/assertsparam/utils/TitleItemDecoration$Companion;", "", "()V", "TAG", "", "TITLE_BG_COLOR", "", "TITLE_TEXT_COLOR", "mTitleTextSize", "assertsparam_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}