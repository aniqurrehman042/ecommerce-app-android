package com.sahoolatkar.sahoolatkar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.adapters.DealFilterAdapter.DealFilter
import com.sahoolatkar.sahoolatkar.models.CategoryModel
import com.squareup.picasso.Picasso


class DealFilterAdapter(listCategory: List<CategoryModel>?) :
    RecyclerView.Adapter<DealFilter>() {
     var listCategory: List<CategoryModel>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealFilter {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_deal_filter_item, parent, false)

        // return itemView
        return DealFilter(itemView)
    }

    override fun onBindViewHolder(holder: DealFilter, position: Int) {
        holder.CategoryName.text = listCategory?.get(position)!!.name;
        Picasso.get().load(listCategory!![position]!!.imageUrl).into(holder.CategoryImage)
    }

    override fun getItemCount(): Int {
        return listCategory!!.size
    }

    inner class DealFilter(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var CategoryImage: ImageView ;
        var CategoryName: TextView

        init {
            CategoryImage = itemView.findViewById(R.id.ivCategoryImage)
            CategoryName = itemView.findViewById(R.id.tvCategoryName)
        }
    }

    init {
        var listCategory = listCategory
        listCategory = this.listCategory
    }
}