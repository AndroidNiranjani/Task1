package com.example.task1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.task1.PaginationDemo
import com.example.task1.R
import com.example.task1.model.EndLessScrollModel

class PaginationAdapter(val activity:PaginationDemo):RecyclerView.Adapter<PaginationAdapter.PaginationViewHolder>() {

    val paginationlist:MutableList<EndLessScrollModel> = ArrayList()


    class PaginationViewHolder(v: View):RecyclerView.ViewHolder(v){
        val employeeTVID:TextView=v.findViewById(R.id.employeeTVID)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaginationViewHolder {
        return PaginationViewHolder(LayoutInflater.from(activity).inflate(R.layout.pagination_item_list,parent,false))
    }

    override fun onBindViewHolder(holder: PaginationViewHolder, position: Int) {
      holder.employeeTVID.text=paginationlist[position].Punches[position].EmployeeID.toString()
    }

    override fun getItemCount(): Int {
       return paginationlist.size
    }
}