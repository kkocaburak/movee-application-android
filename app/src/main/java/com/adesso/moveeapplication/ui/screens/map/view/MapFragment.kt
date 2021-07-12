package com.adesso.moveeapplication.ui.screens.map.view

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.adesso.moveeapplication.R
import com.adesso.moveeapplication.application.MoveeConstants
import com.adesso.moveeapplication.data.model.maps.NearbySearchResult
import com.adesso.moveeapplication.data.model.maps.PlaceDetailResult
import com.adesso.moveeapplication.ui.base.BaseFragment
import com.adesso.moveeapplication.ui.base.BaseUIConstants
import com.adesso.moveeapplication.ui.base.ViewModelFactory
import com.adesso.moveeapplication.ui.components.adapter.PlaceInfoWindowAdapter
import com.adesso.moveeapplication.ui.screens.map.viewmodel.MapViewModel
import com.adesso.moveeapplication.ui.util.AlertDialogUtil
import com.adesso.moveeapplication.ui.util.IntentUtil
import com.adesso.moveeapplication.ui.util.StringCompilerUtil
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.*

class MapFragment : BaseFragment(),
    OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener,
    ActivityCompat.OnRequestPermissionsResultCallback {

    private lateinit var mapViewModel : MapViewModel

    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap

    private var placeDetailPopUp: PopupWindow?= null
    private var placeList: ArrayList<NearbySearchResult> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapViewModel = ViewModelProvider(this, ViewModelFactory())
            .get(MapViewModel::class.java)

        registerLiveData()
        initComponents()
        initMap(savedInstanceState)
    }

    //region RegisterLiveData
    private fun registerLiveData() {
        registerNearbySearch()
        registerLastLocation()
        registerPlaceDetail()
        registerError()
    }

    private fun registerNearbySearch() {
        mapViewModel.nearbyResponse.observe(viewLifecycleOwner, Observer {
            for (place in it.results) {
                val marker = MarkerOptions()
                    .position(LatLng(place.geometry.location.lat, place.geometry.location.lng))
                    .title(place.name)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))

                googleMap.addMarker(marker)
                placeList.add(place)
            }
        })
    }

    private fun registerLastLocation() {
        mapViewModel.userLastLocation.observe(viewLifecycleOwner, Observer {

            googleMap.moveCamera(CameraUpdateFactory.newLatLng(it))
            googleMap.setOnInfoWindowClickListener(this)

            val cameraPosition: CameraPosition = CameraPosition.builder()
                .target(it)
                .zoom(BaseUIConstants.MAP_ZOOM)
                .bearing(BaseUIConstants.MAP_BEARING)
                .tilt(BaseUIConstants.MAP_TILT)
                .build()
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

            val lat = StringCompilerUtil.compileWithComma(it.latitude)
            val latLong = StringCompilerUtil.compileString(lat, it.longitude)
            mapViewModel.getMapsNearbySearch(
                latLong,
                BaseUIConstants.MAP_RADIUS.toString(),
                BaseUIConstants.MAP_SEARCH_TEXT
            )
        })
    }

    private fun registerPlaceDetail() {
        mapViewModel.placeDetailResponse.observe(viewLifecycleOwner, Observer {
            showPopUpDetailWindow(it.result)
        })
    }

    private fun registerError() {
        mapViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            AlertDialogUtil.showAlert(it, context)
        })
    }
    //endregion

    //region UI
    private fun initComponents() {
        mapView = requireView().findViewById(R.id.fragment_map_mapView)
    }

    private fun initMap(savedInstanceState: Bundle?) {
        mapView.onCreate(savedInstanceState)
        mapView.onResume()
        mapView.getMapAsync(this)
    }
    //endregion

    //region Events
    override fun onMapReady(_googleMap: GoogleMap) {
        googleMap = _googleMap
        enableMyLocation()

        googleMap.setOnInfoWindowClickListener(this)

        val customInfoWindowAdapter = PlaceInfoWindowAdapter()
        googleMap.setInfoWindowAdapter(customInfoWindowAdapter)
    }

    override fun onInfoWindowClick(p0: Marker?) {
        placeList.forEach {place ->
            if (place.name == p0?.title) {
                mapViewModel.getPlaceDetail(place.placeId)
            }
        }
    }
    //endregion

    private fun showPopUpDetailWindow(placeDetail: PlaceDetailResult) {
        placeDetailPopUp = showAlertFilter(placeDetail)
        placeDetailPopUp!!.isOutsideTouchable = true
        placeDetailPopUp!!.isFocusable = true
        placeDetailPopUp!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        placeDetailPopUp!!.showAtLocation(
            requireView(),
            Gravity.TOP,
            BaseUIConstants.MAP_POPUP_X_OFFSET,
            BaseUIConstants.MAP_POPUP_Y_OFFSET
        )
    }

    private fun enableMyLocation() {
        if (!::googleMap.isInitialized){
            return
        }
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            googleMap.isMyLocationEnabled = true

            LocationServices.getFusedLocationProviderClient(requireActivity()).lastLocation.addOnSuccessListener(
                requireActivity()
            ) {
                it?.apply {
                    mapViewModel.userLastLocation.value = LatLng(it.latitude, it.longitude)
                }
            }
        } else {
            if(shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)){
                Toast.makeText(
                    requireContext(),
                    getString(R.string.info_message_string),
                    Toast.LENGTH_SHORT
                ).show()
            }
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults[MoveeConstants.INT_ZERO] == PackageManager.PERMISSION_GRANTED){
            enableMyLocation()
        } else {
            Toast.makeText(
                requireContext(),
                getString(R.string.access_denied_string),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun showAlertFilter(placeDetail: PlaceDetailResult): PopupWindow {
        val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.pop_up_map_place_detail, null)

        val placeTitleText = view.findViewById<TextView>(R.id.pop_up_place_txt_name)
        val placeAddressText = view.findViewById<TextView>(R.id.pop_up_place_txt_address)
        val placeWebSiteText = view.findViewById<TextView>(R.id.pop_up_place_txt_website)

        val placeMapNavigationButton = view.findViewById<LinearLayout>(R.id.pop_up_place_layout_go)

        placeTitleText.text = placeDetail.name
        placeAddressText.text = placeDetail.formattedAddress

        if (placeDetail.website != null) {
            placeWebSiteText.text = placeDetail.website
            placeWebSiteText.setOnClickListener {
                IntentUtil.toWebsite(placeDetail.website)
            }
        }

        placeMapNavigationButton.setOnClickListener {
            val navigationLat = placeDetail.geometry.location.lat.toString()
            val navigationLng = placeDetail.geometry.location.lng.toString()
            loadNavigationView(navigationLat, navigationLng)
        }

        return PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    private fun loadNavigationView(lat: String, lng: String) {

        val navigation: Uri = Uri.parse(getString(R.string.map_location_string,lat,lng))
        val navigationIntent = Intent(Intent.ACTION_VIEW, navigation)
        navigationIntent.setPackage(getString(R.string.map_url_string))
        startActivity(navigationIntent)
    }

}