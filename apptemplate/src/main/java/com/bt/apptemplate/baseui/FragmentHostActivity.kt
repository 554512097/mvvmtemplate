package com.bt.apptemplate.baseui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.bt.apptemplate.R

abstract class FragmentHostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_host)
        var navHostFragment = supportFragmentManager.findFragmentByTag("host")
        if (navHostFragment == null) {
            navHostFragment = NavHostFragment.create(getNavGraphResId(), intent.extras)
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host, navHostFragment, "host")
            .setPrimaryNavigationFragment(navHostFragment)
            .commit()
    }

    protected abstract fun getNavGraphResId(): Int
}