package com.adesso.moveeapplication.ui.util

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.adesso.moveeapplication.BuildConfig
import com.adesso.moveeapplication.R
import com.adesso.moveeapplication.application.MoveeApplication

object IntentUtil {

    fun startNewActivityAndFinish(currentActivity: AppCompatActivity, clazz: Class<*>) {
        val intent = Intent(MoveeApplication.appContext, clazz)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        currentActivity.startActivity(intent)
        currentActivity.finish()
    }

    fun toWebsite(url: String) {
        val bundle = Bundle()
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        ContextCompat.startActivity(MoveeApplication.appContext, intent, bundle)
    }

    fun shareUrl(url: String) {
        val bundle = Bundle()
        val mIntent = Intent(Intent.ACTION_SEND)
        mIntent.type = MoveeApplication.appContext.getString(R.string.text_plain_string)
        mIntent.putExtra(Intent.EXTRA_TEXT, (BuildConfig.BASE_MOVIE_URL + url))

        val intent = Intent.createChooser(mIntent, MoveeApplication.appContext.getString(R.string.share_media_string))
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        ContextCompat.startActivity(MoveeApplication.appContext, intent, bundle)
    }

}