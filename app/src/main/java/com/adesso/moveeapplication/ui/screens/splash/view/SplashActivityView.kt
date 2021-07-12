package com.adesso.moveeapplication.ui.screens.splash.view

import android.os.Bundle
import android.os.Handler
import com.adesso.moveeapplication.R
import com.adesso.moveeapplication.ui.base.BaseUIConstants
import com.adesso.moveeapplication.ui.base.BaseView
import com.adesso.moveeapplication.ui.screens.dashboard.main.view.MainView
import com.adesso.moveeapplication.ui.util.IntentUtil
import java.util.*

class SplashActivityView : BaseView() {

    private val updateHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        updateLanguage()
        updateHandler.postDelayed({toMainScreen()}, BaseUIConstants.SPLASH_DELAY_TIME)
    }

    private fun updateLanguage() {
        val phoneLanguage = Locale.getDefault().language
        Locale.setDefault(Locale(phoneLanguage))
    }

    private fun toMainScreen(){
        IntentUtil.startNewActivityAndFinish(this, MainView::class.java)
    }

}