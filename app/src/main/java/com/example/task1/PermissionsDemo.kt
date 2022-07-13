package com.example.task1



import android.Manifest.permission.*
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionsDemo : AppCompatActivity() {

    private lateinit var locationBTNID: Button
    private lateinit var storageBTNID: Button
    private lateinit var cameraBTNID: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permissions_demo)

        locationBTNID = findViewById(R.id.locationBTNID)
        storageBTNID = findViewById(R.id.storageBTNID)
        cameraBTNID = findViewById(R.id.cameraBTNID)

        locationBTNID.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) !==
                PackageManager.PERMISSION_GRANTED
            ) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        ACCESS_FINE_LOCATION
                    )
                ) {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(ACCESS_FINE_LOCATION), 1
                    )
                } else {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(ACCESS_FINE_LOCATION), 1
                    )
                }
            }
        }

        cameraBTNID.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, CAMERA) !==
                PackageManager.PERMISSION_GRANTED
            ) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this, CAMERA)) {
                    ActivityCompat.requestPermissions(this, arrayOf(CAMERA), 2)
                } else {
                    ActivityCompat.requestPermissions(this, arrayOf(CAMERA), 2)
                }
            }
        }
        storageBTNID.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) !==
                PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this, WRITE_EXTERNAL_STORAGE)) {
                    ActivityCompat.requestPermissions(this, arrayOf(WRITE_EXTERNAL_STORAGE), 3)
                } else {
                    ActivityCompat.requestPermissions(this, arrayOf(WRITE_EXTERNAL_STORAGE), 3)
                }
            }else if (ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) !==
                PackageManager.PERMISSION_GRANTED){

                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this, READ_EXTERNAL_STORAGE)) {
                    ActivityCompat.requestPermissions(this, arrayOf(READ_EXTERNAL_STORAGE), 4)
                } else {
                    ActivityCompat.requestPermissions(this, arrayOf(READ_EXTERNAL_STORAGE), 4)
                }
            }
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if ((ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) ===
                                PackageManager.PERMISSION_GRANTED)) {
                        Toast.makeText(this, "Location Permission Granted", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Location Permission Denied", Toast.LENGTH_SHORT).show()
                }
                return
            }

            2 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if ((ContextCompat.checkSelfPermission(this, CAMERA) ===
                                PackageManager.PERMISSION_GRANTED)) {
                        Toast.makeText(this, "Camera Permission Granted", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Camera Permission Denied", Toast.LENGTH_SHORT).show()
                }
                return
            }
            3-> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if ((ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) ===
                                PackageManager.PERMISSION_GRANTED)) {
                        Toast.makeText(this, "Storage Permission Granted", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Storage Permission Denied", Toast.LENGTH_SHORT).show()
                }
                return
            }
            4-> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if ((ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) ===
                                PackageManager.PERMISSION_GRANTED)) {
                        Toast.makeText(this, "Storage Permission Granted", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Storage Permission Denied", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }

    }
}