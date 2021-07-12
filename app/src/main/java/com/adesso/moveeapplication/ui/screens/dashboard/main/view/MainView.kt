package com.adesso.moveeapplication.ui.screens.dashboard.main.view

import android.content.DialogInterface
import android.os.Bundle
import android.os.Process
import androidx.core.content.ContextCompat
import com.adesso.moveeapplication.R
import com.adesso.moveeapplication.application.MoveeApplication
import com.adesso.moveeapplication.ui.base.BaseView
import com.adesso.moveeapplication.ui.base.TabLayoutType
import com.adesso.moveeapplication.ui.util.AlertDialogUtil
import com.google.android.material.tabs.TabLayout

/**
 * Created by Burak Karakoca on 23.09.2020.
 */
class MainView : BaseView() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpUI()
        initializeBase()
    }

    private fun setUpUI() {
        tabLayout = findViewById(R.id.activity_main_tab_layout)
        tabLayoutClickListener()
    }

    private fun tabLayoutClickListener() {
        tabLayout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position)
                {
                    TabLayoutType.MOVIE_TAB.value ->
                        tabClicked(tab, true, R.drawable.ic_tabbar_movie_selected)
                    TabLayoutType.TV_TAB.value ->
                        tabClicked(tab, true, R.drawable.ic_tabbar_serie_selected)
                    TabLayoutType.SEARCH_TAB.value ->
                        tabClicked(tab, true, R.drawable.ic_tabbar_search_selected)
                    TabLayoutType.PROFILE_TAB.value ->
                        tabClicked(tab, true, R.drawable.ic_tabbar_profil_selected)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                when (tab.position)
                {
                    TabLayoutType.MOVIE_TAB.value ->
                        tabClicked(tab, false, R.drawable.ic_tabbar_movie)
                    TabLayoutType.TV_TAB.value ->
                        tabClicked(tab, false, R.drawable.ic_tabbar_serie)
                    TabLayoutType.SEARCH_TAB.value ->
                        tabClicked(tab, false, R.drawable.ic_tabbar_search)
                    TabLayoutType.PROFILE_TAB.value ->
                        tabClicked(tab, false, R.drawable.ic_tabbar_profil)
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

    private fun tabClicked(tab: TabLayout.Tab, isSelected: Boolean, drawable: Int) {
        if (isSelected) {
            tab.icon = ContextCompat.getDrawable(MoveeApplication.appContext, drawable)

        } else {
            tab.icon = ContextCompat.getDrawable(MoveeApplication.appContext, drawable)
        }
    }

    override fun onBackPressed() {
        if (isInMainFragment) {
            val exitDialog =  AlertDialogUtil.showAlertWithButtons(
                getString(R.string.quit_app_string),
                this
            )
            exitDialog.setPositiveButton(this.getString(R.string.yes_string))
            { _ : DialogInterface, _: Int ->
                finish()
            }
            exitDialog.setNegativeButton(this.getString(R.string.no_string))
            { dialog : DialogInterface, _: Int ->
                dialog.dismiss()
            }
            exitDialog.show()
        } else {
            super.onBackPressed()
        }
    }
}