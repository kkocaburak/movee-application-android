package com.adesso.moveeapplication.data.components.network.repository.tmdb.model


import com.adesso.moveeapplication.data.base.ModelConstants
import com.google.gson.annotations.SerializedName

data class PostResponse(
    @SerializedName(ModelConstants.STATUS_CODE_STRING)
    val statusCode: Int,
    @SerializedName(ModelConstants.STATUS_MESSAGE_STRING)
    val statusMessage: String,
    @SerializedName(ModelConstants.SUCCESS_STRING)
    val success: Boolean
)