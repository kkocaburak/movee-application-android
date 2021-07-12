package com.adesso.moveeapplication.data.components.network.repository.tmdb.model


import com.adesso.moveeapplication.data.base.ModelConstants
import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    @SerializedName(ModelConstants.ID_STRING)
    val id: Int,
    @SerializedName(ModelConstants.OVERVIEW_STRING)
    val overview: String,
    @SerializedName(ModelConstants.POPULARITY_STRING)
    val popularity: Double,
    @SerializedName(ModelConstants.POSTER_PATH_STRING)
    val posterPath: String,
    @SerializedName(ModelConstants.RELEASE_DATE_STRING)
    val releaseDate: String,
    @SerializedName(ModelConstants.RUN_TIME_STRING)
    val runtime: Int,
    @SerializedName(ModelConstants.TITLE_STRING)
    val title: String,
    @SerializedName(ModelConstants.VIDEO_STRING)
    val video: Boolean,
    @SerializedName(ModelConstants.VOTE_AVERAGE_STRING)
    val voteAverage: Double
)