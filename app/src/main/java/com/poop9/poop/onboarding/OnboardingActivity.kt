package com.poop9.poop.onboarding

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.android.material.tabs.TabLayout
import com.poop9.poop.MainActivity
import com.poop9.poop.R
import com.poop9.poop.base.BaseActivity
import com.poop9.poop.startActivity
import kotlinx.android.synthetic.main.activity_onboarding.*
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions

class OnboardingActivity : BaseActivity(), EasyPermissions.PermissionCallbacks {

    companion object {
        const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    private lateinit var tabLayout: TabLayout

    private var showPermissionDeniedDialog: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        initViewPager()
        onboarding_skip.setOnClickListener { onboardingSkip() }
    }

    private fun initViewPager() {
        OnboardingPagerAdapter(supportFragmentManager).apply {
            attachOnStartListener {
                enableMyLocation()
            }
            onboarding_viewpager.adapter = this
        }

        tabLayout = findViewById(R.id.onboarding_tab_layout)
        tabLayout.setupWithViewPager(onboarding_viewpager)
    }

    private fun onboardingSkip() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
        overridePendingTransition(R.anim.fade_start, R.anim.fade_end)
    }

    @AfterPermissionGranted(LOCATION_PERMISSION_REQUEST_CODE)
    private fun enableMyLocation() {
        val perms = arrayOf(ACCESS_FINE_LOCATION)
        if (EasyPermissions.hasPermissions(this, *perms)) {
            moveToMain()
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(
                this, "권한을 추가해주세요.",
                LOCATION_PERMISSION_REQUEST_CODE, *perms
            )
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    /**
     * Display a dialog box asking the user to grant permissions if they were denied
     */
    override fun onResumeFragments() {
        super.onResumeFragments()
        if (showPermissionDeniedDialog) {
            AlertDialog.Builder(this).apply {
                setPositiveButton("네", null)
                setMessage("권한을 허용해주셔야 합니다...")
                create()
            }.show()
            showPermissionDeniedDialog = false
        }
    }

    override fun onPermissionsDenied(requestCode: Int, list: List<String>) {
        showPermissionDeniedDialog = true
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        moveToMain()
    }

    private fun moveToMain() {
        startActivity<MainActivity>()
        finish()
    }
}
