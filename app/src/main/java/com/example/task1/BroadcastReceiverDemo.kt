package com.example.task1

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.task1.utils.AlarmReceiver
import com.example.task1.utils.DateChangeReceiver
import com.example.task1.utils.NetworkReceiver


class BroadcastReceiverDemo : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_broadcast_receiver_demo)


        val intentFilter = IntentFilter()
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        intentFilter.addAction(Intent.ACTION_DATE_CHANGED);
        intentFilter.addAction(Intent.ACTION_TIME_TICK)
        intentFilter.addAction(Intent.ACTION_TIMEZONE_CHANGED)
        intentFilter.addAction(Intent.ACTION_TIME_CHANGED)
        registerReceiver(NetworkReceiver(), intentFilter)


    }
}