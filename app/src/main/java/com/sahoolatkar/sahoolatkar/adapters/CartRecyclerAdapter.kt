package com.sahoolatkar.sahoolatkar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.models.CartProduct
import com.sahoolatkar.sahoolatkar.ui.MainActivity
import com.squareup.picasso.Picasso

class CartRecyclerAdapter(val mainActivity: MainActivity, val products: MutableList<CartProduct>) :
    RecyclerView.Adapter<CartRecyclerAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvProductName = itemView.findViewById<TextView>(R.id.tvProductName)
        var tvPrice = itemView.findViewById<TextView>(R.id.tvPrice)
        var tvQty = itemView.findViewById<TextView>(R.id.tvQty)
        var tvRemove = itemView.findViewById<TextView>(R.id.tvRemove)
        var ivProductImg = itemView.findViewById<ImageView>(R.id.ivProductImg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mainActivity).inflate(R.layout.layout_cart_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cartProduct = products[position]

        holder.tvProductName.text = cartProduct.product.name
        holder.tvPrice.text = cartProduct.product.price
        holder.tvQty.text = cartProduct.quantity.toString()
        Picasso.get().load(cartProduct.product.images[0].src).into(holder.ivProductImg)

        holder.tvRemove.setOnClickListener {
            products.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, products.size)
            mainActivity.removeCartItem()
        }
    }
}