package com.adesso.moveeapplication.data.components.network.repository.maps.model


import com.adesso.moveeapplication.data.base.ModelConstants
import com.adesso.moveeapplication.data.components.network.repository.maps.base.ModelMapsConstants
import com.adesso.moveeapplication.data.model.maps.PlaceDetailResult
import com.google.gson.annotations.SerializedName

data class PlaceDetailResponse(
    @SerializedName(ModelMapsConstants.HTML_ATTRIBUTIONS_STRING)
    val htmlAttributions: List<Any>,
    @SerializedName(ModelMapsConstants.RESULT_STRING)
    val result: PlaceDetailResult,
    @SerializedName(ModelConstants.STATUS_STRING)
    val status: String
)