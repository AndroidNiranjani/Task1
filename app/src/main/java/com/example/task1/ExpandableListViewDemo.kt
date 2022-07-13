package com.example.task1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ExpandableListView
import androidx.lifecycle.lifecycleScope
import com.example.task1.adapter.ExpandableDemoAdapter
import com.example.task1.model.ExpandableRequestModel
import com.example.task1.model.Item
import com.example.task1.model.OrderItem
import com.example.task1.webapi.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ExpandableListViewDemo : AppCompatActivity() {

    private lateinit var expandable_list:ExpandableListView
    private lateinit var header: List<OrderItem>
    private lateinit var child: HashMap<String,List<Item>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expandable_list_view_demo)

        expandable_list=findViewById(R.id.expandable_list)
        getDataApi()

    }

    private fun getDataApi() {
        child= HashMap()
        val requestmodel=ExpandableRequestModel("northscott1","pickerapp","Android","0","44276524227","19298","22","91", "aisle","30","4d4a0609ada8437a9ba57a67c8d14bab","1.0.29")
        lifecycleScope.launch {
            val apiInterface = ApiInterface.create().getExpandableListItems(requestmodel)
            Log.e("api=>",apiInterface.toString())
            withContext(Dispatchers.Main){
                if(apiInterface.isSuccessful){
                    header=apiInterface.body()!!.data.order_item_list
                    header.forEach(){
                        if(it.items!=null){
                            it.items.forEach{ j->
                                child[it.toString()] = listOf(j)
                                Log.e("loop==>",j.toString())
                            }
                        }
                    }
                     /*  child[header[1].toString()]= listOf(header[1].items[0])
                        child[header[1].toString()]= listOf(header[1].items[1])
                        child[header[1].toString()]= listOf(header[1].items[0])
                        child[header[2].toString()]= listOf(header[2].items[1])
                        child[header[2].toString()]= listOf(header[2].items[0])*/


                    expandable_list.setAdapter(ExpandableDemoAdapter(this@ExpandableListViewDemo,header,child))
                }
            }
        }
    }
}




