package com.poop9.poop.onboarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.poop9.poop.R
import kotlinx.android.synthetic.main.activity_onboarding.*

class OnboardingActivity : AppCompatActivity(){

    lateinit var tabLayout : TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        initViewPager()
        onboarding_skip.setOnClickListener{onboardingSkip()}
    }

    private fun initViewPager(){
        val adapter = OnboardingPagerAdapter(supportFragmentManager)
        onboarding_viewpager.adapter = adapter
        tabLayout = findViewById(R.id.onboarding_tab_layout)
        tabLayout.setupWithViewPager(onboarding_viewpager)
    }

    private fun onboardingSkip(){
        finish()
    }
}
