package com.example.task1.utils



import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.text.format.DateFormat
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.task1.R
import java.util.*
import java.util.concurrent.TimeUnit


class AlarmReceiver:BroadcastReceiver(){

    override fun onReceive(context: Context, intent: Intent) {
        val timeInMillis = intent.getLongExtra(Constants.EXTRA_EXACT_ALARM_TIME, 0L)

        when(intent.action){
            Constants.ACTION_SET_EXACT_ALARM ->{
                buildNotification(context,"Set Exact Time",convertDate(timeInMillis))
                Toast.makeText(context,"Set Exact Time"+convertDate(timeInMillis),Toast.LENGTH_LONG).show()
            }
            Constants.ACTION_SET_REPITITIVE_ALARM ->{
                val cal=Calendar.getInstance().apply {
                    this.timeInMillis=timeInMillis+ TimeUnit.DAYS.toMillis(7)

                }
             AlarmService(context).setRepetitiveAlarm(cal.timeInMillis)
                buildNotification(context,"Set Repetitive Time",convertDate(timeInMillis))
                Toast.makeText(context,"Set REPITITIVE Time"+convertDate(timeInMillis),Toast.LENGTH_LONG).show()

            }

            }

    }
private fun buildNotification(context: Context,title:String, message:String) {

    val CHANNEL_ID="Notification ID"

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel=  NotificationChannel(CHANNEL_ID,CHANNEL_ID, NotificationManager.IMPORTANCE_LOW)

       context. getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        val builder = NotificationCompat.Builder(context,CHANNEL_ID)
        builder.setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()

        with(NotificationManagerCompat.from(context)) {
            notify(101, builder.build())
        }


    }


}

    private fun convertDate(timeInMillis:Long): String =
        DateFormat.format("dd/MM/yyyy hh:mm:ss", timeInMillis).toString()
    }