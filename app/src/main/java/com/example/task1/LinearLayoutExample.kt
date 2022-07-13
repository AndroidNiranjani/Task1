package com.example.task1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.task1.adapter.CustomSpinnerAdapter
import com.example.task1.model.CountryData

class LinearLayoutExample : AppCompatActivity() {

    var radioGroup: RadioGroup? = null
    lateinit var radioButton: RadioButton
    private lateinit var button: Button
    private lateinit var preferences: SharedPreferences
    private lateinit var locationSPID: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linear_layout_example)

        preferences=getSharedPreferences("SHARED_PREF",Context.MODE_PRIVATE)


        val logoutBTNID:Button=findViewById(R.id.logoutBTNID)
        logoutBTNID.setOnClickListener{
            val editor:SharedPreferences.Editor=preferences.edit()
            editor.clear()
            editor.apply()

            val intent:Intent=Intent(this,MainActivity::class.java)
            startActivity(intent)

        }

        /*val tremsTVID: TextView = findViewById(R.id.termsID)
        val storeETID: TextView = findViewById(R.id.storeETID)

        storeETID.setOnClickListener{
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog_spinner, null)
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)

            val  mAlertDialog = mBuilder.show()
            locationSPID=mDialogView.findViewById(R.id.locationSPID)
            setCustomAdapterSpinner()
            //locationSPID.setOnItemClickListener()

        }

        radioGroup = findViewById(R.id.radioGroup)

        val intSelectButton: Int = radioGroup!!.checkedRadioButtonId
        radioButton = findViewById(intSelectButton)

        val word: Spannable = SpannableString("By Tapping" + " SUBMIT "+ "I agree")

        word.setSpan(
            ForegroundColorSpan(Color.WHITE),
            0,
            word.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        tremsTVID.setText(word)
        val wordTwo: Spannable = SpannableString(" T&C and Privacy Policy")

        wordTwo.setSpan(
            ForegroundColorSpan(Color.BLUE),
            0,
            wordTwo.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        tremsTVID.append(wordTwo)*/
    }

    private fun setCustomAdapterSpinner() {
        val country_list = arrayListOf<CountryData>()

        country_list.add(CountryData("Lock", R.drawable.ic_outline_lock_24))
        country_list.add(CountryData("Preview", R.drawable.ic_outline_preview_24))
        country_list.add(CountryData("Share", R.drawable.ic_baseline_share_24))
        country_list.add(CountryData("Link", R.drawable.ic_baseline_link_24))
        country_list.add(CountryData("Logo", R.drawable.logo))

        val adapter = CustomSpinnerAdapter(
            this,
            country_list
        )

        locationSPID.adapter = adapter

        locationSPID.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {
                Toast.makeText(this@LinearLayoutExample, "" + (parent?.getItemAtPosition(pos) as CountryData).nameStr, Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        })
    }
}