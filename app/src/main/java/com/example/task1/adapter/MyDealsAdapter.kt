package com.example.task1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.task1.Interface.AddListClickInterface
import com.example.task1.R
import com.example.task1.model.Special1


class MyDealsAdapter(private val dealslist:List<Special1>,private val addListClickInterface1: AddListClickInterface):
    RecyclerView.Adapter<MyDealsAdapter.DealsViewholder>() {

    class DealsViewholder (ItemView: View) : RecyclerView.ViewHolder(ItemView){
            val logoIVID=itemView.findViewById<ImageView>(R.id.logoIVID)
            val discountTVID=itemView.findViewById<TextView>(R.id.discountTVID)
            val addListBTNID=itemView.findViewById<Button>(R.id.addListBTNID)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealsViewholder {
        val view:View=LayoutInflater.from(parent.context).inflate(R.layout.deals_list_item,parent,false)
        return DealsViewholder(view)
    }

    override fun onBindViewHolder(holder: DealsViewholder, position: Int) {
        val ItemsViewModel = dealslist[position]

            holder.discountTVID.text=ItemsViewModel.Title
            Glide.with(holder.itemView.context).load(ItemsViewModel.ImagePath)
                .into(holder.logoIVID)

        holder.addListBTNID.setOnClickListener{

            if (holder.addListBTNID.text.equals("Add to List")) {
                addListClickInterface1.addlist(position,true)
            } else{
                addListClickInterface1.addlist(position,false)
                //removeClickInterface1.removecart(ItemsViewModel.SSNewsId)
            }

        }

        if(ItemsViewModel.IsInCart)
            holder.addListBTNID.setText("Remove From Cart")
        else
            holder.addListBTNID.setText("Add to List")



    }

    override fun getItemCount(): Int {
        return dealslist.size
    }
}