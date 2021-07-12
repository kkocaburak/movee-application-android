package com.adesso.moveeapplication.data.model.maps


import com.adesso.moveeapplication.data.components.network.repository.maps.base.ModelMapsConstants
import com.google.gson.annotations.SerializedName

data class AddressComponent(
    @SerializedName(ModelMapsConstants.LONG_NAME_STRING)
    val longName: String,
    @SerializedName(ModelMapsConstants.SHORT_NAME_STRING)
    val shortName: String,
    @SerializedName(ModelMapsConstants.TYPES_STRING)
    val types: List<String>
)