package com.example.task1

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task1.Interface.AddListClickInterface
import com.example.task1.adapter.MyDealsAdapter
import com.example.task1.model.Special1
import com.example.task1.webapi.ApiInterface
import kotlinx.android.synthetic.main.fragment_my_deals.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CollapseHeaderDemo : AppCompatActivity(),AddListClickInterface {

    private lateinit var showdealsRVID:RecyclerView
    private lateinit var layoutmanager:LinearLayoutManager

    private lateinit var myList:List<Special1>
    private lateinit var dealsAdapter: MyDealsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collapse_header_demo)

        showdealsRVID=findViewById(R.id.showdealsRVID)


        callapi()

        layoutmanager=GridLayoutManager(this,2)
        showdealsRVID.layoutManager=layoutmanager



    }

    private fun callapi() {
        val dialog= Dialog(this)
        dialog.setContentView(R.layout.loader_dialog)
        dialog.window?.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window?.setGravity(Gravity.CENTER)
        dialog.show()
        lifecycleScope.launch{
            dialog.dismiss()
            val apiInterface=
                ApiInterface.create().getDeals(10001,"2fe16d86-c4ac-4980-84e6-d49d72c5987b",0)
            Log.e("request==>",apiInterface.toString())
            withContext(Dispatchers.Main) {
                if (apiInterface.isSuccessful) {
                    myList=apiInterface.body()!!.Specials
                    dealsAdapter = MyDealsAdapter(myList,this@CollapseHeaderDemo)
                    showdealsRVID.adapter = dealsAdapter

                }

            }
        }
    }

    override fun addlist(position: Int, isInCart: Boolean) {
        TODO("Not yet implemented")
    }
}