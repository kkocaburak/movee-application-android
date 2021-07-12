package com.adesso.moveeapplication.data.model.tmdb

import com.adesso.moveeapplication.data.base.ModelConstants
import com.google.gson.annotations.SerializedName

/**
 * Created by Burak Karakoca on 23.10.2020.
 */
data class LoginInfo(
    @SerializedName(ModelConstants.USERNAME_STRING)
    val username: String,
    @SerializedName(ModelConstants.PASSWORD_STRING)
    val password: String,
    @SerializedName(ModelConstants.REQUEST_TOKEN_STRING)
    val requestToken: String
)