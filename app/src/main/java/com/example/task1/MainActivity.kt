package com.example.task1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.task1.model.LoginRequestModel
import com.example.task1.utils.NetworkHelper
import com.example.task1.webapi.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    var isremembered=false
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var checkBox: CheckBox
    private lateinit var strEmail: String
    private lateinit var strPassword: String
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences=getSharedPreferences("SHARED_PREF",Context.MODE_PRIVATE)
        isremembered=sharedPreferences.getBoolean("CHECKBOX",false)
        val tremsTVID:TextView = findViewById(R.id.termsTVID)
        val forgetTVID:TextView = findViewById(R.id.forgetTVID)


        etEmail=findViewById(R.id.etEmail)
        etPassword=findViewById(R.id.etPassword)
        checkBox=findViewById(R.id.checkBox)
        val loginBtn:TextView = findViewById(R.id.loginBtn)

        if(isremembered){
            val intent:Intent=Intent(this,LinearLayoutExample::class.java)
            startActivity(intent)
            finish()
        }

        loginBtn.setOnClickListener {
        strEmail=etEmail.text.toString()
        strPassword=etPassword.text.toString()
            val checked:Boolean=checkBox.isChecked

                if(strEmail.isEmpty()){
                    etEmail.error="Email is Requried"
                } else if (!strEmail.matches(emailPattern.toRegex())) {
                    etEmail.error="Invalid Email Address"
                }else if(strPassword.isEmpty()){
                    etPassword.error="Password is Requried"
                } /*else if(strPassword.length<=6){
                    etPassword.error="Password should have atleast 6 letters"
                }*/
                else {
                    val editor: SharedPreferences.Editor = sharedPreferences.edit()
                    editor.putString("EMAIL", strEmail)
                    editor.putString("PASSWORD", strPassword)
                    editor.putBoolean("CHECKBOX", checked)
                    editor.apply()
                    if(NetworkHelper.isNetworkConnected(this)) {
                        loginapi()
                    }else{
                        Toast.makeText(this,"Please check your internet connection",Toast.LENGTH_LONG).show()
                    }
                }
        }
        val word: Spannable = SpannableString("By Tapping Continue as guest account, I agree to")
        word.setSpan(
            ForegroundColorSpan(Color.WHITE),
            0,
            word.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        tremsTVID.setText(word)
        val wordTwo: Spannable = SpannableString("\n T&C and Privacy color")

        wordTwo.setSpan(
            ForegroundColorSpan(Color.BLUE),
            0,
            wordTwo.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        tremsTVID.append(wordTwo)

        //callApi()
    }

    private fun loginapi() {
        val loginRequestModel=LoginRequestModel("1","3","csQzy1lARzeByqyeWAivY5:APA91bF37z0UylvPmv0cf09nmzlgzazJYyxemLEnF1FB-b_qlkEpktrMh6is2nPcupSHZ8GCUlArQpnHJoCg0FoSUemAUEfCJYJVuoQr2Uz34-lDjJq53qq50iaLUDihV_AhncNLv4G1","3.4.10","POCO F1 (beryllium)","android","4.9.112-perf-g526fc33(v11.0.5.0.pejmixm",strPassword,strEmail)
        lifecycleScope.launch(Dispatchers.IO) {
            val apiInterface= ApiInterface.create().loginapi(loginRequestModel)
            Log.e("request==>",apiInterface.toString())
            withContext(Dispatchers.Main) {
                if (apiInterface.isSuccessful) {
                    Log.e("response==>", apiInterface.body().toString())
                    if(apiInterface.body()!!.CustomerId==0) {
                        Toast.makeText(applicationContext,apiInterface.body()!!.ErrorMesasge.ErrorDetails,Toast.LENGTH_LONG).show()
                    }else{
                        startActivity(Intent(this@MainActivity, LinearLayoutExample::class.java))
                        val user_token:String=apiInterface.body()!!.UserToken

                        val editor: SharedPreferences.Editor = sharedPreferences.edit()

                        editor.putString("user_token",user_token)
                        val token=sharedPreferences.getString("user_token",null)
                        Log.e("token==>",token.toString())
                        editor.apply()
                    }
                }
            }
        }
    }

    /* fun callApi() {
         val apiInterface = ApiInterface.create().getCoupouns()
         apiInterface.enqueue(object : Callback<List<Special>> {
             override fun onResponse(
                 call: Call<List<com.example.task1.model.Special>>?,
                 response: Response<List<com.example.task1.model.Special>>?
             ) {

                 if (response?.body() != null) {

                     val adapter = ExampleAdapter(response?.body()!!)
                     recycler_view.adapter = adapter
                 }
             }

             override fun onFailure(call: Call<List<com.example.task1.model.Special>>?, t: Throwable?) {

             }
         })
    }
*/

}






