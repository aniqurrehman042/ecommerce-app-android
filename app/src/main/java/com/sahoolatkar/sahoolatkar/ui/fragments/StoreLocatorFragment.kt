package com.sahoolatkar.sahoolatkar.ui.fragments

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.SphericalUtil
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.models.StoreModel
import com.sahoolatkar.sahoolatkar.utils.ViewUtils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_store_locator.*
import java.util.*


class StoreLocatorFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private val NEARBY_RADIUS = 80
    private var map: GoogleMap? = null
    private val REQUEST_LOCATION: Int = 1
    private lateinit var mainActivity: Activity
    private val stores: MutableList<StoreModel> = ArrayList()
    private val storeMarkers: MutableList<Marker> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onResume() {
        super.onResume()
        if (mapView != null)
            mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        if (mapView != null)
            mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        if (mapView != null)
            mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mapView != null)
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

        return view
    }

    private fun init() {
        addStores()
        setListeners()
    }

    private fun setListeners() {
        llClose.setOnClickListener {
            hideStoreDetails()
        }
    }

    private fun hideStoreDetails() {
        ViewUtils.hideView(llClose)
        ViewUtils.hideView(llStoreDetails)
    }

    private fun showStoreDetails() {
        ViewUtils.showView(llClose)
        ViewUtils.showView(llStoreDetails)
    }

    private fun addStores() {
        stores.add(
            StoreModel(
                "Sahooolat Kar Township",
                31.456544,
                74.301541,
                "Boss Sahoolat Kar, College Road, Township, Lahore",
                "9 am - 8 pm",
                "0323-4000062",
                "https://lh5.googleusercontent.com/p/AF1QipOBKUmL73b4UjnH3I6tO2sKaJ9gP5_8ne-P7b9V=w408-h669-k-no"
            )
        )
        stores.add(
            StoreModel(
                "Sahooolat Kar LOS",
                31.542794,
                74.316784,
                "Boss Sahoolat Kar, Firoz Pur Road, LOS Chowk, Lahore",
                "9 am - 8 pm",
                "0304-4855158",
                "https://lh5.googleusercontent.com/p/AF1QipOBKUmL73b4UjnH3I6tO2sKaJ9gP5_8ne-P7b9V=w408-h669-k-no"
            )
        )
        stores.add(
            StoreModel(
                "Sahooolat Kar LOS",
                38.502794,
                70.296784,
                "Boss Sahoolat Kar, Firoz Pur Road, LOS Chowk, Lahore",
                "9 am - 8 pm",
                "0304-4855158",
                "https://lh5.googleusercontent.com/p/AF1QipOBKUmL73b4UjnH3I6tO2sKaJ9gP5_8ne-P7b9V=w408-h669-k-no"
            )
        )
        stores.add(
            StoreModel(
                "Sahooolat Kar LOS",
                33.602794,
                78.306784,
                "Boss Sahoolat Kar, Firoz Pur Road, LOS Chowk, Lahore",
                "9 am - 8 pm",
                "0304-4855158",
                "https://lh5.googleusercontent.com/p/AF1QipOBKUmL73b4UjnH3I6tO2sKaJ9gP5_8ne-P7b9V=w408-h669-k-no"
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainActivity = activity as Activity

        //initialize before calling map onCreate
        MapsInitializer.initialize(activity)
        if (mapView != null) {
            //instead of passing null its a good practice to use savedInstanceState
            mapView.onCreate(savedInstanceState);
            mapView.onResume()
            mapView.getMapAsync(this)
            mapReadyCode(activity)
        }

        init()

    }

    private fun mapReadyCode(context: Context?) {

    }

    override fun onMapReady(map: GoogleMap?) {
        this.map = map
        map?.setOnMarkerClickListener(this)

        addStoreMarkers()
//        tvStoreTitle.text = SphericalUtil.computeDistanceBetween(storeMarkers[0].position, storeMarkers[1].position).toString()

//        val currentLocation = getCurrentLatLng()
//        if (currentLocation != null) {
//            moveToLocation(currentLocation)
//        } else {
//            moveCameraToShowAllStores()
//        }

        setPositionAndZoom()
    }

    private fun setPositionAndZoom() {
        moveCameraToShowAllStores()
    }

    private fun moveCameraToShowAllStores() {
        val bounds: LatLngBounds = getBoundsAndSetNearestStoreDetails()

        val padding = 80 // offset from edges of the map in pixels

        val cu = CameraUpdateFactory.newLatLngBounds(bounds, 800, 800, padding)

        map?.moveCamera(cu)
        map?.animateCamera(cu)
    }

    private fun getBoundsAndSetNearestStoreDetails(): LatLngBounds {
        val currentPosition = getCurrentLatLng()
        var nearestStore: StoreModel? = null
        var nearestDistance: Int? = null
        val builder: LatLngBounds.Builder = LatLngBounds.Builder()

        storeMarkers.forEachIndexed { index, marker ->
            if (currentPosition != null) {
                val distanceBtwMeAndStore = SphericalUtil.computeDistanceBetween(
                    marker.position,
                    currentPosition
                )
                if (distanceBtwMeAndStore < (NEARBY_RADIUS * 1000)
                ) {
                    builder.include(marker.position)
                }
                if (nearestDistance == null || nearestDistance!! > distanceBtwMeAndStore.toInt()) {
                    nearestDistance = distanceBtwMeAndStore.toInt()
                    nearestStore = stores[index]
                }
            } else {
                builder.include(marker.position)
            }
        }

        if (nearestStore != null) {
            setStoreDetails(nearestStore!!)
        }

        if (currentPosition != null) {
            builder.include(currentPosition)
        }

        return builder.build()
    }

    private fun addStoreMarkers() {

        for (store in stores) {
            storeMarkers.add(
                map?.addMarker(
                    MarkerOptions()
                        .position(LatLng(store.storeLat, store.storeLng))
                        .title(store.storeTitle)
                )!!
            )
        }
    }

    override fun onMarkerClick(marker: Marker?): Boolean {

        if (storeMarkers.contains(marker)) {
            var selectedStore = stores[storeMarkers.indexOf(marker)]
            setStoreDetails(selectedStore)
            showStoreDetails()
        }
        return true
    }

    private fun setStoreDetails(selectedStore: StoreModel) {
        Picasso.get().load(selectedStore.storeImgUrl).into(ivStoreImg)
        tvStoreTitle.text = selectedStore.storeTitle
        tvStoreAddress.text = selectedStore.storeAddress
        tvStoreTiming.text = selectedStore.storeTiming
        tvStorePhone.text = selectedStore.storePhone
        showStoreDetails()
    }

    private fun moveToLocation(currentLocation: LatLng) {
        map?.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15f))
        // Zoom in, animating the camera.
        map?.animateCamera(CameraUpdateFactory.zoomIn())
        // Zoom out to zoom level 10, animating with a duration of 2 seconds.
        map?.animateCamera(CameraUpdateFactory.zoomTo(15f), 2000, null)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_LOCATION) {
            if (permissions[0] == Manifest.permission.ACCESS_COARSE_LOCATION && grantResults[0] == PackageManager.PERMISSION_GRANTED
                && permissions[1] == Manifest.permission.ACCESS_FINE_LOCATION && grantResults[1] == PackageManager.PERMISSION_GRANTED
            ) {
                moveCameraToShowAllStores()
            } else {

            }
        }
    }

    private fun getCurrentLatLng(): LatLng? {

        if (ActivityCompat.checkSelfPermission(
                mainActivity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                mainActivity,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ),
                REQUEST_LOCATION
            )
            return null
        } else {
            val locationManager =
                requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val locationProvider = LocationManager.NETWORK_PROVIDER
            val lastKnownLocation =
                locationManager.getLastKnownLocation(locationProvider)
            val userLat = lastKnownLocation!!.latitude
            val userLng = lastKnownLocation.longitude
            map!!.isMyLocationEnabled = true
            return LatLng(userLat, userLng)
        }
    }
}