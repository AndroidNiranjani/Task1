package com.example.task1

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import android.os.Bundle
import android.provider.Settings
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.*

class CurrentLocation : AppCompatActivity() {
    private lateinit var fusedLocationProviderClient:FusedLocationProviderClient
    private lateinit var locationTVID:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_location)

        fusedLocationProviderClient=LocationServices.getFusedLocationProviderClient(this)
        locationTVID=findViewById(R.id.locationTVID)
        getCurrentLocation()

    }

    @SuppressLint("SetTextI18n")
    private fun getCurrentLocation(){

        if(checkPermissions()){
            if(isLocationEnabled()){

                if (ActivityCompat.checkSelfPermission(
                        this,
                        ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                   requestPermission()
                    return
                }
                fusedLocationProviderClient.lastLocation.addOnCompleteListener(this){ task->

                    val location:Location?=task.result

                    if(location==null){
                        Toast.makeText(applicationContext,"Null Received",Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(applicationContext,"Get Success",Toast.LENGTH_LONG).show()

                        locationTVID.text="Latitude : "+ location.latitude + "\n"+ "Longitude : "+ location.longitude

                        val geocoder= Geocoder(this, Locale.getDefault())
                        val addresses:List<Address> = geocoder.getFromLocation(location.latitude,location.longitude,1)

                        val areaTVID:TextView=findViewById(R.id.getLocation)

                        areaTVID.text= "Area: "+ addresses[0].getAddressLine(0) + "\n"+
                                        "State Name :" + addresses[0].adminArea

                    }

                }

            }else{
                Toast.makeText(applicationContext,"Turn on Location",Toast.LENGTH_LONG).show()
                val intent= Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }

        }else{
            requestPermission()
        }

    }

    private fun isLocationEnabled():Boolean{

        val locationManager:LocationManager=getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    private fun requestPermission(){
        ActivityCompat.requestPermissions(this, arrayOf(ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION),
            PERMISSION_ACCESS_CODE)
    }

    companion object{
        private const val PERMISSION_ACCESS_CODE=100
    }

    private fun checkPermissions() : Boolean
    {
        if(ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED)
            {
            return true
        }
        return false
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode== PERMISSION_ACCESS_CODE){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(applicationContext,"Granted",Toast.LENGTH_LONG).show()
                getCurrentLocation()
            }else{
                Toast.makeText(applicationContext,"Denied",Toast.LENGTH_LONG).show()
            }
        }
    }


}