package com.qbitscience.live_location_qbitscience

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.qbitscience.live_location_qbitscience.databinding.ActivityCurrentLocationMapsBinding

class Current_Location_MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding:ActivityCurrentLocationMapsBinding
    var lat_curr:Double = 0.0
    var long_curr:Double=0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var intent:Intent=getIntent()
        lat_curr= intent.getStringExtra("lat_curr")!!.toDouble()
        long_curr= intent.getStringExtra("long_curr")!!.toDouble()
        binding = ActivityCurrentLocationMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val QbitScience = LatLng(lat_curr, long_curr)
        mMap.addMarker(MarkerOptions().position(QbitScience).title("Welcome to QbitScience"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(QbitScience))
    }
}