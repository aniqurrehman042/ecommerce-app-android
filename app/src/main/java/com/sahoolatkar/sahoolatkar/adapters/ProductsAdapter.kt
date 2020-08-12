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
import com.sahoolatkar.sahoolatkar.api_models.product.Product
import com.sahoolatkar.sahoolatkar.globals.GlobalVariables
import com.sahoolatkar.sahoolatkar.ui.MainActivity
import com.sahoolatkar.sahoolatkar.ui.fragments.HomeFragmentDirections
import com.sahoolatkar.sahoolatkar.ui.fragments.ProductsCatalogFragmentDirections
import com.sahoolatkar.sahoolatkar.ui.fragments.WishListFragmentDirections
import com.sahoolatkar.sahoolatkar.utils.ViewUtils
import com.squareup.picasso.Picasso

class ProductsAdapter(
    val mainActivity: MainActivity,
    val products: List<Product>,
    private val parentFragment: String
) :
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivProductImg: ImageView = itemView.findViewById(R.id.ivProductImg)
        val ivLike: ImageView = itemView.findViewById(R.id.ivLike)
        val tvProductName: TextView = itemView.findViewById(R.id.tvProductName)
        val tvCurrency: TextView = itemView.findViewById(R.id.tvCurrency)
        val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        val tvStartingFrom: TextView = itemView.findViewById(R.id.tvStartingFrom)
        val tvInstallments: TextView = itemView.findViewById(R.id.tvInstallments)
        val tvPerMonth: TextView = itemView.findViewById(R.id.tvPerMonth)
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

        holder.tvProductName.text = product.name
        if (product.meta_data[0].value.value[0] == '{' && !product.variations.isNullOrEmpty()) {
            ViewUtils.showView(holder.tvStartingFrom)
            ViewUtils.showView(holder.tvInstallments)
            ViewUtils.showView(holder.tvPerMonth)
        } else {
            ViewUtils.hideView(holder.tvStartingFrom)
            ViewUtils.hideView(holder.tvInstallments)
            ViewUtils.hideView(holder.tvPerMonth)
        }
        holder.tvPrice.text = product.price
        if (!product.images.isNullOrEmpty()) {
            Picasso.get()
                .load(product.images[0].src)
                .into(holder.ivProductImg)
        } else {
            holder.ivProductImg.setImageResource(0)
        }

        holder.ivLike.setOnClickListener {
            if (product.wishListed) {
                holder.ivLike.setImageResource(R.drawable.ic_like_off)
                product.wishListed = false
                mainActivity.removeProductFromWishList(product)
                if (parentFragment == GlobalVariables.WISH_LIST_FRAGMENT) {
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, products.size)
                    mainActivity.onRemoveCartItem()
                }
            } else {
                holder.ivLike.setImageResource(R.drawable.ic_like_on)
                product.wishListed = true
                mainActivity.addProductToWishList(product)
            }
        }

        if (product.wishListed) {
            holder.ivLike.setImageResource(R.drawable.ic_like_on)
        } else {
            holder.ivLike.setImageResource(R.drawable.ic_like_off)
        }

        holder.clProduct.setOnClickListener {
            startProductDetailsFragment(product)
        }
    }

    private fun startProductDetailsFragment(product: Product) {
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