package com.owner.assetsparam.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.owner.assetsparam.BR

/**
 * 用于控制三级分类显示部分还是全部
 * Created by Administrator on 2018/10/26 0026
 */
class ThirdCgMoreView : BaseObservable() {
    //列表内容大于4个时为true，显示“更多”
    @get:Bindable
    var isMore: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.more)
        }
    //列表内容是收缩显示为false，展开全部显示是true
    @get:Bindable
    var isExpanded: Boolean = false
    set(value) {
        field = value
        notifyPropertyChanged(BR.expanded)
    }


}