package com.adesso.moveeapplication.data.components.network.repository.maps.api

import com.adesso.moveeapplication.BuildConfig
import com.adesso.moveeapplication.R
import com.adesso.moveeapplication.application.MoveeApplication
import com.adesso.moveeapplication.data.components.network.repository.maps.base.GoogleMapsApiEndPoints
import com.adesso.moveeapplication.data.components.network.repository.maps.base.IGoogleMapsAPI
import com.adesso.moveeapplication.data.components.network.repository.maps.model.NearbySearchResponse
import com.adesso.moveeapplication.data.components.network.repository.maps.model.PlaceDetailResponse
import com.adesso.moveeapplication.data.util.NetworkUtil
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Burak Karakoca on 28.10.2020.
 */
object GoogleMapsApi {
    private val googleMapsAPI: IGoogleMapsAPI

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.MAPS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        googleMapsAPI = retrofit.create(IGoogleMapsAPI::class.java)
    }

    fun getNearbySearch(
        myCallback: Callback<NearbySearchResponse>,
        latLng: String,
        radius: String,
        query: String
    ) {
        if (!NetworkUtil.isNetworkAvailable(MoveeApplication.appContext)) {
            return
        }

        val nearbyPlacesInfo = googleMapsAPI.getNearSearch(
            GoogleMapsApiEndPoints.MAPS_NEAR_PLACES.url,
            latLng,
            radius,
            query,
            BuildConfig.MAPS_API_KEY
        )

        nearbyPlacesInfo.enqueue(myCallback)
    }

    fun getPlaceDetail(myCallback: Callback<PlaceDetailResponse>, placeId: String) {
        if (!NetworkUtil.isNetworkAvailable(MoveeApplication.appContext)) {
            return
        }

        val placeDetail = googleMapsAPI.getPlaceDetail(
            GoogleMapsApiEndPoints.MAPS_PLACE_DETAIL.url,
            placeId,
            MoveeApplication.appContext.getString(R.string.maps_text_search_parameters),
            BuildConfig.MAPS_API_KEY
        )

        placeDetail.enqueue(myCallback)
    }

}