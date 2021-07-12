package com.adesso.moveeapplication.ui.base

import com.adesso.moveeapplication.application.MoveeApplication

object BaseUIConstants {
    const val SPLASH_DELAY_TIME: Long = 3000
    const val PAGINATION_RATE: Double = 0.6

    const val MOVIE_TYPE_STRING: String = "Movie"
    const val TV_SERIES_TYPE_STRING: String = "Tv Series"

    const val MOVIE_TYPE: Int = 0
    const val TV_SERIES_TYPE: Int = 1
    const val NONE_TYPE: Int = -1

    const val MAX_TEXT_LINE: Int = 100
    const val MIN_TEXT_LINE: Int = 5

    const val SEARCH_DELAY: Long = 500

    const val MAP_RADIUS: Long = 5000
    const val MAP_ZOOM: Float = 13f
    const val MAP_BEARING: Float = 90f
    const val MAP_TILT: Float = 30f
    const val MAP_POPUP_X_OFFSET: Int = 0
    const val MAP_POPUP_Y_OFFSET: Int = 400
    const val MAP_SEARCH_TEXT: String = "movie_theater"
}