package com.ingrammicro.imdelivery

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.multidex.MultiDex

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    private fun getCurrentFragment(): Fragment? {
        return supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment)?.childFragmentManager?.primaryNavigationFragment

    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}
