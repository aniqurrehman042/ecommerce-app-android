package com.sahoolatkar.sahoolatkar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import com.asksira.loopingviewpager.LoopingPagerAdapter
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.api_models.product.ProductApiModel
import com.sahoolatkar.sahoolatkar.globals.GlobalVariables
import com.sahoolatkar.sahoolatkar.ui.MainActivity
import com.sahoolatkar.sahoolatkar.ui.fragments.HomeFragmentDirections
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_product_item.view.*

class ProductsSliderAdapter(
    val mainActivity: MainActivity,
    private val products: List<ProductApiModel>,
    isInfinite: Boolean
) : LoopingPagerAdapter<ProductApiModel>(mainActivity, products, isInfinite) {

    //This method will be triggered if the item View has not been inflated before.
    override fun inflateView(
        viewType: Int,
        container: ViewGroup,
        listPosition: Int
    ): View {
        return LayoutInflater.from(context)
            .inflate(R.layout.layout_product_item, container, false)
    }

    //Bind your data with your item View here.
    //You can assume convertView will not be null here.
    override fun bindView(
        convertView: View,
        listPosition: Int,
        viewType: Int
    ) {

        val product = products[listPosition]

        if (!product.images.isNullOrEmpty()) {
            Picasso.get().load(product.images[0].src)
                .into(convertView.findViewById<ImageView>(R.id.ivProductImg))
        }

        convertView.findViewById<TextView>(R.id.tvProductName).text = product.name
        convertView.findViewById<TextView>(R.id.tvProductDesc).text =
            product.description
        convertView.findViewById<TextView>(R.id.tvPrice).text =
            product.price
        convertView.findViewById<ConstraintLayout>(R.id.clProduct).setOnClickListener {
            startProductDetailsFragment(product)
        }


        convertView.ivLike.setOnClickListener {
            if (product.wishListed) {
                convertView.findViewById<ImageView>(R.id.ivLike).ivLike.setImageResource(R.drawable.ic_like_off)
                product.wishListed = false
                mainActivity.removeProductFromWishList(product)
            } else {
                convertView.findViewById<ImageView>(R.id.ivLike).ivLike.setImageResource(R.drawable.ic_like_on)
                product.wishListed = true
                mainActivity.addProductToWishList(product)
            }
        }
    }

    private fun startProductDetailsFragment(product: ProductApiModel) {
        Navigation.findNavController(mainActivity.findViewById(R.id.navHostFragment))
            .navigate(HomeFragmentDirections.actionHomeToProductDetailsFragment(product))
    }
}