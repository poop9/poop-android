package com.poop9.poop

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.poop9.poop.base.BaseActivity
import com.poop9.poop.onboarding.OnboardingActivity


class SplashActivity : BaseActivity() {

    private val timeNum = 2 //2초
    private val timeSec = timeNum * 1000
    private var clicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        startCountdown()
    }

    private fun startCountdown() {
        val handler = Handler()
        handler.postDelayed(splashHandler(), timeSec.toLong())
    }

    private inner class splashHandler : Runnable {
        override fun run() {
            gotoOnboarding()
        }
    }
    private fun gotoOnboarding(){
        val intent = Intent(this, OnboardingActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
        overridePendingTransition(R.anim.fade_start, R.anim.fade_end)
    }
}
