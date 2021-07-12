package com.adesso.moveeapplication.data.components.network.repository.maps.model


import com.adesso.moveeapplication.data.base.ModelConstants
import com.adesso.moveeapplication.data.components.network.repository.maps.base.ModelMapsConstants
import com.adesso.moveeapplication.data.model.maps.NearbySearchResult
import com.google.gson.annotations.SerializedName

data class NearbySearchResponse(
    @SerializedName(ModelMapsConstants.HTML_ATTRIBUTIONS_STRING)
    val htmlAttributions: List<Any>,
    @SerializedName(ModelMapsConstants.NEXT_PAGE_TOKEN_STRING)
    val nextPageToken: String,
    @SerializedName(ModelConstants.RESULTS_STRING)
    val results: List<NearbySearchResult>,
    @SerializedName(ModelConstants.STATUS_STRING)
    val status: String
)