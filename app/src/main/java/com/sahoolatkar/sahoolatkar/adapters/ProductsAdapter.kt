package com.sahoolatkar.sahoolatkar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.api_models.product.ProductApiModel
import com.sahoolatkar.sahoolatkar.globals.GlobalVariables
import com.sahoolatkar.sahoolatkar.ui.MainActivity
import com.sahoolatkar.sahoolatkar.ui.fragments.HomeFragmentDirections
import com.sahoolatkar.sahoolatkar.ui.fragments.ProductsCatalogFragmentDirections
import com.sahoolatkar.sahoolatkar.ui.fragments.WishListFragmentDirections
import com.squareup.picasso.Picasso

class ProductsAdapter(
    val mainActivity: MainActivity,
    val products: List<ProductApiModel>,
    private val parentFragment: String
) :
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivProductImg: ImageView = itemView.findViewById(R.id.ivProductImg)
        val ivLike: ImageView = itemView.findViewById(R.id.ivLike)
        val tvProductName: TextView = itemView.findViewById(R.id.tvProductName)
        val tvProductDesc: TextView = itemView.findViewById(R.id.tvProductDesc)
        val tvCurrency: TextView = itemView.findViewById(R.id.tvCurrency)
        val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        val clProduct: ConstraintLayout = itemView.findViewById(R.id.clProduct)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_product_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        var liked = false

        holder.tvProductName.text = product.name
        holder.tvProductDesc.text = product.description
        holder.tvPrice.text = product.price
        Picasso.get()
            .load(product.images[0].src)
            .into(holder.ivProductImg)

        if (parentFragment == GlobalVariables.WISH_LIST_FRAGMENT) {
            holder.ivLike.visibility = View.GONE
        }

        holder.ivLike.setOnClickListener {
            liked = if (liked) {
                holder.ivLike.setImageResource(R.drawable.ic_like_off)
                mainActivity.removeProductFromWishList(product)
                false
            } else {
                holder.ivLike.setImageResource(R.drawable.ic_like_on)
                mainActivity.addProductToWishList(product)
                true
            }
        }

        holder.clProduct.setOnClickListener {
            startProductDetailsFragment(product)
        }
    }

    private fun startProductDetailsFragment(product: ProductApiModel) {
        when (parentFragment) {
            GlobalVariables.HOME_FRAGMENT -> {
                Navigation.findNavController(mainActivity.findViewById(R.id.navHostFragment))
                    .navigate(HomeFragmentDirections.actionHomeToProductDetailsFragment(product))
            }

            GlobalVariables.PRODUCT_CATALOG_FRAGMENT -> {
                Navigation.findNavController(mainActivity.findViewById(R.id.navHostFragment))
                    .navigate(ProductsCatalogFragmentDirections.actionProductsCatalogFragmentToProductDetailsFragment(product))
            }

            GlobalVariables.WISH_LIST_FRAGMENT -> {
                Navigation.findNavController(mainActivity.findViewById(R.id.navHostFragment))
                    .navigate(WishListFragmentDirections.actionWishListFragmentToProductDetailsFragment(product))
            }
        }
    }
}