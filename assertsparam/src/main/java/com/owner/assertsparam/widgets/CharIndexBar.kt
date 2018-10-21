package com.owner.assertsparam.widgets

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import com.owner.assertsparam.Interface.onIndexPressedListener
import com.owner.assertsparam.R

import java.util.Arrays
import java.util.HashMap

/**
 *  自定义视图，仿通讯录右侧字母索引
 * Created by Administrator on 2018/1/21 0021.
 */

class CharIndexBar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : View(context, attrs, defStyleAttr) {
    private val mCharList: List<String>
    private val textColorMap = HashMap<String, Int>()//用于存放每个索引的颜色
    private val mPaint: Paint
    private var mCharHeight: Int = 0
    private var mWidth: Int = 0
    private var mHeight: Int = 0

    private var mOnIndexPressedListener: onIndexPressedListener? = null
    private var mShowHintText: TextView? = null
    private var mLayoutManager: LinearLayoutManager? = null
//    private var mPersonList: List<Person>? = null
    /**
     * 得到按下事件监听器
     * @return
     */
    fun getmOnIndexPressedListener(): onIndexPressedListener? {
        return mOnIndexPressedListener
    }

    /**
     * 设置按下事件监听器
     * @param mOnIndexPressedListener
     */
    fun setmOnIndexPressedListener(mOnIndexPressedListener: onIndexPressedListener) {
        this.mOnIndexPressedListener = mOnIndexPressedListener
    }

    init {
        var textSize = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 15f, resources.displayMetrics).toInt()
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.CharIndexBar, defStyleAttr, 0)
        val n = typedArray.indexCount
        for (i in 0 until n) {
            val attr = typedArray.getIndex(i)
            when (attr) {
                R.styleable.CharIndexBar_textSize -> textSize = typedArray.getDimensionPixelSize(attr, 15)
            }
        }
        typedArray.recycle()
        mCharList = Arrays.asList(*INDEX_STRING)
        mPaint = Paint()
        mPaint.isAntiAlias = true
        mPaint.textSize = textSize.toFloat()
        initEvent()
        initMap()
    }

    /**
     * 初始化Map
     */
    fun initMap() {
        for (i in mCharList.indices) {
            textColorMap[mCharList[i]] = Color.GRAY
        }
    }

    /**
     * 初始化事件
     */
    fun initEvent() {
        setmOnIndexPressedListener(object : onIndexPressedListener {
            override fun onIndexPressed(index: Int, text: String) {
                if (mShowHintText != null) {
                    mShowHintText!!.visibility = View.VISIBLE
                    mShowHintText!!.text = text
                }
                if (mLayoutManager != null) {
//                    val position = getPersonIndex(text)
//                    if (position != -1) {
//                        mLayoutManager!!.scrollToPositionWithOffset(position, 0)
//                    }
                }
                initMap()
                textColorMap[text] = Color.BLUE
                postInvalidate()
            }

            override fun onMotionEventEnd() {
                if (mShowHintText != null) {
                    val handler = Handler()
                    val runnable = Runnable { mShowHintText!!.visibility = View.GONE }
                    handler.postDelayed(runnable, 3000)
                }
            }
        })
    }

    /**
     * 显示当前被按下的index的TextView
     *
     * @return
     */

    fun setShowHintText(mShowHintText: TextView): CharIndexBar {
        this.mShowHintText = mShowHintText
        return this
    }

    fun setmLayoutManager(mLayoutManager: LinearLayoutManager): CharIndexBar {
        this.mLayoutManager = mLayoutManager
        return this
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        //取出宽高的MeasureSpec  Mode 和Size
        val wMode = View.MeasureSpec.getMode(widthMeasureSpec)
        val wSize = View.MeasureSpec.getSize(widthMeasureSpec)
        val hMode = View.MeasureSpec.getMode(heightMeasureSpec)
        val hSize = View.MeasureSpec.getSize(heightMeasureSpec)
        var measureWidth = 0
        var measureHeight = 0//最终测量出来的宽高

        //得到合适宽度：
        val indexBounds = Rect()//存放每个绘制的index的Rect区域
        var index: String//每个要绘制的index内容
        for (i in mCharList.indices) {
            index = mCharList[i]
            mPaint.getTextBounds(index, 0, index.length, indexBounds)//测量计算文字所在矩形，可以得到宽高
            measureWidth = Math.max(indexBounds.width(), measureWidth)//循环结束后，得到index的最大宽度
            measureHeight = Math.max(indexBounds.width(), measureHeight)//循环结束后，得到index的最大高度，然后*size
        }
        measureHeight *= mCharList.size
        when (wMode) {
            View.MeasureSpec.EXACTLY -> measureWidth = wSize
            View.MeasureSpec.AT_MOST -> measureWidth = Math.min(measureWidth, wSize)//wSize此时是父控件能给子View分配的最大空间
            View.MeasureSpec.UNSPECIFIED -> {
            }
        }

        //得到合适的高度：
        when (hMode) {
            View.MeasureSpec.EXACTLY -> measureHeight = hSize
            View.MeasureSpec.AT_MOST -> measureHeight = Math.min(measureHeight, hSize)//wSize此时是父控件能给子View分配的最大空间
            View.MeasureSpec.UNSPECIFIED -> {
            }
        }

        setMeasuredDimension(measureWidth, measureHeight)


    }

    override fun onDraw(canvas: Canvas) {
        val t = paddingTop
        val BarBounds = Rect()
        var index: String//每个要绘制的index内容
        for (i in mCharList.indices) {
            index = mCharList[i]
            mPaint.color = textColorMap[mCharList[i]]!!
            mPaint.getTextBounds(index, 0, index.length, BarBounds)
            val fontMetrics = mPaint.fontMetrics
            val baseline = ((mCharHeight.toFloat() - fontMetrics.bottom - fontMetrics.top) / 2).toInt()
            canvas.drawText(index, (mWidth / 2 - BarBounds.width() / 2).toFloat(), (t + mCharHeight * i + baseline).toFloat(), mPaint)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = h
        mCharHeight = (mHeight - paddingTop - paddingBottom) / mCharList.size
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                val y = event.y
                //通过计算判断落点在哪个区域：
                var pressChar = ((y - paddingTop) / mCharHeight).toInt()
                //边界处理（在手指move时，有可能已经移出边界，防止越界）
                if (pressChar < 0) {
                    pressChar = 0
                } else if (pressChar >= mCharList.size) {
                    pressChar = mCharList.size - 1
                }
                //回调监听器
                if (null != mOnIndexPressedListener) {
                    mOnIndexPressedListener!!.onIndexPressed(pressChar, mCharList[pressChar])
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL ->

                if (mOnIndexPressedListener != null) {
                    mOnIndexPressedListener!!.onMotionEventEnd()
                }
            else -> if (mOnIndexPressedListener != null) {
                mOnIndexPressedListener!!.onMotionEventEnd()
            }
        }
        return true
    }

//    fun setPersonList(list: List<Person>) {
//        mPersonList = list
//    }

//    fun getPersonIndex(text: String): Int {
//        if (TextUtils.isEmpty(text)) {
//            return -1
//        }
//        for (i in mPersonList!!.indices) {
//            if (text == mPersonList!![i].getAcronym()) {
//                return i
//            }
//        }
//        return -1
//    }

    companion object {

        var INDEX_STRING = arrayOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
                "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z")
    }
}
