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
package com.owner.todo.data.source.remote.entites

import com.owner.todo.data.Task

/**
 *
 * Created by Liuyong on 2018-12-09.It's AMSystem
 *@description:
 */
data class TaskDelete(val method:String ="DELETE",
                       val taskId:String){
    val path = "/1.1/classes/Task/$taskId"
}

data class BatchDeleteRequest(val requests:List<TaskDelete>)


data class TaskList(val results:List<Task>)

