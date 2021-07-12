package com.adesso.moveeapplication.data.model.tmdb


import com.adesso.moveeapplication.data.base.ModelConstants
import com.google.gson.annotations.SerializedName

data class TvSeriesCrew(
    @SerializedName(ModelConstants.CREDIT_ID_STRING)
    val creditId: String,
    @SerializedName(ModelConstants.DEPARTMENT_STRING)
    val department: String,
    @SerializedName(ModelConstants.GENDER_STRING)
    val gender: Int,
    @SerializedName(ModelConstants.ID_STRING)
    val id: Int,
    @SerializedName(ModelConstants.JOB_STRING)
    val job: String,
    @SerializedName(ModelConstants.NAME_STRING)
    val name: String,
    @SerializedName(ModelConstants.PROFILE_PATH_STRING)
    val profilePath: Any
)