package com.example.task1.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.task1.R
import com.example.task1.model.Item
import com.example.task1.model.OrderItem

class ExpandableDemoAdapter internal constructor(private val context: Context,private val orderItemList:List<OrderItem>,private val subitemList:HashMap<String,List<Item>>):
    BaseExpandableListAdapter() {
    override fun getGroupCount(): Int {
        return orderItemList.size
    }

    override fun getChildrenCount(p0: Int): Int {
        return orderItemList[p0].items.size
    }

    override fun getGroup(p0: Int): String {
       return orderItemList[p0].name
    }

    override fun getChild(p0: Int, p1: Int): String {
        Log.e("list==>",orderItemList[p0].items[p1].name)
       return orderItemList[p0].items[p1].name

    }

    override fun getGroupId(p0: Int): Long {
       return p0.toLong()
    }

    override fun getChildId(p0: Int, p1: Int): Long {
      return p1.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(p0: Int, p1: Boolean, p2: View?, p3: ViewGroup?): View? {
        var convertView=p2

        if(convertView==null){
            val inflater=context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.layout_item,null)

        }
        val title=convertView?.findViewById<TextView>(R.id.titleTVID)
        title?.text= getGroup(p0)
        return convertView
    }

    override fun getChildView(p0: Int, p1: Int, p2: Boolean, p3: View?, p4: ViewGroup?): View? {
        var convertView=p3
        val topiclist=getChild(p0,p1)
        if(convertView==null){
            val inflater=context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.layout_group_item,null)
        }
        val subtitle=convertView?.findViewById<TextView>(R.id.subtitleTVID)
       // val subItemIVID=convertView?.findViewById<ImageView>(R.id.subItemIVID)
        subtitle?.text=topiclist

        return convertView
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }
}