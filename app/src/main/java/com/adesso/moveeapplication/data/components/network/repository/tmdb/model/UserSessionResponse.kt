package com.adesso.moveeapplication.data.components.network.repository.tmdb.model

import com.adesso.moveeapplication.data.base.ModelConstants
import com.google.gson.annotations.SerializedName

/**
 * Created by Burak Karakoca on 23.10.2020.
 */
data class UserSessionResponse(
    @SerializedName(ModelConstants.EXPIRES_AT_STRING)
    val expiresAt: String,
    @SerializedName(ModelConstants.SESSION_ID_STRING)
    val sessionId: String,
    @SerializedName(ModelConstants.SUCCESS_STRING)
    val success: Boolean,
    @SerializedName(ModelConstants.STATUS_CODE_STRING)
    val statusCode: Int,
    @SerializedName(ModelConstants.STATUS_MESSAGE_STRING)
    val statusMessage: String
)