package com.adesso.moveeapplication.ui.base

import androidx.fragment.app.Fragment

/**
 * Created by Burak Karakoca on 5.10.2020.
 */

interface INavigator {
    fun navigateTo(fragment: Fragment)
    fun navigateAndClearBackStack(fragment: Fragment)
    fun navigateBack()
    fun setBackButtonEnabled(isEnabled: Boolean)
}