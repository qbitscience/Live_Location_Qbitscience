package com.qbitscience.live_location_qbitscience

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import java.text.DecimalFormat

class MainActivity : AppCompatActivity(),LocationListener {
    lateinit var locates:TextView
    lateinit var liveNext: Button
    lateinit var lat:String
    lateinit var long:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        locates=findViewById(R.id.locate)
        liveNext=findViewById(R.id.live)

        var locationManger:LocationManager= getSystemService(LOCATION_SERVICE) as LocationManager
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        locationManger.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0.0f,this)
        liveNext.setOnClickListener {
            locationManger.removeUpdates(this)

            intent=Intent(this,Current_Location_MapsActivity::class.java)
            intent.putExtra("lat_curr",lat)
            intent.putExtra("long_curr",long)
            startActivity(intent)
        }
    }

    override fun onLocationChanged(location: Location) {

        var latlong_precise=DecimalFormat("#.####")
        lat=latlong_precise.format(location.latitude)
        long=latlong_precise.format(location.longitude)

        locates.setText("{$lat}\n{$long}")
    }


}
