package com.example.task1.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.task1.Interface.AddListClickInterface
import com.example.task1.Interface.OpenDetailsClickInterface

import com.example.task1.R
import com.example.task1.model.Special
import de.hdodenhof.circleimageview.CircleImageView


class ExampleAdapter(private val examplelist: List<Special>,private val addListClickInterface: AddListClickInterface,private val openDetailsClickInterface: OpenDetailsClickInterface):


    RecyclerView.Adapter<ExampleAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.coupouns_list_item, parent, false)
        return MyViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ExampleAdapter.MyViewHolder, position: Int) {
        val ItemsViewModel = examplelist[position]

        // sets the image to the imageview from our itemHolder class
        //holder.peoplesIVID.setImageResource(ItemsViewModel.Specials.get(position).ImagePath)
        val uri: String? = ItemsViewModel.ImagePath
        Glide.with(holder.itemView.getContext()).load(uri)
            .into(holder.peoplesIVID);

        // sets the text to the textview from our itemHolder class

        /*holder.parentCLID.animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.animation)*/

        holder.parentCLID.setOnClickListener{

          openDetailsClickInterface.opendetails(position,ItemsViewModel.IsInCart)
           /* val context=holder.itemView.context
            val intent= Intent(context,DetailsAcivity::class.java)

           val special=Special(ItemsViewModel.Amount,ItemsViewModel.CouponLimit,ItemsViewModel.CustomerID,ItemsViewModel.CustomerName,
           ItemsViewModel.DepartmentName,ItemsViewModel.Details,ItemsViewModel.DiscountAmount,ItemsViewModel.ExpiresOn,ItemsViewModel.ImagePath,
               ItemsViewModel.IsDiscountPercentage,ItemsViewModel.IsFeatured,ItemsViewModel.IsInCart,ItemsViewModel.IsInNCRImpressions,ItemsViewModel.IsRedeem
                       ,ItemsViewModel.IsWeeklyCoupons,ItemsViewModel.NCRPromotionCode,ItemsViewModel.NewsCategoryDescription,ItemsViewModel.NewsCategoryId,
           ItemsViewModel.PLUCode,ItemsViewModel.ProductCategoryId,ItemsViewModel.ProductName,ItemsViewModel.RedeemDate,ItemsViewModel.SSNewsId,ItemsViewModel.SendNotification,ItemsViewModel.SpecialPrice
                       ,ItemsViewModel.Title,ItemsViewModel.ValidFromDate,ItemsViewModel.productId)


            intent.putExtra("couponsData", special)
            context.startActivity(intent)*/
        }
        holder.nameTVID.text = ItemsViewModel.Title
        holder.pNameTVID.text = ItemsViewModel.ProductName
        holder.saveTVID.text = "SAVE  "+ ItemsViewModel.PLUCode

        holder.addListBtnID.setOnClickListener() {
            if (holder.addListBtnID.text.equals("Add to List")) {
                addListClickInterface.addlist(position,true)
            } else{
                addListClickInterface.addlist(position,false)
            }
        }

        if(ItemsViewModel.IsInCart){
            holder.addListBtnID.setText("Remove From Cart")
        }else{
            holder.addListBtnID.setText("Add to List")
        }


    }



    override fun getItemCount(): Int {
        return examplelist.size
    }
    class MyViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        val peoplesIVID: CircleImageView=itemView.findViewById(R.id.peoplesIVID);
        val nameTVID: TextView=itemView.findViewById(R.id.nameTVID);
        val parentCLID: ConstraintLayout=itemView.findViewById(R.id.parentCLID);
        val pNameTVID: TextView=itemView.findViewById(R.id.pNameTVID);
        val saveTVID: TextView=itemView.findViewById(R.id.saveTVID);
        val addListBtnID: Button=itemView.findViewById(R.id.addListBtnID)
    }

}






