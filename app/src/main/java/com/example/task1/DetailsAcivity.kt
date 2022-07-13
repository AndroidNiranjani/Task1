package com.example.task1

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.task1.fragment.CouponsFragment
import com.example.task1.model.SaveRequestModel
import com.example.task1.model.Special
import com.example.task1.webapi.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DetailsAcivity : AppCompatActivity() {
    private lateinit var titleTVID:TextView
    private lateinit var detailsTVID:TextView
    private lateinit var expirydateTVID:TextView
    private lateinit var disclaimerdetailsTVID:TextView
    private lateinit var productImgIVID:ImageView
    private lateinit var backIVID:ImageView
    private lateinit var addBTNID:Button
    private lateinit var saveTVID:TextView
     private  var isIncart:Boolean = false
     private  var position:Int = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        titleTVID=findViewById(R.id.titleTVID)
        detailsTVID=findViewById(R.id.detailsTVID)
        expirydateTVID=findViewById(R.id.expirydateTVID)
        disclaimerdetailsTVID=findViewById(R.id.disclaimerdetailsTVID)
        productImgIVID=findViewById(R.id.productImgIVID)
        saveTVID=findViewById(R.id.saveTVID)
        backIVID=findViewById(R.id.backIVID)
        addBTNID=findViewById(R.id.addBTNID)

        val special=intent.getParcelableExtra<Special>("couponsData")
        isIncart=intent.getBooleanExtra("isInCart",false)
        position=intent.getIntExtra("position",0)

        if (special != null) {
            titleTVID.text=special.Title
            expirydateTVID.text=special.ExpiresOn
            //disclaimerdetailsTVID.text=special.Details
            saveTVID.text="SAVE"+"\n"+ special.PLUCode
            Glide.with(this).load(special.ImagePath)
                .into(productImgIVID)

            val details=special.Details
            val list = details.split("||")
            detailsTVID.text=list[0]
            disclaimerdetailsTVID.text = list[1]


            backIVID.setOnClickListener{
                finish()
            }

            addBTNID.setOnClickListener {
                if (addBTNID.text.equals("Add to List")) {
                    savecartApi(special)
                }else{
                    removecartApi(special)
                }
            }
          if(special.IsInCart){
                addBTNID.setText("Remove From Cart")
            }else{
                addBTNID.setText("Add to List")
            }

        }


    }


    private fun removecartApi(special:Special) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.loader_dialog)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window?.setGravity(Gravity.CENTER)
        dialog.show()
        lifecycleScope.launch(Dispatchers.IO) {
            val response = ApiInterface.create().removecart(special.SSNewsId, "2fe16d86-c4ac-4980-84e6-d49d72c5987b")
            Log.e("req==>",response.toString())
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    dialog.dismiss()
                    isIncart=false
                    Log.e("res==>", response.body()!!.toString())
                    addBTNID.text = "Add to Cart"
                        Toast.makeText(this@DetailsAcivity, "Removed from cart sucessfully", Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    private fun savecartApi(  special: Special) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.loader_dialog)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setGravity(Gravity.CENTER)
        dialog.show()
        val saveRequestModel = SaveRequestModel(
            special.Amount,
            special.ProductCategoryId,
            special.Details,
            special.ExpiresOn,
            special.IsInNCRImpressions,
            "44923808010",
            special.NCRPromotionCode,
            special.SSNewsId,
            special.ProductName,
            special.Title,
            "",
            "Niranjani",
            "2fe16d86-c4ac-4980-84e6-d49d72c5987b",
            special.ValidFromDate
        )

        lifecycleScope.launch(Dispatchers.IO) {
            val response = ApiInterface.create().savecart(saveRequestModel)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    dialog.dismiss()
                    Log.e("res==>", response.body()!!.toString())
                    isIncart=true
                    addBTNID.text = "Remove from cart"
                    Toast.makeText(this@DetailsAcivity, "Add to cart sucessfully", Toast.LENGTH_LONG).show()
                }
            }

        }
    }



    override fun onBackPressed() {

        val intent = Intent(this,CouponsFragment::class.java)
        intent.putExtra("isInCart",isIncart)
        intent.putExtra("position",position)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

  /*  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        for (fragment in supportFragmentManager.fragments) {
            fragment.onActivityResult(requestCode, resultCode, data) }
    }*/

}