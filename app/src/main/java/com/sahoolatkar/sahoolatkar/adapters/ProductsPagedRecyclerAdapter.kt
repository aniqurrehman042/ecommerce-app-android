package com.sahoolatkar.sahoolatkar.adapters

import ProductApiModel
import android.app.Activity
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.diff_util_callbacks.ProductsDiffUtilCallback
import com.squareup.picasso.Picasso

class ProductsPagedRecyclerAdapter(val activity: Activity, val parentFragment: String) :
    PagedListAdapter<ProductApiModel, ProductsPagedRecyclerAdapter.ViewHolder>(
        ProductsDiffUtilCallback()
    ) {
    class ViewHolder(itemView: View, private val parentFragment: String, private val activity: Activity) : RecyclerView.ViewHolder(itemView) {
        fun bindProduct(product: ProductApiModel) {
            var liked = false

            tvOldCurrency.paintFlags =
                tvOldCurrency.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            tvOldPrice.paintFlags =
                tvOldCurrency.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

            tvProductName.text = product.name
            tvProductDesc.text = product.description
            tvPrice.text = product.price
            // tvDiscount.text = product.discount.toString()
            Picasso.get()
                .load(product.images[0].src)
                .into(ivProductImg)

            ivLike.setOnClickListener {
                liked = if (liked) {
                    ivLike.setImageResource(R.drawable.ic_like_off)
                    false
                } else {
                    ivLike.setImageResource(R.drawable.ic_like_on)
                    true
                }
            }

            clProduct.setOnClickListener {
                startProductDetailsFragment()
            }
        }

        private fun startProductDetailsFragment() {
            when (parentFragment) {
                activity.getString(R.string.fragment_home) -> {
                    Navigation.findNavController(activity.findViewById(R.id.navHostFragment))
                        .navigate(R.id.action_home_to_productDetailsFragment)
                }

                activity.getString(R.string.fragment_products_catalog) -> {
                    Navigation.findNavController(activity.findViewById(R.id.navHostFragment))
                        .navigate(R.id.action_productsCatalogFragment_to_productDetailsFragment)
                }
            }
        }

        val ivProductImg = itemView.findViewById<ImageView>(R.id.ivProductImg)
        val ivLike = itemView.findViewById<ImageView>(R.id.ivLike)
        val tvProductName = itemView.findViewById<TextView>(R.id.tvProductName)
        val tvProductDesc = itemView.findViewById<TextView>(R.id.tvProductDesc)
        val tvCurrency = itemView.findViewById<TextView>(R.id.tvCurrency)
        val tvPrice = itemView.findViewById<TextView>(R.id.tvPrice)
        val tvOldCurrency = itemView.findViewById<TextView>(R.id.tvOldCurrency)
        val tvOldPrice = itemView.findViewById<TextView>(R.id.tvOldPrice)

        //  val tvDiscount = itemView.findViewById<TextView>(R.id.tvDiscount)
        val llOldPrice = itemView.findViewById<LinearLayout>(R.id.llOldPrice)

        // val llDiscount = itemView.findViewById<LinearLayout>(R.id.llDiscount)
        val clProduct = itemView.findViewById<ConstraintLayout>(R.id.clProduct)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_product_item, parent, false)
        return ViewHolder(view, parentFragment, activity)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bindProduct(it)
        }
    }
}