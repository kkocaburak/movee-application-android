package com.adesso.moveeapplication.data.model.maps


import com.adesso.moveeapplication.data.components.network.repository.maps.base.ModelMapsConstants
import com.google.gson.annotations.SerializedName

data class Southwest(
    @SerializedName(ModelMapsConstants.LAT_STRING)
    val lat: Double,
    @SerializedName(ModelMapsConstants.LNG_STRING)
    val lng: Double
)