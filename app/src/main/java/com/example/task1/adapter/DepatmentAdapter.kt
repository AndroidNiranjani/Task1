package com.example.task1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.task1.R
import com.example.task1.model.GalleryItems

class DepatmentAdapter(private val list:List<GalleryItems>):
    RecyclerView.Adapter<DepatmentAdapter.Viewholder>() {
    class Viewholder (ItemView: View) : RecyclerView.ViewHolder(ItemView){
        val depatmentIVID: ImageView =itemView.findViewById(R.id.depatmentIVID);
        val depatmentnameTVID: TextView =itemView.findViewById(R.id.depatmentnameTVID);
        val parenFLID: FrameLayout =itemView.findViewById(R.id.parenFLID);

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepatmentAdapter.Viewholder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.department_list_item,parent,false)
        return Viewholder(view)
    }

    override fun onBindViewHolder(holder: DepatmentAdapter.Viewholder, position: Int) {
        val ItemsViewModel = list[position]
        Glide.with(holder.itemView.context).load(ItemsViewModel.URL).into(holder.depatmentIVID)
        holder.depatmentnameTVID.text = ItemsViewModel.PageNumber
        holder.parenFLID.animation=AnimationUtils.loadAnimation(holder.itemView.context,R.anim.animation)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}