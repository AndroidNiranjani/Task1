package com.example.task1.fragment

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.task1.Interface.AddListClickInterface
import com.example.task1.R
import com.example.task1.adapter.MyDealsAdapter
import com.example.task1.model.SaveRequestModel
import com.example.task1.model.Special1
import com.example.task1.utils.NetworkHelper
import com.example.task1.webapi.ApiInterface
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_coupons.*
import kotlinx.android.synthetic.main.fragment_my_deals.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*


class MyDealsFragment : Fragment(),AddListClickInterface{
    // TODO: Rename and change types of parameters
    private lateinit var myList:List<Special1>
    private lateinit var dealsAdapter: MyDealsAdapter
    private lateinit var swipeID: SwipeRefreshLayout
    private lateinit var notConnectedLLID: LinearLayout
    private lateinit var dataCSID: ConstraintLayout

    private lateinit var coordinator2:CoordinatorLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view:View= inflater.inflate(R.layout.fragment_my_deals, container, false)
        val dealsRV:RecyclerView=view.findViewById(R.id.dealsRV)
         coordinator2=view.findViewById(R.id.coordinator2)
        swipeID=view.findViewById(R.id.swipeID)
        notConnectedLLID=view.findViewById(R.id.notConnectedLLID)
        dataCSID=view.findViewById(R.id.dataCSID)

        dealsRV.layoutManager = GridLayoutManager(activity, 2)

        checkingNetwork()

        val refreshListener = SwipeRefreshLayout.OnRefreshListener {

            swipeID.isRefreshing = false
            shuffleItems()
        }
        swipeID.setOnRefreshListener(refreshListener)

            return view
        }

    private fun shuffleItems() {
        val connection:Boolean=isNetworkAvailable();
        if(connection){
            dealsAdapter= MyDealsAdapter(myList,this)
            dealsRV.adapter=dealsAdapter
            dealsAdapter.notifyDataSetChanged()
            dataCSID.visibility=View.VISIBLE
            notConnectedLLID.visibility=View.GONE
        } else{
            val snackbar = Snackbar
                .make(coordinator2, "No Internet Connection", Snackbar.LENGTH_LONG)
            snackbar.show()
            dataCSID.visibility=View.GONE
            notConnectedLLID.visibility=View.VISIBLE
            //myList.isEmpty()
        }
       // Collections.shuffle(myList)


    }

    fun isNetworkAvailable(): Boolean {
        val connectivityManager = this.requireContext()
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null
    }


    private fun checkingNetwork() {
        if (NetworkHelper.isNetworkConnected(requireContext())) {
            callapi()
        } else {
            //Toast.makeText(context,"Check your Internet Connectivity",Toast.LENGTH_LONG).show()
            val snackbar =
                Snackbar.make(coordinator2, "No Internet Connection!", Snackbar.LENGTH_LONG)
                    .setAction("RETRY", View.OnClickListener {
                    })
            snackbar.setActionTextColor(Color.RED);
            val view = snackbar.view
            val textView =
                view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
            textView.setTextColor(Color.YELLOW);
            snackbar.show();
        }

    }

    private fun callapi() {
        lifecycleScope.launch{
            val apiInterface=
                ApiInterface.create().getDeals(10001,"2fe16d86-c4ac-4980-84e6-d49d72c5987b",0)
           Log.e("request==>",apiInterface.toString())
            withContext(Dispatchers.Main) {
                swipeID.isRefreshing=false
                if (apiInterface.isSuccessful) {
                    myList=apiInterface.body()!!.Specials
                    dealsAdapter = MyDealsAdapter(myList,this@MyDealsFragment)
                    dealsRV.adapter = dealsAdapter
                }
            }
        }
    }

    override fun addlist(position:Int,isInCart:Boolean) {
        val ItemsViewModel = myList[position]
        if(isInCart) {
            val progressdialog = ProgressDialog(activity)
            progressdialog.show()
            val saveRequestModel = SaveRequestModel(
                ItemsViewModel.Amount,
                ItemsViewModel.ProductCategoryId,
                ItemsViewModel.Details,
                ItemsViewModel.ExpiresOn,
                ItemsViewModel.IsInNCRImpressions,
                "44923808010",
                ItemsViewModel.NCRPromotionCode,
                ItemsViewModel.SSNewsId,
                ItemsViewModel.ProductName,
                ItemsViewModel.Title,
                "",
                "Niranjani",
                "2fe16d86-c4ac-4980-84e6-d49d72c5987b",
                ItemsViewModel.ValidFromDate
            )
            lifecycleScope.launch(Dispatchers.IO) {
                val response = ApiInterface.create().savecart(saveRequestModel)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        progressdialog.dismiss()
                        Log.e("res==>", response.body()!!.toString())
                        ItemsViewModel.IsInCart = isInCart
                        dealsAdapter.notifyDataSetChanged()
                        Toast.makeText(context, "Added to cart sucessfully", Toast.LENGTH_SHORT)
                            .show()

                    }
                }
            }
        }else{
            val progressdialog = ProgressDialog(activity)
            progressdialog.show()
            lifecycleScope.launch(Dispatchers.IO){
                val response= ApiInterface.create().removecart(myList[position].SSNewsId,"2fe16d86-c4ac-4980-84e6-d49d72c5987b")
                Log.e("res==>",response.toString())
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        Log.e("res==>",response.body()!!.toString())
                        progressdialog.dismiss()
                        ItemsViewModel.IsInCart = isInCart
                        dealsAdapter.notifyDataSetChanged()
                        Toast.makeText(context,"Removed from cart sucessfully",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }





}