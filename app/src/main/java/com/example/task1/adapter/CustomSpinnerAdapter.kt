package com.example.task1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.task1.model.CountryData
import com.example.task1.R
import kotlinx.android.synthetic.main.item_custom_spinner.view.*

class CustomSpinnerAdapter (ctx: Context, countries: ArrayList<CountryData>) : ArrayAdapter<CountryData>(ctx, 0, countries)  {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createItemView(position, convertView, parent);
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createItemView(position, convertView, parent);
    }

    fun createItemView(position: Int, recycledView: View?, parent: ViewGroup):View {
        val country = getItem(position)

        val view = recycledView ?: LayoutInflater.from(context).inflate(
            R.layout.item_custom_spinner,
            parent,
            false)

        country?.let {
            view.imageViewFlag.setImageResource(country.imgstr)
            view.textViewCountryName.text = country.nameStr
        }
        return view
    }


}