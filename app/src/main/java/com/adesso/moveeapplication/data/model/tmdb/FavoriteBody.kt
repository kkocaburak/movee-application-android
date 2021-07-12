package com.adesso.moveeapplication.data.model.tmdb

/**
 * Created by Burak Karakoca on 3.11.2020.
 */
data class FavoriteBody(
    val media_type: String,
    val media_id: Int,
    val favorite: Boolean
)