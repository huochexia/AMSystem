package com.owner.amsystem.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.owner.amsystem.R
import com.owner.amsystem.ui.fragment.HomeFragment
import com.owner.baselibrary.ext.addFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val homeFragment by lazy { HomeFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment(homeFragment,R.id.mContainer)
        mBottomNavBar.checkAssertBadge(0)
        mBottomNavBar.checkMsgBadge(false)
    }
}
