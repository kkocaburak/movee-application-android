package com.adesso.moveeapplication.ui.util

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.adesso.moveeapplication.application.MoveeApplication
import com.adesso.moveeapplication.application.MoveeConstants

/**
 * Created by Burak Karakoca on 22.10.2020.
 */
object KeyboardUtil {
    private val context = MoveeApplication.appContext

    fun showKeyboard() {
        val inputMethodManager =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
        inputMethodManager!!.toggleSoftInput(InputMethodManager.SHOW_FORCED, MoveeConstants.INT_ZERO)
    }
    fun hideKeyboard(view: View) {
        val inputMethodManager =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, MoveeConstants.INT_ZERO)
    }
}