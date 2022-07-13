package com.example.task1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.work.*
import com.example.task1.database.RoomDb
import com.example.task1.utils.MyWorkerClass
import kotlinx.android.synthetic.main.activity_display_data.*
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit

class DisplayData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_data)

        val constraints = Constraints.Builder().setRequiresCharging(false)
            .setRequiredNetworkType(NetworkType.NOT_REQUIRED).build()

        val delayToPass:Long = 64800000-32400000
        Log.d("time==>",delayToPass.toString())
        val periodicWorkRequest =
            PeriodicWorkRequest.Builder(MyWorkerClass::class.java, 24, TimeUnit.HOURS)
                .setInputData(Data.Builder().putBoolean("isStart", true).build())
                .setInitialDelay(delayToPass, TimeUnit.MILLISECONDS)
                .build()

        val workManager = WorkManager.getInstance(this)

        workManager.enqueue(periodicWorkRequest)

        workManager.getWorkInfoByIdLiveData(periodicWorkRequest.id).observeForever {
            if (it != null) {

                Log.d("periodicWorkRequest", "Status changed to ${it.state.isFinished}")

            }
        }
      val userDao= RoomDb.getAppDateBase(this)?.userDao()
        val list=userDao?.getUserData()
        val sb=StringBuffer()
        list?.forEach {
            //displayNameTVID.text= sb.append(it.toString())

            displayNameTVID.append("Name : ${it.username} \n")
            phoneTVID.append("Phone : ${it.userphone} \n")
            emailTVID.append("Email : ${it.userEmail} \n")
        }
       // peroidicReq()


        }

    fun peroidicReq(){
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
            .setRequiresCharging(false)
            .setRequiresBatteryNotLow(true)
            .build()

        val periodicWorkRequest =
            PeriodicWorkRequest.Builder(MyWorkerClass::class.java, 24, TimeUnit.HOURS)
                .setInputData(Data.Builder().putBoolean("isStart", true).build())
                .setConstraints(constraints)
                .setInitialDelay(0, TimeUnit.MILLISECONDS)
                .setConstraints(constraints)
                .build()

        val workManager = WorkManager.getInstance(this)

        workManager.enqueue(periodicWorkRequest)

        workManager.getWorkInfoByIdLiveData(periodicWorkRequest.id).observeForever {
            if (it != null) {
                Log.d("periodicWorkRequest", "Status changed to ${it.state.isFinished}")

            }
        }
    }

}