package com.example.task1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.task1.R
import com.example.task1.model.Item
import com.example.task1.model.OrderItem


class ExpandableAdapter(var context:Context,var header:MutableList<OrderItem>,var body:List<List<Item>>) : BaseExpandableListAdapter() {

    override fun getGroupCount(): Int {
       return header.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
       return this.body[groupPosition].size
    }

    override fun getGroup(groupPosition: Int): String {
        return header[groupPosition].name
    }

    override fun getChild(groupPosition: Int, childPosition: Int): String {
        return body[groupPosition][childPosition].name
    }

    override fun getGroupId(groupPosition: Int): Long {
       return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
       return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(groupPosition: Int, isExpandable: Boolean, convertView: View?, p3: ViewGroup?): View? {

        var convertView=convertView
        if(convertView==null){
            val inflater=context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.layout_item,null)
        }
        val title=convertView?.findViewById<TextView>(R.id.titleTVID)
        title?.text=getGroup(groupPosition)
        return convertView
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, p2: Boolean, convertView: View?, p4: ViewGroup?): View? {
        var convertView=convertView
        if(convertView==null){
            val inflater=context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.layout_group_item,null)
        }
        val subtitle=convertView?.findViewById<TextView>(R.id.subtitleTVID)
        //val subItemIVID=convertView?.findViewById<ImageView>(R.id.subItemIVID)
        subtitle?.text=getChild(groupPosition,childPosition)
        return convertView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}