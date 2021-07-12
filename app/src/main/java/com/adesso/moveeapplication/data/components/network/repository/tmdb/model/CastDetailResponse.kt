package com.adesso.moveeapplication.data.components.network.repository.tmdb.model


import com.adesso.moveeapplication.data.base.ModelConstants
import com.google.gson.annotations.SerializedName

data class CastDetailResponse(
    @SerializedName(ModelConstants.ADULT_STRING)
    val adult: Boolean,
    @SerializedName(ModelConstants.ALSO_KNOWN_AS_STRING)
    val alsoKnownAs: List<Any>,
    @SerializedName(ModelConstants.BIOGRAPHY_STRING)
    val biography: String?,
    @SerializedName(ModelConstants.BIRTHDAY_STRING)
    val birthday: String?,
    @SerializedName(ModelConstants.DEATH_DAY_STRING)
    val deathDay: String,
    @SerializedName(ModelConstants.GENDER_STRING)
    val gender: Int,
    @SerializedName(ModelConstants.HOMEPAGE_STRING)
    val homepage: Any,
    @SerializedName(ModelConstants.ID_STRING)
    val id: Int,
    @SerializedName(ModelConstants.IMDB_ID_STRING)
    val imdbId: String,
    @SerializedName(ModelConstants.KNOWN_FOR_DEPARTMENT_STRING)
    val knownForDepartment: String,
    @SerializedName(ModelConstants.NAME_STRING)
    val name: String,
    @SerializedName(ModelConstants.PLACE_OF_BIRTH_STRING)
    val placeOfBirth: String?,
    @SerializedName(ModelConstants.POPULARITY_STRING)
    val popularity: Double,
    @SerializedName(ModelConstants.PROFILE_PATH_STRING)
    val profilePath: String
)