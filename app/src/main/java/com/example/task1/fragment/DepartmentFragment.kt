package com.example.task1.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task1.webapi.ApiInterface
import com.example.task1.R
import com.example.task1.utils.NetworkHelper
import kotlinx.coroutines.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


class DepartmentFragment : Fragment() {
    private lateinit var recycler_view:RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View= inflater.inflate(R.layout.fragment_department, container, false)
        recycler_view=view.findViewById(R.id.depart_RVID);

        recycler_view.layoutManager= GridLayoutManager(activity,2)
       // callapi()
if(NetworkHelper.isNetworkConnected(requireContext())) {
    apicoroutine()
}else{
    Toast.makeText(context,"Please check your Network Connection",Toast.LENGTH_LONG).show()
}
       /* val data = ArrayList<ExampleModel>()
        for (i in 1..20) {
            data.add(ExampleModel(R.drawable.logo, "Title " + i))
        }
        val adapter= DepatmentAdapter(data)
        recycler_view.adapter=adapter
        */
        return view
    }

    private fun apicoroutine() {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ApiInterface.BASE_URL)
            .build()
            .create(ApiInterface::class.java)

            lifecycleScope.launch (Dispatchers.IO) {
            val response=retrofit.getDepartment()
            withContext(Dispatchers.Main) {
            if(response.isSuccessful) {
                /*val adapter = DepatmentAdapter(response.body()!!.GalleryItems)
                recycler_view.adapter = adapter*/
            }
            }

        }
    }
}