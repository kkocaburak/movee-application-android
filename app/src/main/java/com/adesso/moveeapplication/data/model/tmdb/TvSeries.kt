package com.adesso.moveeapplication.data.model.tmdb

import com.adesso.moveeapplication.data.base.ModelConstants
import com.adesso.moveeapplication.data.model.tmdb.ITvSeries
import java.io.Serializable
import com.google.gson.annotations.SerializedName

data class TvSeries(
    @SerializedName(ModelConstants.BACKDROP_PATH_STRING)
    val backdropPath: String,
    @SerializedName(ModelConstants.FIRST_AIR_DATE_STRING)
    override val firstAirDate: String,
    @SerializedName(ModelConstants.GENRE_IDS_STRING)
    override val genreIds: List<Int>,
    @SerializedName(ModelConstants.ID_STRING)
    override val id: Int,
    @SerializedName(ModelConstants.NAME_STRING)
    override val name: String,
    @SerializedName(ModelConstants.ORIGIN_COUNTRY_STRING)
    val originCountry: List<String>,
    @SerializedName(ModelConstants.ORIGINAL_LANGUAGE_STRING)
    val originalLanguage: String,
    @SerializedName(ModelConstants.ORIGINAL_NAME_STRING)
    val originalName: String,
    @SerializedName(ModelConstants.OVERVIEW_STRING)
    override val overview: String,
    @SerializedName(ModelConstants.POPULARITY_STRING)
    val popularity: Double,
    @SerializedName(ModelConstants.POSTER_PATH_STRING)
    override val posterPath: String,
    @SerializedName(ModelConstants.VOTE_AVERAGE_STRING)
    override val voteAverage: Double,
    @SerializedName(ModelConstants.VOTE_COUNT_STRING)
    val voteCount: Int,
    @SerializedName(ModelConstants.RATING_STRING)
    var rating: Int
) : Serializable, ITvSeries