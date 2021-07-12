package com.adesso.moveeapplication.data.model.tmdb


import com.adesso.moveeapplication.data.base.ModelConstants
import com.google.gson.annotations.SerializedName

data class Gravatar(
    @SerializedName(ModelConstants.HASH_STRING)
    val hash: String
)