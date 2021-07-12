package com.adesso.moveeapplication.data.components.network.repository.maps.base

/**
 * Created by Burak Karakoca on 28.10.2020.
 */
enum class GoogleMapsApiEndPoints(val url: String) {
    MAPS_NEAR_PLACES("maps/api/place/nearbysearch/json"),
    MAPS_PLACE_DETAIL("maps/api/place/details/json")
}