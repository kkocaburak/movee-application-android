package com.adesso.moveeapplication.data.model.maps


import com.adesso.moveeapplication.data.components.network.repository.maps.base.ModelMapsConstants
import com.google.gson.annotations.SerializedName

data class Geometry(
    @SerializedName(ModelMapsConstants.LOCATION_STRING)
    val location: Location,
    @SerializedName(ModelMapsConstants.VIEWPORT_STRING)
    val viewport: Viewport
)