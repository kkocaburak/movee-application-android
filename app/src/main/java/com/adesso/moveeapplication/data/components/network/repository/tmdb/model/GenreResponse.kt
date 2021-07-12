package com.adesso.moveeapplication.data.components.network.repository.tmdb.model

import com.adesso.moveeapplication.data.base.ModelConstants
import com.adesso.moveeapplication.data.model.tmdb.Genre
import com.google.gson.annotations.SerializedName
/**
 * Created by Burak Karakoca on 23.09.2020.
 */
data class GenreResponse(
    @SerializedName(ModelConstants.GENRES_STRING)
    val genres: List<Genre>
)