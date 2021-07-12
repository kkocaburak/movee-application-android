package com.adesso.moveeapplication.data.components.network.repository.tmdb.model


import com.adesso.moveeapplication.data.base.ModelConstants
import com.google.gson.annotations.SerializedName

data class GuestSessionResponse(
    @SerializedName(ModelConstants.EXPIRES_AT_STRING)
    val expiresAt: String,
    @SerializedName(ModelConstants.GUEST_SESSION_ID_STRING)
    val guestSessionId: String,
    @SerializedName(ModelConstants.SUCCESS_STRING)
    val success: Boolean,
    @SerializedName(ModelConstants.STATUS_CODE_STRING)
    val statusCode: Int,
    @SerializedName(ModelConstants.STATUS_MESSAGE_STRING)
    val statusMessage: String
)