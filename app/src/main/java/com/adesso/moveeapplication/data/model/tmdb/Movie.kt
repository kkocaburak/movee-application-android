package com.adesso.moveeapplication.data.model.tmdb

import com.adesso.moveeapplication.data.base.ModelConstants
import com.adesso.moveeapplication.data.model.tmdb.IMovie
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Burak Karakoca on 23.09.2020.
 */
data class Movie(
    @SerializedName(ModelConstants.ADULT_STRING)
    override val adult: Boolean,
    @SerializedName(ModelConstants.BACKDROP_PATH_STRING)
    override val backdropPath: String,
    @SerializedName(ModelConstants.GENRE_IDS_STRING)
    override val genreIds: List<Int>,
    @SerializedName(ModelConstants.ID_STRING)
    override val id: Int,
    @SerializedName(ModelConstants.ORIGINAL_LANGUAGE_STRING)
    override val originalLanguage: String,
    @SerializedName(ModelConstants.ORIGINAL_TITLE_STRING)
    override val originalTitle: String,
    @SerializedName(ModelConstants.OVERVIEW_STRING)
    override val overview: String?,
    @SerializedName(ModelConstants.POPULARITY_STRING)
    override val popularity: Double?,
    @SerializedName(ModelConstants.POSTER_PATH_STRING)
    override val posterPath: String?,
    @SerializedName(ModelConstants.RELEASE_DATE_STRING)
    override val releaseDate: String,
    @SerializedName(ModelConstants.TITLE_STRING)
    override val title: String,
    @SerializedName(ModelConstants.VIDEO_STRING)
    override val video: Boolean,
    @SerializedName(ModelConstants.VOTE_AVERAGE_STRING)
    override val voteAverage: Double,
    @SerializedName(ModelConstants.VOTE_COUNT_STRING)
    override val voteCount: Int,
    @SerializedName(ModelConstants.RATING_STRING)
    var rating: Int
) : Serializable, IMovie