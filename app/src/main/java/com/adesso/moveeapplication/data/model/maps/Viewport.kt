package com.adesso.moveeapplication.data.model.maps


import com.adesso.moveeapplication.data.components.network.repository.maps.base.ModelMapsConstants
import com.google.gson.annotations.SerializedName

data class Viewport(
    @SerializedName(ModelMapsConstants.NORTHEAST_STRING)
    val northeast: Northeast,
    @SerializedName(ModelMapsConstants.SOUTHWEST_STRING)
    val southwest: Southwest
)