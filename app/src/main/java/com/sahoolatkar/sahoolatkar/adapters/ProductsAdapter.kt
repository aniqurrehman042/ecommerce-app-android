package com.sahoolatkar.sahoolatkar.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.models.ProductModel
import com.squareup.picasso.Picasso

class ProductsAdapter(val products: MutableList<ProductModel>) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivProductImg = itemView.findViewById<ImageView>(R.id.ivProductImg)
        val ivLike = itemView.findViewById<ImageView>(R.id.ivLike)
        val tvProductName = itemView.findViewById<TextView>(R.id.tvProductName)
        val tvProductDesc = itemView.findViewById<TextView>(R.id.tvProductDesc)
        val tvCurrency = itemView.findViewById<TextView>(R.id.tvCurrency)
        val tvPrice = itemView.findViewById<TextView>(R.id.tvPrice)
        val tvOldCurrency = itemView.findViewById<TextView>(R.id.tvOldCurrency)
        val tvOldPrice = itemView.findViewById<TextView>(R.id.tvOldPrice)
        val tvDiscount = itemView.findViewById<TextView>(R.id.tvDiscount)
        val llOldPrice = itemView.findViewById<LinearLayout>(R.id.llOldPrice)
        val llDiscount = itemView.findViewById<LinearLayout>(R.id.llDiscount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.layout_product_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]

        holder.tvOldCurrency.paintFlags = holder.tvOldCurrency.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        holder.tvOldPrice.paintFlags = holder.tvOldCurrency.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

        holder.tvProductName.text = product.name
        holder.tvProductDesc.text = product.desc
        holder.tvPrice.text = product.price.toString()
        holder.tvDiscount.text = product.discount.toString()
        Picasso.get().load("https://sukena.pk/wp-content/uploads/2018/11/0005702_orient-microwave-oven-om-36axxgeg930axx-1-600x900.png").into(holder.ivProductImg)
        Picasso.get().load("https://upload.wikimedia.org/wikipedia/commons/thumb/5/52/Heart_icon_red_hollow.svg/1083px-Heart_icon_red_hollow.svg.png").into(holder.ivLike)
    }
}