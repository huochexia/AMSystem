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
package com.owner.todo.data.source.local

import android.arch.persistence.room.*
import com.owner.todo.data.Task
import io.reactivex.Flowable
import io.reactivex.Single

/**
 *
 * Created by Liuyong on 2018-12-08.It's AMSystem
 *@description:
 */
@Dao
interface TaskDao {

    @Query("SELECT * FROM Tasks")
    fun getTasks(): Flowable<List<Task>>

    @Query("SELECT * FROM Tasks WHERE objectId = :taskId")
    fun getTaskId(taskId: String): Single<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(task: Task)

    @Update
    fun updateTask(task: Task)

    @Query("UPDATE tasks SET completed = :completed WHERE objectId = :taskId")
    fun updateCompleted(taskId: String, completed: Boolean)

    @Query("DELETE FROM Tasks WHERE objectId = :taskId")
    fun deleteTaskById(taskId: String)

    @Query("DELETE FROM Tasks")
    fun deleteTasks()

    @Query("DELETE FROM Tasks WHERE completed = 1")
    fun deleteCompletedTasks(): Int

}