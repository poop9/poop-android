package com.poop9.poop.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.poop9.poop.R

class OnboardingPagerAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {

    private val layoutIds =
        intArrayOf(
            R.layout.fragment_onboarding,
            R.layout.fragment_onboarding,
            R.layout.fragment_onboarding
        )
    // 2
    override fun getItem(position: Int): Fragment = when(position){
        0 -> OnboardingFragment0()
        1 -> OnboardingFragment1()
        2 -> OnboardingFragment2()
        else -> throw IllegalArgumentException()
    }

    // 3
    override fun getCount(): Int {
        return layoutIds.size
    }
}