package com.poop9.poop

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.poop9.poop.databinding.ActivityMainBinding
import com.poop9.poop.map.MapFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = bind<ActivityMainBinding>(R.layout.activity_main)

        binding.bottomNavigation.let { nav ->
            nav.setOnNavigationItemSelectedListener(this::handleMenuItemSelected)
            nav.setOnNavigationItemReselectedListener {} // For prevent recreating fragments
        }

        showMapFragment()
    }

    private fun handleMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.main_map -> showMapFragment()
            R.id.main_report -> showReportFragment()
            else -> return false
        }

        return true
    }

    private fun showMapFragment() {
        switchFragment(MapFragment(), "map")
    }

    private fun showReportFragment() {
        switchFragment(ReportFragment(), "report")
    }

    // Prevent re-creating fragments
    private fun switchFragment(fragment: Fragment, tag: String) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        val curFrag = supportFragmentManager.primaryNavigationFragment
        if (curFrag != null) fragmentTransaction.hide(curFrag)

        var fr = supportFragmentManager.findFragmentByTag(tag)
        if (fr != null) {
            fragmentTransaction.show(fr)
        } else {
            fr = fragment
            fragmentTransaction.add(R.id.fragment_container, fragment, tag)
        }

        fragmentTransaction.setPrimaryNavigationFragment(fr)
        fragmentTransaction.setReorderingAllowed(true)
        fragmentTransaction.commit()
    }
}
