package com.example.mvvmtamplate.activity

import android.os.Bundle
import com.bt.apptemplate.baseui.FragmentHostActivity
import com.example.mvvmtamplate.R

/**
 * Author: kevin
 * Date: 2023/3/1
 * Description:
 */
class MainActivity : FragmentHostActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun getNavGraphResId(): Int = R.navigation.main_graph
}