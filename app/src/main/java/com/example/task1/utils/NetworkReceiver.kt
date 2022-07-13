package com.example.task1.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast


class NetworkReceiver: BroadcastReceiver() {
    private var mContext:Context? = null
    override fun onReceive(context: Context?, intent: Intent?) {
        mContext=context
        val status: String = NetworkUtil.getConnectivityStatusString(context).toString()
        Log.e("Receiver ", "" + status);

        if (status.equals("Not connected to Internet")) {
            Toast.makeText(context,"Not connected to Internet",Toast.LENGTH_LONG).show()


        } else {
            Log.e("Receiver ", "connected to internet");//your code when internet connection come back
            Toast.makeText(context,"connected to internet",Toast.LENGTH_LONG).show()
        }

        if(intent?.action.equals(Intent.ACTION_DATE_CHANGED)){
            Toast.makeText(context,"Date changed sucessfully",Toast.LENGTH_LONG).show()
        }


    }
}