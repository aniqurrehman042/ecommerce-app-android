package com.sahoolatkar.sahoolatkar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.api_models.product.Attributes

class SpecificationsRecyclerAdapter(private val attributes: List<Attributes>) :
    RecyclerView.Adapter<SpecificationsRecyclerAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvSpecificationName: TextView = itemView.findViewById(R.id.tvSpecificationName)
        val tvSpecificationValue: TextView = itemView.findViewById(R.id.tvSpecificationValue)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_product_specifications_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return attributes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val attribute = attributes[position]

        holder.tvSpecificationName.text = attribute.name
        holder.tvSpecificationValue.text = attribute.options.joinToString(" ")
    }
}