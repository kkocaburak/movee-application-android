package com.adesso.moveeapplication.data.components.network.repository.tmdb.model


import com.adesso.moveeapplication.data.base.ModelConstants
import com.adesso.moveeapplication.data.model.tmdb.Cast
import com.adesso.moveeapplication.data.model.tmdb.TvSeriesCrew
import com.google.gson.annotations.SerializedName

data class TvSeriesCastResponse(
    @SerializedName(ModelConstants.CAST_STRING)
    val cast: List<Cast>,
    @SerializedName(ModelConstants.CREW_STRING)
    val crew: List<TvSeriesCrew>,
    @SerializedName(ModelConstants.ID_STRING)
    val id: Int
)