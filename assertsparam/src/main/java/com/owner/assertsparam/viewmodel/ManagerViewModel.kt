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

import android.text.TextUtils
import com.owner.assertsparam.data.Manager
import com.owner.assertsparam.utils.PinyinComparator
import com.owner.baselibrary.model.respository.BaseRepository
import com.owner.baselibrary.utils.PinyinUtils
import com.owner.baselibrary.viewmodel.BaseViewModel
import java.util.*

/**
 *
 * Created by Liuyong on 2018-10-30.It's AMSystem
 *@description:
 */
class ManagerViewModel : BaseViewModel<BaseRepository>() {
    private var mComparator = PinyinComparator()
    val mManagerList = mutableListOf<Manager>()

   init {
       val user1=Manager("张三","客房部",phone = "18932902193")
       val user2=Manager("李三","客房部",phone = "18932902193")
       val user3=Manager("王三","客房部",phone = "18932902193")
       val user4=Manager("刘二","客房部",phone = "18932902193")
       val user5=Manager("刘三","工程",phone = "18932902193")
       val user6=Manager("赵四","会服部",phone = "18932902193")
       val user7=Manager("陈小","客房部",phone = "18932902193")
       val user8=Manager("张三","客房部",phone = "18932902193")
       mManagerList.add(user1)
       mManagerList.add(user2)
       mManagerList.add(user3)
       mManagerList.add(user4)
       mManagerList.add(user5)
       mManagerList.add(user6)
       mManagerList.add(user7)
       mManagerList.add(user8)
   }

    /**
     * 根据输入框中的值来过滤数据并更新RecyclerView
     *
     * @param filterStr
     */
    fun filterData(filterStr: String) {
        var filterDateList = mutableListOf<Manager>()

        if (TextUtils.isEmpty(filterStr)) {
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
        mManagerList.clear()
        mManagerList.addAll(filterDateList)
    }

}
