package com.adesso.moveeapplication.data.components.network.repository.tmdb.model


import com.adesso.moveeapplication.data.base.ModelConstants
import com.adesso.moveeapplication.data.model.tmdb.Movie
import com.google.gson.annotations.SerializedName

data class CastMoviesResponse(
    @SerializedName(ModelConstants.CAST_STRING)
    val castMovies: List<Movie>,
    @SerializedName(ModelConstants.ID_STRING)
    val id: Int
)