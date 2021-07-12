package com.adesso.moveeapplication.data.components.network.repository.tmdb.model


import com.adesso.moveeapplication.data.base.ModelConstants
import com.adesso.moveeapplication.data.model.tmdb.TvSeriesCreator
import com.google.gson.annotations.SerializedName

data class TvSeriesDetailResponse(
    @SerializedName(ModelConstants.EPISODE_RUN_TIME_STRING)
    val episodeRunTime: List<Int>,
    @SerializedName(ModelConstants.CREATED_BY_STRING)
    val createdBy: List<TvSeriesCreator>,
    @SerializedName(ModelConstants.FIRST_AIR_DATE_STRING)
    val firstAirDate: String,
    @SerializedName(ModelConstants.ID_STRING)
    val id: Int,
    @SerializedName(ModelConstants.IN_PRODUCTION_STRING)
    val inProduction: Boolean,
    @SerializedName(ModelConstants.LAST_AIR_DATE_STRING)
    val lastAirDate: String,
    @SerializedName(ModelConstants.NAME_STRING)
    val name: String,
    @SerializedName(ModelConstants.NUMBER_OF_SEASONS_STRING)
    val numberOfSeasons: Int,
    @SerializedName(ModelConstants.OVERVIEW_STRING)
    val overview: String,
    @SerializedName(ModelConstants.POPULARITY_STRING)
    val popularity: Double,
    @SerializedName(ModelConstants.POSTER_PATH_STRING)
    val posterPath: String,
    @SerializedName(ModelConstants.STATUS_STRING)
    val status: String,
    @SerializedName(ModelConstants.VOTE_AVERAGE_STRING)
    val voteAverage: Double
)