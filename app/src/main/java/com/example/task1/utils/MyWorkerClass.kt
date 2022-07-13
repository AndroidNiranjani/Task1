package com.example.task1.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log.d
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.task1.R
import com.example.task1.database.RoomDb

class MyWorkerClass(context : Context, params: WorkerParameters): Worker(context, params) {
    private  val TAG = "MyWorkerClass"
   private val userDao= RoomDb.getAppDateBase(context)?.userDao()

    override fun doWork(): Result {
        val list=userDao?.getUserData()
         d(TAG,"List:"+list.toString())
        d(TAG,"Uploading photos in background")

        sendNotification("Background Task","Succcessfully done")
        return Result.success()
    }

    private fun sendNotification(title: String, message: String) {
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        //If on Oreo then notification required a notification channel.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel("default", "Default", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }
        val notification: NotificationCompat.Builder = NotificationCompat.Builder(
            applicationContext,
            "default"
        )
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.mipmap.ic_launcher)
        notificationManager.notify(1, notification.build())
    }
}