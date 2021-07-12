package com.adesso.moveeapplication.ui.base

import android.app.AlertDialog
import android.opengl.Visibility
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.adesso.moveeapplication.R
import com.adesso.moveeapplication.application.MoveeApplication
import com.google.android.material.tabs.TabLayout

/**
 * Created by Burak Karakoca on 28.09.2020.
 */
abstract class BaseView: AppCompatActivity() {
    private lateinit var baseViewModel : BaseViewModel
    private lateinit var loadingFrameLayout : FrameLayout

    lateinit var tabLayout : TabLayout

    var isInMainFragment: Boolean = true

    fun initializeBase() {
        baseViewModel = ViewModelProvider(this, ViewModelFactory())
            .get(BaseViewModel::class.java)

        loadingFrameLayout = findViewById(R.id.activity_main_layout_loading)

        observeIsLoading()
    }

    private fun observeIsLoading() {
        baseViewModel.isLoading.observe(this, Observer {
            it.let { isLoading ->
                if (isLoading) {
                    loadingFrameLayout.visibility = View.VISIBLE
                } else {
                    loadingFrameLayout.visibility = View.INVISIBLE
                }
            }
        })
    }

    fun startLoading() {
        baseViewModel.isLoading.value = true
    }

    fun stopLoading() {
        baseViewModel.isLoading.value = false
    }

    fun showTabBar() {
        tabLayout.visibility = View.VISIBLE
    }

    fun hideTabBar() {
        tabLayout.visibility = View.GONE
    }

}