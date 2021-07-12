package com.adesso.moveeapplication.data.components.network.repository.tmdb.model


import com.adesso.moveeapplication.data.base.ModelConstants
import com.adesso.moveeapplication.data.model.tmdb.TvSeries
import com.google.gson.annotations.SerializedName

data class TvSeriesResponse(
    @SerializedName(ModelConstants.PAGE_STRING)
    val page: Int,
    @SerializedName(ModelConstants.RESULTS_STRING)
    val results: List<TvSeries>,
    @SerializedName(ModelConstants.TOTAL_PAGES_STRING)
    val totalPages: Int,
    @SerializedName(ModelConstants.TOTAL_RESULT_STRING)
    val totalResults: Int
)