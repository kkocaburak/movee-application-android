package com.adesso.moveeapplication.ui.screens.map.viewmodel

import androidx.lifecycle.MutableLiveData
import com.adesso.moveeapplication.data.components.network.repository.maps.api.GoogleMapsApi
import com.adesso.moveeapplication.data.components.network.repository.maps.model.NearbySearchResponse
import com.adesso.moveeapplication.data.components.network.repository.maps.model.PlaceDetailResponse
import com.adesso.moveeapplication.ui.base.BaseViewModel
import com.google.android.gms.maps.model.LatLng
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Burak Karakoca on 28.10.2020.
 */
class MapViewModel : BaseViewModel() {
    val nearbyResponse : MutableLiveData<NearbySearchResponse> = MutableLiveData()
    val placeDetailResponse : MutableLiveData<PlaceDetailResponse> = MutableLiveData()
    val userLastLocation : MutableLiveData<LatLng> = MutableLiveData()

    private var mapsNearbyCallback = object : Callback<NearbySearchResponse> {
        override fun onFailure(call: Call<NearbySearchResponse>, t: Throwable) {
            responseNotSuccessful()
        }
        override fun onResponse(call: Call<NearbySearchResponse>, response: Response<NearbySearchResponse>) {
            if (response.isSuccessful) {
                nearbyResponse.value = response.body()
            } else {
                responseNotSuccessful()
            }
        }
    }

    private var placeDetailCallback = object : Callback<PlaceDetailResponse> {
        override fun onFailure(call: Call<PlaceDetailResponse>, t: Throwable) {
            responseNotSuccessful()
        }
        override fun onResponse(call: Call<PlaceDetailResponse>, response: Response<PlaceDetailResponse>) {
            if (response.isSuccessful) {
                placeDetailResponse.value = response.body()
            } else {
                responseNotSuccessful()
            }
        }
    }

    fun getMapsNearbySearch(latLng: String, radius: String, query: String) {
        GoogleMapsApi.getNearbySearch(mapsNearbyCallback, latLng, radius, query)
    }

    fun getPlaceDetail(placeId: String) {
        GoogleMapsApi.getPlaceDetail(placeDetailCallback,placeId)
    }
}