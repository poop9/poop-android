package com.poop9.poop

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.poop9.poop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = bind<ActivityMainBinding>(R.layout.activity_main)

        binding.bottomNavigation.setOnNavigationItemSelectedListener(this::handleMenuItemSelected)
        binding.bottomNavigation.setOnNavigationItemReselectedListener {} // For prevent recreating fragments
    }

    private fun handleMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.main_map -> showMapFragment()
            R.id.main_report -> showReportFragment()
        }

        return true
    }

    private fun showMapFragment() {
        replace(R.id.fragment_container, DummyFragment())
    }

    private fun showReportFragment() {
        replace(R.id.fragment_container, DummyFragment2())
    }
}
