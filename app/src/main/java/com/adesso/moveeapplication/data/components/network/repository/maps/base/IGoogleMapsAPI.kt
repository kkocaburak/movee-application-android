package com.adesso.moveeapplication.data.components.network.repository.maps.base

import com.adesso.moveeapplication.data.components.network.repository.maps.model.NearbySearchResponse
import com.adesso.moveeapplication.data.components.network.repository.maps.model.PlaceDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Created by Burak Karakoca on 28.10.2020.
 */
interface IGoogleMapsAPI {
    @GET
    fun getNearSearch(
        @Url url: String?,
        @Query(GoogleMapsApiConstants.LOCATION_TEXT) location: String,
        @Query(GoogleMapsApiConstants.RADIUS_TEXT) radius: String,
        @Query(GoogleMapsApiConstants.TYPE_TEXT) type: String,
        @Query(GoogleMapsApiConstants.MAPS_API_TEXT) apiKey: String
    ): Call<NearbySearchResponse>

    @GET
    fun getPlaceDetail(
        @Url url: String?,
        @Query(GoogleMapsApiConstants.PLACE_ID_TEXT) placeId: String,
        @Query(GoogleMapsApiConstants.FIELDS_TEXT) fields: String,
        @Query(GoogleMapsApiConstants.MAPS_API_TEXT) apiKey: String
    ): Call<PlaceDetailResponse>

}