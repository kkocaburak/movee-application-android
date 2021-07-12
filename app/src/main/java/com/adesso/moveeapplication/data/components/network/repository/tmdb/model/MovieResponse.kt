package com.adesso.moveeapplication.data.components.network.repository.tmdb.model

import com.adesso.moveeapplication.data.base.ModelConstants
import com.adesso.moveeapplication.data.model.tmdb.Movie
import com.google.gson.annotations.SerializedName
/**
 * Created by Burak Karakoca on 23.09.2020.
 */
data class MovieResponse (
    @SerializedName(ModelConstants.PAGE_STRING)
    val page: Int,
    @SerializedName(ModelConstants.RESULTS_STRING)
    val results: List<Movie>,
    @SerializedName(ModelConstants.TOTAL_PAGES_STRING)
    val totalPages: Int,
    @SerializedName(ModelConstants.TOTAL_RESULT_STRING)
    val totalResults: Int
)