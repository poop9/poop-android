package com.poop9.poop

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.view.WindowManager

interface Dimm {

    fun dim(window: Window?, amount: Float = 0.5f) {
        if (window == null)
            return

        window.requestFeature(Window.FEATURE_NO_TITLE)
        val params = window.attributes
        params.dimAmount = amount
        params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
        window.attributes = params
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}