package com.adesso.moveeapplication.data.model.tmdb

import com.adesso.moveeapplication.data.base.ModelConstants
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Burak Karakoca on 12.10.2020.
 */
data class Cast(
    @SerializedName(ModelConstants.CHARACTER_STRING)
    val character: String,
    @SerializedName(ModelConstants.CREDIT_ID_STRING)
    val creditId: String,
    @SerializedName(ModelConstants.GENDER_STRING)
    val gender: Int,
    @SerializedName(ModelConstants.ID_STRING)
    override val id: Int,
    @SerializedName(ModelConstants.NAME_STRING)
    override val name: String?,
    @SerializedName(ModelConstants.ORDER_STRING)
    val order: Int,
    @SerializedName(ModelConstants.PROFILE_PATH_STRING)
    override val profilePath: String?
) : Serializable, ICast