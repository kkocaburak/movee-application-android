package com.adesso.moveeapplication.data.model.tmdb


import com.adesso.moveeapplication.data.base.ModelConstants
import com.google.gson.annotations.SerializedName

data class KnownFor(
    @SerializedName(ModelConstants.ADULT_STRING)
    val adult: Boolean,
    @SerializedName(ModelConstants.BACKDROP_PATH_STRING)
    val backdropPath: String,
    @SerializedName(ModelConstants.FIRST_AIR_DATE_STRING)
    val firstAirDate: String,
    @SerializedName(ModelConstants.GENRE_IDS_STRING)
    val genreIds: List<Int>,
    @SerializedName(ModelConstants.ID_STRING)
    val id: Int,
    @SerializedName(ModelConstants.MEDIA_TYPE_STRING)
    val mediaType: String,
    @SerializedName(ModelConstants.NAME_STRING)
    val name: String,
    @SerializedName(ModelConstants.ORIGIN_COUNTRY_STRING)
    val originCountry: List<String>,
    @SerializedName(ModelConstants.ORIGINAL_LANGUAGE_STRING)
    val originalLanguage: String,
    @SerializedName(ModelConstants.ORIGINAL_NAME_STRING)
    val originalName: String,
    @SerializedName(ModelConstants.ORIGINAL_TITLE_STRING)
    val originalTitle: String,
    @SerializedName(ModelConstants.OVERVIEW_STRING)
    val overview: String,
    @SerializedName(ModelConstants.POPULARITY_STRING)
    val popularity: Double,
    @SerializedName(ModelConstants.POSTER_PATH_STRING)
    val posterPath: String,
    @SerializedName(ModelConstants.RELEASE_DATE_STRING)
    val releaseDate: String,
    @SerializedName(ModelConstants.TITLE_STRING)
    val title: String,
    @SerializedName(ModelConstants.VIDEO_STRING)
    val video: Boolean,
    @SerializedName(ModelConstants.VOTE_AVERAGE_STRING)
    val voteAverage: Double,
    @SerializedName(ModelConstants.VOTE_COUNT_STRING)
    val voteCount: Int
)