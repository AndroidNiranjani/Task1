package com.example.task1.fragment

import android.app.Activity.RESULT_OK
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task1.adapter.ExampleAdapter
import com.example.task1.webapi.ApiInterface
import com.example.task1.DetailsAcivity
import com.example.task1.Interface.AddListClickInterface
import com.example.task1.Interface.OpenDetailsClickInterface
import com.example.task1.R
import com.example.task1.model.SaveRequestModel
import com.example.task1.model.Special
import com.example.task1.utils.NetworkHelper
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_coupons.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CouponsFragment : Fragment(),AddListClickInterface,OpenDetailsClickInterface {

    private lateinit var adapter:ExampleAdapter
    private lateinit var myList:List<Special>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view:View=inflater.inflate(R.layout.fragment_coupons, container, false)
        val recycler_view:RecyclerView=view.findViewById(R.id.recycler_view)
        val coordinator1:CoordinatorLayout=view.findViewById(R.id.coordinator1)
        recycler_view.layoutManager=LinearLayoutManager(activity)


        if(NetworkHelper.isNetworkConnected(requireContext())) {
            callApi()
        }else{
            val snackbar= Snackbar.make(coordinator1,"No Internet Connection!", Snackbar.LENGTH_LONG)
                .setAction("RETRY", View.OnClickListener {
                })

            snackbar.setActionTextColor(Color.RED);
            val view=snackbar.view
            val textView=view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
            textView.setTextColor(Color.YELLOW);
            snackbar.show();
        }
        return view
    }



   fun callApi() {
       val dialog=Dialog(requireContext())
       dialog.setContentView(R.layout.loader_dialog)
       dialog.window?.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)
       dialog.window?.setGravity(Gravity.CENTER)
       dialog.show()
       lifecycleScope.launch(Dispatchers.IO) {
           val response=
               ApiInterface.create().getCoupouns(0,"2fe16d86-c4ac-4980-84e6-d49d72c5987b",0)
          Log.e("requestt==>",response.toString())
           withContext(Dispatchers.Main) {
               if (response.isSuccessful) {
                   myList=response.body()!!.Specials
                   dialog.dismiss()
                   Log.e("res==>",response.body()!!.toString())
                    adapter = ExampleAdapter(myList,this@CouponsFragment,this@CouponsFragment)
                   recycler_view.adapter = adapter


               }
           }
       }

    }


    override fun addlist(position:Int,isInCart:Boolean) {
        val ItemsViewModel = myList[position]
        if(isInCart) {
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

            val dialog=Dialog(requireContext())
            dialog.setContentView(R.layout.loader_dialog)
            dialog.window?.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)
            dialog.window?.setGravity(Gravity.CENTER)
            dialog.show()
            lifecycleScope.launch(Dispatchers.IO) {
                val response = ApiInterface.create().savecart(saveRequestModel)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        dialog.dismiss()
                        Log.e("res==>", response.body()!!.toString())
                        ItemsViewModel.IsInCart = isInCart
                        Toast.makeText(context, "Add list sucessfully", Toast.LENGTH_LONG).show()

                        adapter.notifyDataSetChanged()

                    }
                }
            }

        }else{
            val dialog=Dialog(requireContext())
            dialog.setContentView(R.layout.loader_dialog)
            dialog.window?.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)
            dialog.window?.setGravity(Gravity.CENTER)
            dialog.show()
            lifecycleScope.launch(Dispatchers.IO){
                val response= ApiInterface.create().removecart(ItemsViewModel.SSNewsId,"2fe16d86-c4ac-4980-84e6-d49d72c5987b")
                Log.e("req==>",response.toString())
                withContext(Dispatchers.Main) {
                    dialog.dismiss()
                    if (response.isSuccessful) {
                        Log.e("res==>",response.body()!!.toString())
                        ItemsViewModel.IsInCart = isInCart
                        adapter.notifyDataSetChanged()
                        Toast.makeText(context,"Remove list sucessfully",Toast.LENGTH_LONG).show()

                    }
                }
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
            Log.e("result==>",resultCode.toString())
            if(resultCode== RESULT_OK ) {
                    //  val isIncart1=data!!.extras!!.getString("isInCart")
                    val isIncart = data!!.getBooleanExtra("isInCart", false)
                    val position = data!!.getIntExtra("position", 0)
                    Log.e("isIncart==>", isIncart.toString())
                addlist(position,isIncart)
        }
    }

    override fun opendetails(position: Int,isInCart: Boolean) {
        val intent=Intent(activity,DetailsAcivity::class.java)
        intent.putExtra("couponsData",myList[position])
        intent.putExtra("isInCart",isInCart)
        intent.putExtra("position",position)
        startActivityForResult(intent,1001)
    }
}



