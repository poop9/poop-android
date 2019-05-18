package com.poop9.poop

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.poop9.poop.base.BaseActivity
import com.poop9.poop.data.api.PoopRepository
import com.poop9.poop.onboarding.OnboardingActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


class SplashActivity : BaseActivity() {

    private val repo: PoopRepository by inject()

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
        startActivity<OnboardingActivity> {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }

        finish()
        overridePendingTransition(R.anim.fade_start, R.anim.fade_end)
    }
}
