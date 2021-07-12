package com.adesso.moveeapplication.data.components.network.repository.tmdb.model


import com.adesso.moveeapplication.data.base.ModelConstants
import com.adesso.moveeapplication.data.model.tmdb.SearchResponse
import com.google.gson.annotations.SerializedName

data class SearchTMDBResponse(
    @SerializedName(ModelConstants.PAGE_STRING)
    val page: Int,
    @SerializedName(ModelConstants.RESULTS_STRING)
    val results: List<SearchResponse>,
    @SerializedName(ModelConstants.TOTAL_PAGES_STRING)
    val totalPages: Int,
    @SerializedName(ModelConstants.TOTAL_RESULT_STRING)
    val totalResults: Int
)