package com.adesso.moveeapplication.data.model.tmdb

import com.adesso.moveeapplication.data.base.ModelConstants
import com.google.gson.annotations.SerializedName

/**
 * Created by Burak Karakoca on 23.10.2020.
 */
data class RequestToken(
    @SerializedName(ModelConstants.REQUEST_TOKEN_STRING)
    val requestToken: String
)