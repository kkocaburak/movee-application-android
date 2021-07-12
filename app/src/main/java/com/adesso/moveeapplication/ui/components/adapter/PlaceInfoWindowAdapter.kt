package com.adesso.moveeapplication.ui.components.adapter

import android.view.View
import android.widget.TextView
import com.adesso.moveeapplication.R
import com.adesso.moveeapplication.application.MoveeApplication
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

/**
 * Created by Burak Karakoca on 28.10.2020.
 */
class PlaceInfoWindowAdapter : GoogleMap.InfoWindowAdapter {
    override fun getInfoContents(p0: Marker?): View? {
        return null
    }

    override fun getInfoWindow(p0: Marker?): View {
        val view = View.inflate(
            MoveeApplication.appContext,
            R.layout.custom_marker,
            null)
        val txtResourceName: TextView = view.findViewById(R.id.text_marker_title)
        if (p0?.title != null) {
            txtResourceName.text = p0.title
        }
        return view
    }

}