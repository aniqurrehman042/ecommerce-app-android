package com.sahoolatkar.sahoolatkar.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.api_models.product.ProductApiModel
import com.squareup.picasso.Picasso

class CartRecyclerAdapter(val activity: Activity, val products: MutableList<ProductApiModel>) :
    RecyclerView.Adapter<CartRecyclerAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvProductName = itemView.findViewById<TextView>(R.id.tvProductName)
        var tvPrice = itemView.findViewById<TextView>(R.id.tvPrice)
        var tvRemove = itemView.findViewById<TextView>(R.id.tvRemove)
        var ivProductImg = itemView.findViewById<ImageView>(R.id.ivProductImg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(activity).inflate(R.layout.layout_cart_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]

        holder.tvProductName.text = product.name
        holder.tvPrice.text = product.price
        Picasso.get().load(product.images[0].src).into(holder.ivProductImg)

        holder.tvRemove.setOnClickListener {
            products.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, products.size)
        }
    }
}