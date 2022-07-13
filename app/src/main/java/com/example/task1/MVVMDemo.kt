package com.example.task1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task1.Interface.AddListClickInterface
import com.example.task1.Interface.OpenDetailsClickInterface
import com.example.task1.adapter.ExampleAdapter
import com.example.task1.repository.CouponsListRepository
import com.example.task1.viewmodel.MainViewModel
import com.example.task1.viewmodel.ViewModelFactory
import com.example.task1.webapi.ApiInterface

class MVVMDemo : AppCompatActivity(),AddListClickInterface,OpenDetailsClickInterface {

    lateinit var mainViewModel: MainViewModel

    private lateinit var listRVID:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvvmdemo)

        listRVID=findViewById(R.id.listRVID)

        listRVID.layoutManager=LinearLayoutManager(this)

        val apiInterface=ApiInterface.create()
        val repository=CouponsListRepository(apiInterface)
        mainViewModel=ViewModelProvider(this,ViewModelFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.coupons.observe(this, Observer {
           Log.d("data==>",it.Specials.toString())
            val adapter = ExampleAdapter(it.Specials,this,this)
            listRVID.adapter=adapter
        })
    }

    override fun addlist(position: Int, isInCart: Boolean) {
        TODO("Not yet implemented")
    }

    override fun opendetails(position: Int, isInCart: Boolean) {
        TODO("Not yet implemented")
    }
}