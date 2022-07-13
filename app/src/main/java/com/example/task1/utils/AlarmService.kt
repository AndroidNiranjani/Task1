package com.example.task1.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build

class AlarmService(private val context:Context) {

    private val alarmManager:AlarmManager?= context.getSystemService(Context.ALARM_SERVICE) as AlarmManager?

    fun setExactAlarm(timeInMills:Long){
        setAlarm(timeInMills,
        getPendingIntent(getIntent().apply {
            action= Constants.ACTION_SET_EXACT_ALARM
            putExtra(Constants.EXTRA_EXACT_ALARM_TIME, timeInMills)
        }))
    }

    fun setRepetitiveAlarm(timeInMills:Long){
        setAlarm(timeInMills,
            getPendingIntent(getIntent().apply {
                action= Constants.ACTION_SET_REPITITIVE_ALARM
                putExtra(Constants.EXTRA_EXACT_ALARM_TIME, timeInMills)
            }))
    }

    private fun setAlarm(timeInMills: Long,pendingIntent: PendingIntent){
        alarmManager?.let {
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    timeInMills,
                    pendingIntent
                )
            }

            else{
                alarmManager.setExact(
                    AlarmManager.RTC_WAKEUP,
                    timeInMills,
                    pendingIntent
                )
            }
        }
    }

    private fun getIntent()=Intent(context,AlarmReceiver::class.java)

    private fun getPendingIntent(intent: Intent)=
        PendingIntent.getBroadcast(context,RandomIntUtil.getRandomInt(),intent,PendingIntent.FLAG_UPDATE_CURRENT)
}