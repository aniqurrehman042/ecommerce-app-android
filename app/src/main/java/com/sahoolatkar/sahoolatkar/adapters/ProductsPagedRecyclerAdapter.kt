package com.sahoolatkar.sahoolatkar.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.api_models.product.ProductApiModel
import com.sahoolatkar.sahoolatkar.diff_util_callbacks.ProductsDiffUtilCallback
import com.sahoolatkar.sahoolatkar.globals.GlobalVariables
import com.sahoolatkar.sahoolatkar.ui.fragments.HomeFragmentDirections
import com.sahoolatkar.sahoolatkar.ui.fragments.ProductsCatalogFragmentDirections
import com.squareup.picasso.Picasso

class ProductsPagedRecyclerAdapter(val activity: Activity, val parentFragment: String) :
    PagedListAdapter<ProductApiModel, ProductsPagedRecyclerAdapter.ViewHolder>(
        ProductsDiffUtilCallback()
    ) {
    class ViewHolder(itemView: View, private val parentFragment: String, private val activity: Activity) : RecyclerView.ViewHolder(itemView) {
        fun bindProduct(product: ProductApiModel) {
            var liked = false

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
                startProductDetailsFragment(product)
            }
        }

        private fun startProductDetailsFragment(product: ProductApiModel) {
            when (parentFragment) {
                GlobalVariables.HOME_FRAGMENT -> {
                    Navigation.findNavController(activity.findViewById(R.id.navHostFragment))
                        .navigate(HomeFragmentDirections.actionHomeToProductDetailsFragment(product))
                }

                GlobalVariables.PRODUCT_CATALOG_FRAGMENT -> {
                    Navigation.findNavController(activity.findViewById(R.id.navHostFragment))
                        .navigate(ProductsCatalogFragmentDirections.actionProductsCatalogFragmentToProductDetailsFragment(product))
                }
            }
        }

        private val ivProductImg: ImageView = itemView.findViewById(R.id.ivProductImg)
        private val ivLike: ImageView = itemView.findViewById(R.id.ivLike)
        private val tvProductName: TextView = itemView.findViewById(R.id.tvProductName)
        private val tvProductDesc: TextView = itemView.findViewById(R.id.tvProductDesc)
        private val tvCurrency: TextView = itemView.findViewById(R.id.tvCurrency)
        private val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        private val clProduct: ConstraintLayout = itemView.findViewById(R.id.clProduct)
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