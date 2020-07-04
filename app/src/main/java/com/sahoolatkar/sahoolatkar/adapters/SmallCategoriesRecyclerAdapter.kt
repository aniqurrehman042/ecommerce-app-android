package com.sahoolatkar.sahoolatkar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.models.CategoryModel

class SmallCategoriesRecyclerAdapter(var horizontalItems: MutableList<CategoryModel>) : RecyclerView.Adapter<SmallCategoriesRecyclerAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivItemImg = itemView.findViewById<ImageView>(R.id.ivItemImg)
        val tvItemName = itemView.findViewById<TextView>(R.id.tvItemName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_small_category_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return horizontalItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val horizontalItem = horizontalItems[position]

        Glide.with(holder.itemView)
            .load(horizontalItem.imageUrl)
            .fitCenter()
            .into(holder.ivItemImg)
        holder.tvItemName.text = horizontalItem.name
    }
}