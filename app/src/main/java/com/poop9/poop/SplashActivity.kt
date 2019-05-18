package com.poop9.poop

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.poop9.poop.base.BaseActivity
import com.poop9.poop.onboarding.OnboardingActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashActivity : BaseActivity() {

    private val timeNum = 2 //2초
    private val timeSec = timeNum * 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        lifecycleScope.launch {
            delay(timeSec.toLong())
            gotoOnboarding()
        }
    }

    private fun gotoOnboarding() {
        val intent = Intent(this, OnboardingActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
        overridePendingTransition(R.anim.fade_start, R.anim.fade_end)
    }
}
