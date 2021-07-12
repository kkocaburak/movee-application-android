package com.adesso.moveeapplication.data.model.tmdb

import com.adesso.moveeapplication.data.base.ModelConstants
import com.google.gson.annotations.SerializedName
/**
 * Created by Burak Karakoca on 23.09.2020.
 */
data class Genre(
    @SerializedName(ModelConstants.ID_STRING)
    val id: Int,
    @SerializedName(ModelConstants.NAME_STRING)
    val name: String
)