package com.adesso.moveeapplication.data.model.maps

import com.adesso.moveeapplication.data.base.ModelConstants
import com.adesso.moveeapplication.data.components.network.repository.maps.base.ModelMapsConstants
import com.google.gson.annotations.SerializedName

data class PlaceDetailResult(
    @SerializedName(ModelMapsConstants.ADDRESS_COMPONENTS_STRING)
    val addressComponents: List<AddressComponent>,
    @SerializedName(ModelMapsConstants.ADR_ADDRESS_STRING)
    val adrAddress: String,
    @SerializedName(ModelMapsConstants.FORMATTED_ADDRESS_STRING)
    val formattedAddress: String,
    @SerializedName(ModelMapsConstants.FORMATTED_PHONE_NUMBER_STRING)
    val formattedPhoneNumber: String,
    @SerializedName(ModelMapsConstants.GEOMETRY_STRING)
    val geometry: Geometry,
    @SerializedName(ModelMapsConstants.ICON_STRING)
    val icon: String,
    @SerializedName(ModelConstants.ID_STRING)
    val id: String,
    @SerializedName(ModelMapsConstants.INTERNATIONAL_PHONE_NUMBER_STRING)
    val internationalPhoneNumber: String,
    @SerializedName(ModelConstants.NAME_STRING)
    val name: String?,
    @SerializedName(ModelMapsConstants.PLACE_ID_STRING)
    val placeId: String,
    @SerializedName(ModelMapsConstants.RATING_STRING)
    val rating: Double,
    @SerializedName(ModelMapsConstants.REFERENCE_STRING)
    val reference: String,
    @SerializedName(ModelMapsConstants.REVIEWS_STRING)
    val reviews: List<Review>,
    @SerializedName(ModelMapsConstants.TYPES_STRING)
    val types: List<String>,
    @SerializedName(ModelMapsConstants.URL_STRING)
    val url: String,
    @SerializedName(ModelMapsConstants.UTC_OFFSET_STRING)
    val utcOffset: Int,
    @SerializedName(ModelMapsConstants.VICINITY_STRING)
    val vicinity: String,
    @SerializedName(ModelMapsConstants.WEBSITE_STRING)
    val website: String?
)