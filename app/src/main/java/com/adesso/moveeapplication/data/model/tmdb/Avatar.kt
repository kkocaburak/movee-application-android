package com.adesso.moveeapplication.data.model.tmdb


import com.adesso.moveeapplication.data.base.ModelConstants
import com.google.gson.annotations.SerializedName

data class Avatar(
    @SerializedName(ModelConstants.GRAVATAR_STRING)
    val gravatar: Gravatar
)