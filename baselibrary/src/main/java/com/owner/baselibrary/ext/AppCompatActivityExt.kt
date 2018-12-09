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
package com.owner.baselibrary.ext

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar


/**
 * 用于管理Fragment的扩展函数
 * Created by Liuyong on 2018-09-01.
 *@description:
 */
fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}

fun AppCompatActivity.addFragment(fragment: Fragment, containerId: Int) {
    supportFragmentManager.inTransaction {
        add(containerId, fragment)
    }

}

fun AppCompatActivity.replaceFragment(fragment: Fragment, containerId: Int) {
    supportFragmentManager.inTransaction {
        replace(containerId, fragment)
    }
}

fun AppCompatActivity.removeFragment(fragment: Fragment) {
    supportFragmentManager.inTransaction {
        remove(fragment)
    }
}

fun AppCompatActivity.hideFragment(fragment: Fragment) {
    supportFragmentManager.inTransaction {
        hide(fragment)
    }
}

fun AppCompatActivity.showFragment(fragment: Fragment) {
    supportFragmentManager.inTransaction {
        show(fragment)
    }
}

fun AppCompatActivity.setupToolBar(@IdRes toolbarId: Int, action: ActionBar.() -> Unit) {
    setSupportActionBar(findViewById<Toolbar>(toolbarId))
    supportActionBar?.run {
        action()
    }
}

