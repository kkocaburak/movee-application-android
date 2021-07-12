package com.adesso.moveeapplication.data.components.network.repository.tmdb.model


import com.adesso.moveeapplication.data.base.ModelConstants
import com.adesso.moveeapplication.data.model.tmdb.Avatar
import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName(ModelConstants.AVATAR_STRING)
    val avatar: Avatar,
    @SerializedName(ModelConstants.ID_STRING)
    val id: Int,
    @SerializedName(ModelConstants.INCLUDE_ADULT_STRING)
    val includeAdult: Boolean,
    @SerializedName(ModelConstants.NAME_STRING)
    val name: String?,
    @SerializedName(ModelConstants.USERNAME_STRING)
    val username: String?
)