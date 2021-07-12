package com.adesso.moveeapplication.data.model.maps


import com.adesso.moveeapplication.data.components.network.repository.maps.base.ModelMapsConstants
import com.google.gson.annotations.SerializedName

data class PlusCode(
    @SerializedName(ModelMapsConstants.COMPOUND_CODE_STRING)
    val compoundCode: String,
    @SerializedName(ModelMapsConstants.GLOBAL_CODE_STRING)
    val globalCode: String
)