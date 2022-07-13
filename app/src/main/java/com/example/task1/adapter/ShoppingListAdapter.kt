package com.example.task1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.task1.Interface.AddListClickInterface
import com.example.task1.R
import com.example.task1.model.MyCartInfo


class ShoppingListAdapter (private val cartInfoList: ArrayList<MyCartInfo>, private val addListClickInterface: AddListClickInterface):
    RecyclerView.Adapter<ShoppingListAdapter.ShoppingViewHolder>() {
    class ShoppingViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView)  {
        val shoppingTitleTVID:TextView=ItemView.findViewById(R.id.shoppingTitleTVID)
        val shoppingProductTVID:TextView=ItemView.findViewById(R.id.shoppingProductTVID)
        val shoppingIVID:ImageView=ItemView.findViewById(R.id.shoppingIVID)
        val deleteIVID:ImageView=ItemView.findViewById(R.id.deleteIVID)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListAdapter.ShoppingViewHolder {
     val view:View=LayoutInflater.from(parent.context).inflate(R.layout.shopping_list_item,parent,false)
        return ShoppingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingListAdapter.ShoppingViewHolder, position: Int) {
        val ItemsViewModel = cartInfoList[position]
        holder.shoppingTitleTVID.text=ItemsViewModel.Title
        holder.shoppingProductTVID.text=ItemsViewModel.ProductName
        Glide.with(holder.itemView.getContext()).load(ItemsViewModel.ImagePath)
            .into(holder.shoppingIVID);
        holder.deleteIVID.setOnClickListener{
        addListClickInterface.addlist(position,false)

        }

    }

    override fun getItemCount(): Int {
        return cartInfoList.size
    }

    private fun deleteMyEducations(id: String, position: Int) {

    }
}