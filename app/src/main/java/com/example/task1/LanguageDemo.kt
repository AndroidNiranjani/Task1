package com.example.task1

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.example.task1.model.LocalisationResponse
import com.example.task1.utils.LocalHelp
import com.example.task1.utils.LocaleHelper
import com.example.task1.webapi.ApiInterface
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LanguageDemo : AppCompatActivity() {

    private lateinit var messageview:TextView
    private lateinit var messageview1:TextView
    private lateinit var messageview2:TextView
    private lateinit var messageview3:TextView
    private lateinit var messageview4:TextView
    private lateinit var btnSpanish:Button
    private lateinit var btnEnglish:Button
    private lateinit var btnodia:Button
    private lateinit var context:Context
    lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language_demo)
        sharedPreferences=getSharedPreferences("SHARED_PREF",Context.MODE_PRIVATE)
        messageview=findViewById(R.id.messageTVID)
        messageview1=findViewById(R.id.message2TVID)
        messageview2=findViewById(R.id.message3TVID)
        messageview3=findViewById(R.id.message4TVID)
        messageview4=findViewById(R.id.message5TVID)
        btnSpanish=findViewById(R.id.btnSpanish)
        btnEnglish=findViewById(R.id.btnEnglish)
        btnodia=findViewById(R.id.btnodia)

        callLocalApi()
        val gson = Gson()
        val json = sharedPreferences.getString("LanguageData", "")
        val localdata = gson.fromJson(json, LocalisationResponse::class.java)

        btnEnglish.setOnClickListener{
            val localHelp= LocalHelp()
            context = localHelp.setLocale(this, "en")!!;

           // val resources = context.getResources();
            messageview.text = localdata.Resources[0].Data[0].Key
            messageview1.text = localdata.Resources[0].Data[2].Key
            messageview2.text = localdata.Resources[0].Data[3].Key
            messageview3.text = localdata.Resources[0].Data[4].Key
            messageview4.text = localdata.Resources[0].Data[6].Key
        }

        btnSpanish.setOnClickListener{
            val localHelp= LocalHelp()
            context = localHelp.setLocale(this, "es")!!;
            //callLocalApi("es")
            messageview.text = localdata.Resources[0].Data[0].Value
            messageview1.text = localdata.Resources[0].Data[2].Value
            messageview2.text = localdata.Resources[0].Data[3].Value
            messageview3.text = localdata.Resources[0].Data[4].Value
            messageview4.text = localdata.Resources[0].Data[6].Value
          /* val  resources = context.getResources();
            messageview.setText(resources.getString(R.string.language));
            messageview1.setText(resources.getString(R.string.answer1));*/
        }

       /* btnodia.setOnClickListener{
            context = LocaleHelper.setLocale(this, "or");
            val  resources = context.getResources();
            messageview.setText(resources.getString(R.string.language));
            messageview1.setText(resources.getString(R.string.answer1));
        }*/


    }

    private fun callLocalApi() {
        val dialog= Dialog(this)
        dialog.setContentView(R.layout.loader_dialog)
        dialog.window?.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window?.setGravity(Gravity.CENTER)
        dialog.show()
        lifecycleScope.launch{
            val apiInterface= ApiInterface.create().getLocalData()
            Log.e("LangaugeDemo",apiInterface.toString())
            withContext(Dispatchers.Main) {
                dialog.dismiss()
                if (apiInterface.isSuccessful) {
                    val localdata = apiInterface.body()!!
                    val editor = sharedPreferences.edit()
                    val gson = Gson()
                    val json = gson.toJson(localdata)
                    editor.putString("LanguageData",json)
                    editor.apply()

                }


            }
        }
    }
}

