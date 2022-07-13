package com.example.task1.utils

import android.content.Context
import android.net.ConnectivityManager


class NetworkUtil {
    companion object {
        var TYPE_WIFI = 1
        var TYPE_MOBILE = 2
        var TYPE_NOT_CONNECTED = 0

        fun getConnectivityStatus(context: Context?): Int {
            val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo
            if (null != activeNetwork) {
                if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) return TYPE_WIFI
                if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) return TYPE_MOBILE
            }
            return TYPE_NOT_CONNECTED

        }

        fun getConnectivityStatusString(context: Context?): String? {
            val conn: Int = getConnectivityStatus(context)
            var status: String? = null
            if (conn == TYPE_WIFI) {
                //status = "Wifi enabled";
                status = "Internet connection available"
            } else if (conn == TYPE_MOBILE) {
                //status = "Mobile data enabled";
                status = "Internet connection available"
            } else if (conn == TYPE_NOT_CONNECTED) {
                status = "Not connected to Internet"
            }
            return status
        }
    }
}