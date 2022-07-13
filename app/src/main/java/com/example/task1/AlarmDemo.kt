package com.example.task1

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.task1.utils.AlarmService
import java.util.*

class AlarmDemo : AppCompatActivity() {
    lateinit var alarmService: AlarmService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm_demo)
        alarmService= AlarmService(this)

       val setExactbtn=findViewById<Button>(R.id.setExactbtn)
       val setRepetitiveBtn=findViewById<Button>(R.id.setRepetitiveBtn)

        setExactbtn.setOnClickListener {
            setAlarm { timeInMillis ->
                alarmService.setExactAlarm(timeInMillis)
            }
        }

        setRepetitiveBtn.setOnClickListener {
            setAlarm {
                alarmService.setRepetitiveAlarm(it)
            }
        }
    }

    private fun setAlarm(callback: (Long) -> Unit){
        Calendar.getInstance().apply {
            this.set(Calendar.SECOND,0)
            this.set(Calendar.MILLISECOND,0)
            DatePickerDialog(
                this@AlarmDemo,0,
                { _, year, month, day ->
                    this.set(Calendar.YEAR,year)
                    this.set(Calendar.MONTH,month)
                    this.set(Calendar.DAY_OF_MONTH,day)

                    TimePickerDialog(
                        this@AlarmDemo,
                        0,
                        { _, hour, min ->
                            this.set(Calendar.HOUR_OF_DAY,hour)
                            this.set(Calendar.MINUTE,min)
                            callback(this.timeInMillis)
                        },
                        this.get(Calendar.HOUR_OF_DAY),
                        this.get(Calendar.MINUTE),
                        false
                    ).show()
                },
                this.get(Calendar.YEAR),
                this.get(Calendar.MONTH),
                this.get(Calendar.DAY_OF_MONTH),
            ).show()
        }
    }
}