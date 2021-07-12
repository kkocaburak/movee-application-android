package com.adesso.moveeapplication.data.model.maps


import com.adesso.moveeapplication.data.components.network.repository.maps.base.ModelMapsConstants
import com.google.gson.annotations.SerializedName

data class OpeningHours(
    @SerializedName(ModelMapsConstants.OPEN_NOW_STRING)
    val openNow: Boolean
)