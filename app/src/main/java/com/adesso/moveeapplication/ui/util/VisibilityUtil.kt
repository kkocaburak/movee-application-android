package com.adesso.moveeapplication.ui.util

import android.view.View

/**
 * Created by Burak Karakoca on 5.11.2020.
 */
object VisibilityUtil {
    fun setVisibilityForValue(unit: View, visibilityValue: Boolean) {
        unit.visibility = if (visibilityValue) View.VISIBLE else View.INVISIBLE
    }
}