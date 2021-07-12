package com.adesso.moveeapplication.ui.util

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import com.adesso.moveeapplication.BuildConfig
import com.adesso.moveeapplication.R
import com.adesso.moveeapplication.application.MoveeApplication
import com.bumptech.glide.Glide

/**
 * Created by Burak Karakoca on 12.10.2020.
 */
object GlideUtil {
    fun setImage(imageUrl: String, image: ImageView) {
        Glide.with(MoveeApplication.appContext)
            .load(
                StringCompilerUtil.compileString(BuildConfig.BASE_MOVIE_IMG_URL, imageUrl)
            )
            .placeholder(ColorDrawable(Color.WHITE))
            .centerCrop()
            .into(image)
    }
}