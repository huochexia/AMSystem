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
package com.owner.assetsparam.model.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.owner.assetsparam.data.CategoryInfo

/**
 *
 * Created by Liuyong on 2018-12-13.It's AMSystem
 *@description:
 */
@Database(entities = [CategoryInfo::class],version = 1)
abstract class AssetsDatabase :RoomDatabase(){

    abstract fun getCategoryDao():CategoryDao
    companion object {
        private val INSTANCE :AssetsDatabase? =null

        private val lock =Any()

        fun getInstance(context:Context) = INSTANCE ?: synchronized(lock){
            INSTANCE ?: Room.databaseBuilder(context.applicationContext,
                    AssetsDatabase::class.java,"AssetsDatabase.db").build()
        }
    }
}