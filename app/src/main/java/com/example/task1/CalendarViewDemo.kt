package com.example.task1

import android.graphics.Color
import android.net.ParseException
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.timessquare.CalendarPickerView
import java.text.SimpleDateFormat
import java.util.*

class CalendarViewDemo : AppCompatActivity() {

    private lateinit var calendar_view: CalendarPickerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_view_demo)

        calendar_view=findViewById(R.id.calendar_view)
        val nextYear = Calendar.getInstance()
        nextYear.add(Calendar.YEAR, 1)
        val today = Date()
        calendar_view.init(today, nextYear.getTime())
            .withSelectedDate(today)
            .inMode(CalendarPickerView.SelectionMode.RANGE)
        calendar_view.highlightDates(getHolidays())

    }

    

    private fun getHolidays(): ArrayList<Date?>? {
        val sdf = SimpleDateFormat("dd-M-yyyy")
        val dateInString = "21-04-2023"
        var date: Date? = null
        try {
            date = sdf.parse(dateInString)
        }
        catch (e: ParseException) {
            e.printStackTrace()
        }
        val holidays: ArrayList<Date?> = ArrayList()
        holidays.add(date)
        return holidays
    }
}