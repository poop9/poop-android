package com.poop9.poop.onboarding

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.poop9.poop.R
import kotlinx.android.synthetic.main.fragment_onboarding.*

class OnboardingFragment0 : OnboardingFragment(){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onboarding_image.setImageDrawable(ContextCompat.getDrawable(context!!,
            R.drawable.onboarding_1_illust
        ))
        onboarding_title.text = getString(R.string.onboarding_title0)
        onboarding_desc.text = getString(R.string.onboarding_desc0)
        onboarding_start.visibility = View.INVISIBLE
    }
}