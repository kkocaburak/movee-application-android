package com.adesso.moveeapplication.data.model.maps


import com.adesso.moveeapplication.data.base.ModelConstants
import com.adesso.moveeapplication.data.components.network.repository.maps.base.ModelMapsConstants
import com.adesso.moveeapplication.data.model.maps.Geometry
import com.adesso.moveeapplication.data.model.maps.OpeningHours
import com.adesso.moveeapplication.data.model.maps.Photo
import com.adesso.moveeapplication.data.model.maps.PlusCode
import com.google.gson.annotations.SerializedName

data class NearbySearchResult(
    @SerializedName(ModelMapsConstants.BUSINESS_STATUS_STRING)
    val businessStatus: String,
    @SerializedName(ModelMapsConstants.GEOMETRY_STRING)
    val geometry: Geometry,
    @SerializedName(ModelMapsConstants.ICON_STRING)
    val icon: String,
    @SerializedName(ModelConstants.NAME_STRING)
    val name: String,
    @SerializedName(ModelMapsConstants.OPENING_HOURS_STRING)
    val openingHours: OpeningHours,
    @SerializedName(ModelMapsConstants.PHOTOS_STRING)
    val photos: List<Photo>,
    @SerializedName(ModelMapsConstants.PLACE_ID_STRING)
    val placeId: String,
    @SerializedName(ModelMapsConstants.PLUS_CODE_STRING)
    val plusCode: PlusCode,
    @SerializedName(ModelMapsConstants.PRICE_LEVEL_STRING)
    val priceLevel: Int,
    @SerializedName(ModelMapsConstants.RATING_STRING)
    val rating: Double,
    @SerializedName(ModelMapsConstants.REFERENCE_STRING)
    val reference: String,
    @SerializedName(ModelMapsConstants.SCOPE_STRING)
    val scope: String,
    @SerializedName(ModelMapsConstants.TYPES_STRING)
    val types: List<String>,
    @SerializedName(ModelMapsConstants.USER_RATING_TOTAL_STRING)
    val userRatingsTotal: Int,
    @SerializedName(ModelMapsConstants.VICINITY_STRING)
    val vicinity: String
)