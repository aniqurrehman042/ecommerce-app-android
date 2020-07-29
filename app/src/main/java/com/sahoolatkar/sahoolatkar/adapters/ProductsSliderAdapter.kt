package com.sahoolatkar.sahoolatkar.adapters

import android.app.Activity
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
import com.sahoolatkar.sahoolatkar.ui.fragments.HomeFragmentDirections
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_product_item.view.*

class ProductsSliderAdapter(
    val activity: Activity,
    private val products: List<ProductApiModel>,
    isInfinite: Boolean
) : LoopingPagerAdapter<ProductApiModel>(activity, products, isInfinite) {

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

        var liked = false

        Picasso.get().load(products[listPosition].images[0].src)
            .into(convertView.findViewById<ImageView>(R.id.ivProductImg))

        convertView.findViewById<TextView>(R.id.tvProductName).text = products[listPosition].name
        convertView.findViewById<TextView>(R.id.tvProductDesc).text = products[listPosition].description
        convertView.findViewById<TextView>(R.id.tvPrice).text =
            products[listPosition].price
        convertView.findViewById<ConstraintLayout>(R.id.clProduct).setOnClickListener {
            startProductDetailsFragment(products[listPosition])
        }


        convertView.ivLike.setOnClickListener {
            liked = if (liked) {
                convertView.ivLike.setImageResource(R.drawable.ic_like_off)
                false
            } else {
                convertView.ivLike.setImageResource(R.drawable.ic_like_on)
                true
            }
        }
    }

    private fun startProductDetailsFragment(product: ProductApiModel) {
        Navigation.findNavController(activity.findViewById(R.id.navHostFragment))
            .navigate(HomeFragmentDirections.actionHomeToProductDetailsFragment(product))
    }
}