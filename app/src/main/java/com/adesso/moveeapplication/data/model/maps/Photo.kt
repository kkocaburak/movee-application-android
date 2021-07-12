package com.adesso.moveeapplication.data.model.maps


import com.adesso.moveeapplication.data.components.network.repository.maps.base.ModelMapsConstants
import com.google.gson.annotations.SerializedName

data class Photo(
    @SerializedName(ModelMapsConstants.HEIGHT_STRING)
    val height: Int,
    @SerializedName(ModelMapsConstants.HTML_ATTRIBUTIONS_STRING)
    val htmlAttributions: List<String>,
    @SerializedName(ModelMapsConstants.PHOTO_REFERENCE_STRING)
    val photoReference: String,
    @SerializedName(ModelMapsConstants.WIDTH_STRING)
    val width: Int
)