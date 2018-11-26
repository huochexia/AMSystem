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
package com.owner.assetsparam.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.databinding.Bindable
import com.kennyc.view.MultiStateView
import com.owner.assetsparam.BR
import com.owner.assetsparam.data.Manager
import com.owner.assetsparam.model.repository.AssetsParamRepository
import com.owner.assetsparam.model.repository.impl.APRepositoryImpl
import com.owner.assetsparam.utils.PinyinComparator
import com.owner.baselibrary.ext.execute
import com.owner.baselibrary.utils.PinyinUtils
import com.owner.baselibrary.viewmodel.BaseViewModel
import java.util.*

/**
 *
 * Created by Liuyong on 2018-10-30.It's AMSystem
 *@description:
 */
class ManagerViewModel(val isEdited:Boolean,val isQuery:Boolean) : BaseViewModel<AssetsParamRepository>() {

    private var mComparator = PinyinComparator()
    private var mManagerList = mutableListOf<Manager>()
    private var mSortList = mutableListOf<Manager>()

    private var currentSelected: Manager? = null
    var selectedManager = MutableLiveData<Manager>()
    var gotoQueryAsserts = MutableLiveData<Manager>()
    var refresh = MutableLiveData<String>()
    var action = MutableLiveData<String>()//增改删
    @get:Bindable
    var mManagerViewState = MultiStateView.VIEW_STATE_EMPTY
        set(value) {
            field = value
            notifyPropertyChanged(BR.mManagerViewState)
        }

    init {
        repo = APRepositoryImpl()
        fillData()
    }

    /**
     * 将原始列表进行排序后，复制到用于显示的列表中，保存原始列表用于查找。查询是在原表中进行，显示
     * 的是查找结果。 因为显示用的列表在过程中会不断变化。
     */
    fun getSortList(): MutableList<Manager> {
        Collections.sort(mManagerList, mComparator)
        mSortList.clear()//必须清空，因为下面使用的是增加，如果不清空，该方法多次调时会出现重复数据
        mSortList.addAll(mManagerList)
        return mSortList
    }

    private fun fillData() {
        val disposable = repo.getAllManager().execute()
                .subscribe({
                    mManagerList.addAll(it.results)
                }, {
                    mManagerViewState = MultiStateView.VIEW_STATE_ERROR
                }, {
                    mManagerViewState = MultiStateView.VIEW_STATE_CONTENT
                    refresh.value = "refresh"

                }, {
                    mManagerViewState = MultiStateView.VIEW_STATE_LOADING
                })
        compositeDisposable.add(disposable)
    }

    /**
     * 通过Id获取用户
     */
    fun getManager(userId: String) {

        val disposable = repo.getManager(userId).execute()
                .subscribe {
                    mManagerList.add(it)
                    refresh.value = "refresh"
                }
        compositeDisposable.add(disposable)
    }

    /**
     * 根据输入框中的值来过滤数据并更新RecyclerView
     *
     * @param filterStr
     */
    fun filterData(filterStr: String) {
        var filterDateList = mutableListOf<Manager>()
        if (filterStr.isNullOrEmpty()) {
            filterDateList = mManagerList
        } else {
            filterDateList.clear()
            for (manager in mManagerList) {
                val name = manager.username
                if (name.indexOf(filterStr) != -1 ||
                        PinyinUtils.getFirstSpell(name).startsWith(filterStr)
                        //不区分大小写
                        || PinyinUtils.getFirstSpell(name).toLowerCase().startsWith(filterStr)
                        || PinyinUtils.getFirstSpell(name).toUpperCase().startsWith(filterStr)) {
                    filterDateList.add(manager)
                }
            }
        }

        // 根据a-z进行排序
        Collections.sort(filterDateList, mComparator)
        mSortList.clear()
        mSortList.addAll(filterDateList)
    }

    /**
     *  点击事件，判断当前视图状态分别进行处理，如果是可编辑，则进行编辑界面；如果是查询，则调用查询
     *  接口启动查询；如果是选择，则显示选择状态
     */
    fun itemOnClick(user: Manager) {
        //选择状态
        if (!isEdited && !isQuery) {
            isAgainClick(user)
        }
        if (isEdited && !isQuery) {
            println("当前是编辑状态")
        }
        if (!isEdited && isQuery) {
            gotoQueryAsserts.value = user
        }
    }

    private fun setSelectedState(user: Manager) {
        mManagerList.forEach {
            it.isSelected = false
        }
        user.isSelected = true
        currentSelected = user
        refresh.value = "OnClick"
    }

    private fun isAgainClick(user: Manager) {
        if (currentSelected?.objectId ?: "" == user.objectId) {
            user.isSelected = !user.isSelected
            refresh.value = "Again"

        } else {
            setSelectedState(user)
        }
        currentSelected = if (user.isSelected) {
            user
        } else {
            null
        }
        selectedManager.value = currentSelected ?: Manager()
    }

    /**
     * 增加管理人
     */
    fun addManager() {
        action.value = "add"
    }
}
