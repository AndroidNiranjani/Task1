package com.example.task1

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task1.adapter.ShoppingListAdapter
import com.example.task1.Interface.AddListClickInterface
import com.example.task1.model.MyCartInfo
import com.example.task1.utils.NetworkHelper
import com.example.task1.webapi.ApiInterface
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_shopping_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShoppingList : AppCompatActivity(),AddListClickInterface {

    private lateinit var shoppinListRVID:RecyclerView
    private lateinit var cartInfo: ArrayList<MyCartInfo>
    private lateinit var shoppingListAdapter: ShoppingListAdapter
    lateinit var sharedPreferences: SharedPreferences
    private lateinit var usertoken:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_list)

        sharedPreferences=getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        shoppinListRVID=findViewById(R.id.shoppinListRVID)

        shoppinListRVID.layoutManager=LinearLayoutManager(this)

        usertoken=sharedPreferences.getString("user_token",null).toString()
        Log.e("token==>",usertoken)

        if(NetworkHelper.isNetworkConnected(this)){
        if (usertoken != null) {
            callshoppingApi()
        }else{
            val snackbar= Snackbar.make(listLLID,"No Internet Connection!", Snackbar.LENGTH_LONG)
                .setAction("RETRY", View.OnClickListener {
                })

            snackbar.setActionTextColor(Color.RED);
            val view=snackbar.view
            val textView=view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
            textView.setTextColor(Color.YELLOW);
            snackbar.show();
        }
        }

    }

    private fun callshoppingApi() {

        val dialog= Dialog(this)
        dialog.setContentView(R.layout.loader_dialog)
        dialog.window?.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window?.setGravity(Gravity.CENTER)
        dialog.show()

        lifecycleScope.launch{
            val apiInterface= ApiInterface.create().getshoppingDetails("2fe16d86-c4ac-4980-84e6-d49d72c5987b")
            Log.e("shoppingReq==>",apiInterface.toString())
            withContext(Dispatchers.Main) {
                if (apiInterface.isSuccessful) {
                    dialog.dismiss()
                    cartInfo = apiInterface.body()!!.MyCartInfo
                    shoppingListAdapter = ShoppingListAdapter(cartInfo,this@ShoppingList)
                    shoppinListRVID.adapter = shoppingListAdapter
                }
            }
        }
    }

    override fun addlist(position: Int, isInCart: Boolean) {
       lifecycleScope.launch {
          val apiInterface= ApiInterface.create().removecart(cartInfo[position].OfferId,usertoken)
           Log.e("removereq==>",apiInterface.toString())
           withContext(Dispatchers.Main){
               if(apiInterface.isSuccessful){
                   cartInfo[position].AddToCart = isInCart
                   Toast.makeText(this@ShoppingList, "Removed from cart sucessfully", Toast.LENGTH_LONG).show()
                   cartInfo.removeAt(position)
                   shoppingListAdapter.notifyItemRemoved(position)
                   shoppingListAdapter.notifyItemRangeChanged(position, cartInfo.size)
                   shoppingListAdapter.notifyDataSetChanged()
               }
           }
       }
    }
}

