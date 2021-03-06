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
package com.owner.provideslib.router

/**
 *  ARouter路径常量
 * Created by Liuyong on 2018-10-12.It's AMSystem
 *@description:
 */
object RouterPath {
    class App {
       companion object {
           const val PATH_MAIN = "/app/main"
       }
    }

    class UserCenter {
        companion object {
            const val PATH_USER_INFO="/usercenter/userinfo"
            const val PATH_USER_LOGIN="/usercenter/login"
            const val PATH_USER_REGISTER="/usercenter/register"
        }
    }

    class Asset {
        companion object {
            const val PATH_ASSETSPARAM_MAIN = "/assetsparam/main"
            const val PATH_ASSETSPARAM_FOUR = "/assetsparam/four"
            const val PATH_ASSET_INFO = "/assetinfo/info"
        }
    }
    class Todo{
        companion object {
            const val PATH_TODO_TASK = "/todo/taskActivity"
        }
    }

}