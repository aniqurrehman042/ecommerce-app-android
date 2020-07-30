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
        var tvProductName: TextView = itemView.findViewById(R.id.tvProductName)
        var tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        var tvQty: TextView = itemView.findViewById(R.id.tvQty)
        var tvPlus: TextView = itemView.findViewById(R.id.tvPlus)
        var tvMinus: TextView = itemView.findViewById(R.id.tvMinus)
        var tvRemove: TextView = itemView.findViewById(R.id.tvRemove)
        var ivProductImg: ImageView = itemView.findViewById(R.id.ivProductImg)
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

        holder.tvPlus.setOnClickListener{
            addQty(holder.tvQty)
            cartProduct.quantity++
        }
        holder.tvMinus.setOnClickListener{
            minusQty(holder.tvQty)
            cartProduct.quantity--
        }

        holder.tvRemove.setOnClickListener {
            products.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, products.size)
            mainActivity.onRemoveCartItem()
            mainActivity.blinkCart()
        }
    }

    private fun minusQty(tvQty: TextView)
    {
        var num :Int = (tvQty.text).toString().toInt()
        if(num>1)
            num--
        tvQty.text  = num.toString()
    }
    private fun addQty(tvQty: TextView)
    {
        var num :Int = (tvQty.text).toString().toInt()
        num++
        tvQty.text  = num.toString()
    }
}