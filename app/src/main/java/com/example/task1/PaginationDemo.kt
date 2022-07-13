package com.example.task1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task1.adapter.PaginationAdapter
import com.example.task1.model.EndLessScrollModel
import com.example.task1.model.Punche
import com.example.task1.webapi.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PaginationDemo : AppCompatActivity() {

    lateinit var paginationlist:MutableList<EndLessScrollModel>
    lateinit var layoutManager: LinearLayoutManager
    lateinit var scrollRVID:RecyclerView
    lateinit var adapter: PaginationAdapter
    var page=1
    var isLoading = false
    val limit =10


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagination_demo)

        scrollRVID=findViewById(R.id.scrollRVID)
        layoutManager=LinearLayoutManager(this)
        scrollRVID.layoutManager=layoutManager

    }

   fun getPage(){
       val start:Int = (page-1) * limit
       val end:Int = (page) * limit
       for (i:Int in start..end){

       }
   }
}