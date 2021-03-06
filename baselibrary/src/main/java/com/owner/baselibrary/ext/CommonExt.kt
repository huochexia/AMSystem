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
package com.owner.baselibrary.ext

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.support.design.widget.Snackbar
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import cn.carbs.android.avatarimageview.library.AvatarImageView
import com.bumptech.glide.Glide
import com.owner.baselibrary.common.SingleLiveEvent
import com.owner.baselibrary.utils.GlideUtils
import com.owner.baselibrary.widgets.DefaultTextWatcher
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.File


/**
 *
 * Created by Liuyong on 2018-09-21.It's AMSystem
 *@description:
 */

/**
 * Observable扩展,组合线程调度
 */

fun <T> Observable<T>.execute(): Observable<T> {
    return this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}


/**
 * Button扩展方法，判断按钮是否可用
 */
fun Button.enabled(et: EditText, method: () -> Boolean) {
    val btn = this
    et.addTextChangedListener(object : DefaultTextWatcher() {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            btn.isEnabled = method()
        }
    })

}

/**
 * ImageView扩展方法，通过Url加载网络图片
 */
fun ImageView.loadUrl(url: String) {
    GlideUtils.loadUrlImage(context,url,this)
}

/**
 * 路径判断，如果不是子目录，判断是不是文件，如果是文件则删除后创建
 */
fun File.ensureDir():Boolean{
    try {
        if (!isDirectory) {
            if (isFile) {
                delete()
            }
            return mkdirs()
        }
    } catch (e: Exception) {
        Log.w("FileExt",e.message)
    }
    return false

}
fun AvatarImageView.loadWithGlide(url: String?, textPlaceHolder: Char) {
    textPlaceHolder.toString().let {
        setTextAndColorSeed(it.toUpperCase(),it.hashCode().toString())
    }
    Glide.with(this.context).load(url).into(this)
}

/**
 * 扩展目的就是省去每次都写show（）方法
 */
fun View.showSnackbar(snackbarText: String, timeLength: Int) {
    Snackbar.make(this,snackbarText,timeLength).show()
}

/**
 *
 */
fun View.setupSnacker(lifecycleOwner: LifecycleOwner,
                      snackbarMessageLiveEvent: SingleLiveEvent<Int>, timeLength: Int) {
    snackbarMessageLiveEvent.observe(lifecycleOwner, Observer { it ->
        it?.let {
            showSnackbar(context.getString(it),timeLength)
        }
    })
}