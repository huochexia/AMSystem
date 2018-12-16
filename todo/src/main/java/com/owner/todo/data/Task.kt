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
package com.owner.todo.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import java.util.*

/**
 * 这是一个不可修改的模型类，为了和Room一起编译，我们不能使用@JvmOverloads去生成多个构造方法
 * Created by Liuyong on 2018-12-08.It's AMSystem
 *@description:
 */
@Entity(tableName = "tasks")
data class Task @JvmOverloads constructor(
        var title: String = "",
        var description: String = "",
        var userId: String = "",
        @PrimaryKey var objectId: String =""
) {
    /**
     * 如果任务完成了返回真，否则返回假
     */
    @ColumnInfo(name = "completed")
    var isCompleted = false

    val titleForList: String
        get() = if (title.isNotEmpty()) title else description
    /**
     * 任务状态
     */
    val isActive
        get() = !isCompleted

    val isEmpty
        get() = title.isEmpty() && description.isEmpty()

}