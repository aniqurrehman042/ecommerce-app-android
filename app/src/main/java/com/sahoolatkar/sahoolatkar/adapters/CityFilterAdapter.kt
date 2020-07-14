package com.sahoolatkar.sahoolatkar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.models.CityFilterModel
import com.sahoolatkar.sahoolatkar.ui.fragments.StoreLocatorFragment


class CityFilterAdapter(
    private val cityList: MutableList<CityFilterModel>,
    private val storeLocatorFragment: StoreLocatorFragment
) :
    RecyclerView.Adapter<CityFilterAdapter.MyView>() {

    // View Holder class which
    // extends RecyclerView.ViewHolder
    inner class MyView(view: View) : RecyclerView.ViewHolder(view) {
        // Text View
        var cityName: TextView = view.findViewById(R.id.tvCityName)
    }

    // Constructor for adapter class
    // which takes a list of String type
    // Override onCreateViewHolder which deals
    // with the inflation of the card layout
    // as an item for the RecyclerView.
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyView {

        // Inflate item.xml using LayoutInflator
        val itemView: View = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.layout_city_filter_item,
                parent,
                false
            )

        // return itemView
        return MyView(itemView)
    }

    // Override onBindViewHolder which deals
    // with the setting of different data
    // and methods related to clicks on
    // particular items of the RecyclerView.
    override fun onBindViewHolder(
        holder: MyView,
        position: Int
    ) {

        // Set the text of each item of
        // Recycler view with the list items
        holder.cityName.text = cityList[position].cityName
        holder.cityName.setOnClickListener {
            storeLocatorFragment.moveToLocation(cityList[position].cityLoc, 11f)
        }
    }

    // Override getItemCount which Returns
    // the length of the RecyclerView.
    override fun getItemCount(): Int {
        return cityList.size
    }
}