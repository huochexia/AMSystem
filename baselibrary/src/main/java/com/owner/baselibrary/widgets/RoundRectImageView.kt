/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.owner.baselibrary.widgets

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.ImageView
import com.owner.baselibrary.R
import org.jetbrains.anko.dimen

/**
 *  改造ImageView使其具有圆角
 * Created by Liuyong on 2018-10-06.
 *@description:
 */
class RoundRectImageView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr) {

    val radius = dimen(R.dimen.common_radius).toFloat()

    private val radiusArray:FloatArray = floatArrayOf(radius,radius,radius,radius,0.0f,0.0f,0.0f,0.0f)

    private fun drawRoundAngle(paramCanvas: Canvas) {
        val paint = Paint()
        paint.isAntiAlias = true
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        val path = Path()
        path.addRoundRect(RectF(0.0f,0.0f,width.toFloat(),height.toFloat()),this.radiusArray,
                Path.Direction.CW)
        path.fillType = Path.FillType.INVERSE_WINDING
        paramCanvas.drawPath(path,paint)
    }


    override fun draw(canvas: Canvas) {
        var bitmap = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888)
        var localCanvas = Canvas(bitmap)
        if (bitmap.isRecycled) {
            bitmap = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888)
            localCanvas = Canvas(bitmap)
        }
        super.draw(localCanvas)
        drawRoundAngle(localCanvas)
        val paint = Paint()
        paint.xfermode = null
        canvas.drawBitmap(bitmap,0.0f,0.0f,paint)
        bitmap.recycle()
    }
}