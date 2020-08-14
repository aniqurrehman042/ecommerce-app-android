package com.sahoolatkar.sahoolatkar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.api_models.shared.LineItems

class LineItemsRecyclerAdapter(private val lineItemsList: List<LineItems>) : RecyclerView.Adapter<LineItemsRecyclerAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvProductName: TextView = itemView.findViewById(R.id.tvProductName)
        val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        val tvQty: TextView = itemView.findViewById(R.id.tvQty)
        val tvSubTotal: TextView = itemView.findViewById(R.id.tvSubTotal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_line_items_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lineItemsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val lineItems = lineItemsList[position]

        holder.tvProductName.text = lineItems.name
        holder.tvPrice.text = lineItems.price.toString()
        holder.tvSubTotal.text = lineItems.subtotal.toInt().toString()
        holder.tvQty.text = lineItems.quantity.toString()
    }

}