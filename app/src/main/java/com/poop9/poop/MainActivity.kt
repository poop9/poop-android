package com.poop9.poop

import android.os.Bundle
import android.view.MenuItem
import com.poop9.poop.base.BaseActivity
import com.poop9.poop.databinding.ActivityMainBinding
import com.poop9.poop.map.MapFragment
import com.poop9.poop.report.ReportFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = bind<ActivityMainBinding>(R.layout.activity_main)

        binding.bottomNavigation.setOnNavigationItemSelectedListener(this::handleMenuItemSelected)
        binding.bottomNavigation.setOnNavigationItemReselectedListener {} // For prevent recreating fragments

        showMapFragment()
        showLoginDialog()
    }

    private fun handleMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.main_map -> showMapFragment()
            R.id.main_report -> showReportFragment()
            else -> return false
        }

        return true
    }

    private fun showLoginDialog(){
        val dialog = LoginDialog()
        showDialogFragment(dialog, "Login")
    }

    private fun showMapFragment() {
        replace(R.id.fragment_container, MapFragment())
    }

    private fun showReportFragment() {
        replace(R.id.fragment_container, ReportFragment())
    }
}
