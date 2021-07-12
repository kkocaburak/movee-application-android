package com.adesso.moveeapplication.data.model.maps


import com.adesso.moveeapplication.data.base.ModelConstants
import com.adesso.moveeapplication.data.components.network.repository.maps.base.ModelMapsConstants
import com.google.gson.annotations.SerializedName

data class Review(
    @SerializedName(ModelMapsConstants.AUTHOR_NAME_STRING)
    val authorName: String,
    @SerializedName(ModelMapsConstants.AUTHOR_URL_STRING)
    val authorUrl: String,
    @SerializedName(ModelMapsConstants.LANGUAGE_STRING)
    val language: String,
    @SerializedName(ModelMapsConstants.PROFILE_PHOTO_URL_STRING)
    val profilePhotoUrl: String,
    @SerializedName(ModelMapsConstants.RATING_STRING)
    val rating: Int,
    @SerializedName(ModelMapsConstants.RELATIVE_TIME_STRING)
    val relativeTimeDescription: String,
    @SerializedName(ModelMapsConstants.TEXT_STRING)
    val text: String,
    @SerializedName(ModelMapsConstants.TIME_STRING)
    val time: Int
)