package com.adesso.moveeapplication.application

import android.app.Application
import android.content.Context

class MoveeApplication : Application() {
    override fun onCreate() {
        appContext = applicationContext
        super.onCreate()
    }

    companion object {
        lateinit var appContext: Context
    }
}