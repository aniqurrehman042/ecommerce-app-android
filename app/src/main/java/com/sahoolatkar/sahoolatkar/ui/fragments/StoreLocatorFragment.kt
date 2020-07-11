package com.sahoolatkar.sahoolatkar.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.sahoolatkar.sahoolatkar.R
import kotlinx.android.synthetic.main.fragment_store_locator.*


class StoreLocatorFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private var map: GoogleMap? = null
    private lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_store_locator, container, false)
        mapView = view.findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)

        init(view)

        return view
    }

    private fun init(view: View) {
        mapView.onResume()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapView.getMapAsync(this)

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StoreLocatorFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onMapReady(map: GoogleMap?) {
        this.map = map

        val latLng = LatLng(31.456544, 74.301541)

        val marker = map?.addMarker(
            MarkerOptions()
            .position(latLng)
            .title("SAHOOLAT KAR TOWNSHIP"))

        moveToCurrentLocation(latLng)
    }

    override fun onMarkerClick(p0: Marker?): Boolean {
        return true
    }

    private fun moveToCurrentLocation(currentLocation: LatLng) {
        map?.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15f))
        // Zoom in, animating the camera.
        map?.animateCamera(CameraUpdateFactory.zoomIn())
        // Zoom out to zoom level 10, animating with a duration of 2 seconds.
        map?.animateCamera(CameraUpdateFactory.zoomTo(15f), 2000, null)
    }
}