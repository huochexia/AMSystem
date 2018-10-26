package com.owner.assertsparam.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.owner.assertsparam.BR

/**
 * 用于控制三级分类显示部分还是全部
 * Created by Administrator on 2018/10/26 0026
 */
class ThirdCgMoreView : BaseObservable() {

    @get:Bindable
    var isMore: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.more)
        }
}