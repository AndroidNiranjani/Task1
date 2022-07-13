package com.example.task1

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar


class EnumDemo : AppCompatActivity() {

    private lateinit var enumETID: EditText
    private lateinit var conditionTVID:TextView
    private lateinit var enumBTID:Button
    private lateinit var enumStr:String

    private lateinit var simplesnackBtnID:Button
    private lateinit var callbackBTNID:Button
    private lateinit var colorBtnID:Button
    private lateinit var coordinator:CoordinatorLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enum_demo)


        simplesnackBtnID=findViewById(R.id.simplesnackBtnID)
        callbackBTNID=findViewById(R.id.callbackBTNID)
        colorBtnID=findViewById(R.id.colorBtnID)
        coordinator=findViewById(R.id.coordinator)


        simplesnackBtnID.setOnClickListener{
            val snackbar = Snackbar
                .make(coordinator, "Welcome to SnackBar", Snackbar.LENGTH_LONG)

            snackbar.show()
        }

        callbackBTNID.setOnClickListener {

            val snackbar=Snackbar.make(coordinator,"Message is deleted",Snackbar.LENGTH_LONG)
                .setAction("UNDO", View.OnClickListener {
                    val snackbar1=Snackbar.make(coordinator,"Message is restored!",Snackbar.LENGTH_LONG)
                    snackbar1.show()
                })

            snackbar.show()
        }

        colorBtnID.setOnClickListener{
            val snackbar=Snackbar.make(coordinator,"No Internet Connection!",Snackbar.LENGTH_LONG)
                .setAction("RETRY", View.OnClickListener {

                })

            snackbar.setActionTextColor(Color.RED);
            val view=snackbar.view
            val textView=view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
            textView.setTextColor(Color.YELLOW);
            snackbar.show();
        }

        enumETID=findViewById(R.id.enumETID)
        conditionTVID=findViewById(R.id.conditionTVID)
        enumBTID=findViewById(R.id.enumBTID)
        enumBTID.setOnClickListener {
            enumStr=enumETID.text.toString().trim()
            if(enumStr.isEmpty()){

                Toast.makeText(this,"Please enter any type of weather",Toast.LENGTH_SHORT).show()
            }else if (enumStr.equals("Summer",true)) {
                val type = Weather.Summer.condition
                Log.e("summer",type)
                conditionTVID.setText(type)
            } else if (enumStr.equals("Winter",true)) {
                val type = Weather.Winter.condition
                conditionTVID.setText(type)
            } else if (enumStr.equals("Rainy",true)) {
                val type = Weather.Rainy.condition
                conditionTVID.setText(type)
            }
        }
    }

enum class Weather(val condition:String){
    Summer("Hot"),Winter("Cool"),Rainy("Humidity")
}

}