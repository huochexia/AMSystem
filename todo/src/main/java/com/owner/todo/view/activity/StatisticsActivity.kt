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
package com.owner.todo.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.owner.baselibrary.ext.addFragment
import com.owner.baselibrary.ext.setupToolBar
import com.owner.todo.R
import com.owner.todo.ext.obtainViewModel
import com.owner.todo.view.fragment.StatisticsFragment
import com.owner.todo.viewmodel.StatisticsViewModel
import kotlinx.android.synthetic.main.activity_task.*
import org.jetbrains.anko.startActivity

/**
 *
 * Created by Liuyong on 2018-12-16.It's AMSystem
 *@description:
 */
class StatisticsActivity : AppCompatActivity() {

     private lateinit var  drawerlayout:DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_statistics)

        setupToolBar(R.id.mToolbar){
            title = "Statistics"
            setHomeAsUpIndicator(R.drawable.ic_menu)
            setDisplayHomeAsUpEnabled(true)
        }

        setupNavigationDrawer()

        findOrCreateViewFragement()
    }

    private fun findOrCreateViewFragement() {
        supportFragmentManager.findFragmentById(R.id.mContentFrame)?:
                addFragment(StatisticsFragment.newInstance(),R.id.mContentFrame)
    }

    private fun setupNavigationDrawer() {
        drawerlayout = mDrawerLayout.apply {
            setStatusBarBackground(R.color.colorPrimaryDark)
        }
        setupDrawerContent(findViewById<NavigationView>(R.id.mNavView))
    }

    private fun setupDrawerContent(navigationView: NavigationView) {
        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.mListNavMenuItem -> {
                    startActivity<TasksActivity>()
                }
                R.id.mStatisticsNavMenuItem ->{

                }
                R.id.mFinishMenuItem -> finish()
            }
            it.isChecked = true
            drawerlayout.closeDrawers()
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                android.R.id.home ->{
                    drawerlayout.openDrawer(GravityCompat.START)
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }

    fun obtainViewModel()=obtainViewModel(StatisticsViewModel::class.java)
}