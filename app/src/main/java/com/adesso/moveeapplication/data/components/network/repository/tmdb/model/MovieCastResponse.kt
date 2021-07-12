package com.adesso.moveeapplication.data.components.network.repository.tmdb.model


import com.adesso.moveeapplication.data.base.ModelConstants
import com.adesso.moveeapplication.data.model.tmdb.Cast
import com.adesso.moveeapplication.data.model.tmdb.MovieCrew
import com.google.gson.annotations.SerializedName

data class MovieCastResponse(
    @SerializedName(ModelConstants.CAST_STRING)
    val cast: List<Cast>,
    @SerializedName(ModelConstants.CREW_STRING)
    val crew: List<MovieCrew>,
    @SerializedName(ModelConstants.ID_STRING)
    val id: Int
)