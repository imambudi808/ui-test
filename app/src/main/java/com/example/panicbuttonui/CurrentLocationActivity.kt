package com.example.panicbuttonui

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.example.panicbuttonui.helper.getMyColor
import com.example.panicbuttonui.helper.setLightStatusBar
import com.example.panicbuttonui.helper.setStatusBackgroundColor
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_current_location.*

class CurrentLocationActivity : AppCompatActivity(),OnMapReadyCallback,GoogleMap.OnMarkerClickListener {
    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }
    private lateinit var mMap:GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_location)
        setStatusBackgroundColor(getMyColor(R.color.white))
        setLightStatusBar(true)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        mapSetup()
        btnNext.setOnClickListener {
            startActivity(Intent(this, OpenCameraActivity::class.java))
        }
    }

    private fun mapSetup(){
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap) {

        mMap = p0
        mMap.getUiSettings().setZoomControlsEnabled(true)
        mMap.setOnMarkerClickListener(this)
        setUpMap(mMap)
    }

    override fun onMarkerClick(p0: Marker?): Boolean {
        return false
    }

    private fun setUpMap(map:GoogleMap) {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }

        // 1
        map.isMyLocationEnabled = true

        // 2
        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            // Got last known location. In some rare situations this can be null.
            // 3
            if (location != null) {
//                lastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                map.addMarker(MarkerOptions().position(currentLatLng).title("lokasi anda"))
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))
            }
        }
    }
}