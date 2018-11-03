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
package com.owner.assertsparam.viewmodel

import com.owner.assertsparam.data.Manager
import com.owner.assertsparam.model.repository.AssertsParamRepository
import com.owner.assertsparam.model.repository.impl.APRepositoryImpl
import com.owner.assertsparam.utils.PinyinComparator
import com.owner.baselibrary.utils.PinyinUtils
import com.owner.baselibrary.viewmodel.BaseViewModel
import java.util.*

/**
 *
 * Created by Liuyong on 2018-10-30.It's AMSystem
 *@description:
 */
class ManagerViewModel : BaseViewModel<AssertsParamRepository>() {

    private var mComparator = PinyinComparator()
    private val mManagerList: MutableList<Manager>
    private var mSortList = mutableListOf<Manager>()

    init {
        repo = APRepositoryImpl()
        mManagerList = fillData(repo)
    }

    /**
     * 将原始列表进行排序后，复制到用于显示的列表中，保存原始列表用于查找
     * 因为显示用的列表在过程中会不断变化。
     */
    fun getSortList(): MutableList<Manager> {
        Collections.sort(mManagerList, mComparator)
        mSortList.clear()//必须清空，因为下面使用的是增加，如果不清空，该方法多次调时会出现重复数据
        mSortList.addAll(mManagerList)
        return mSortList
    }

    private fun fillData(repo: AssertsParamRepository): MutableList<Manager> {
        return repo.getManager()
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
                val name = manager.name
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

}
