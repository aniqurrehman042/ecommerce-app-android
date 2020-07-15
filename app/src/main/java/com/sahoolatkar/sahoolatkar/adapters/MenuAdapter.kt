package com.sahoolatkar.sahoolatkar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.models.MenuItemModel

class MenuAdapter(private val infoList: MutableList<MenuItemModel>) :
    RecyclerView.Adapter<MenuAdapter.MyView>() {

    inner class MyView(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var countryName: TextView = itemView.findViewById(R.id.tvTitle)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyView {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_menu_item, parent, false)
        // return itemView
        return MyView(itemView)
    }

    override fun onBindViewHolder(
        holder: MyView,
        position: Int
    ) {
        holder.countryName.text = infoList[position].title
    }

    override fun getItemCount(): Int {
        return infoList.size
    }

}
