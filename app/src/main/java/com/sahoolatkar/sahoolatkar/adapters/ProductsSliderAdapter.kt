package com.sahoolatkar.sahoolatkar.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.asksira.loopingviewpager.LoopingPagerAdapter
import com.makeramen.roundedimageview.RoundedImageView
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.models.ProductModel
import com.sahoolatkar.sahoolatkar.models.SliderItemModel
import com.squareup.picasso.Picasso

class ProductsSliderAdapter(
    context: Context,
    var products: ArrayList<ProductModel>,
    isInfinite: Boolean
) : LoopingPagerAdapter<ProductModel>(context, products, isInfinite) {

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
    //Below is just an example in the demo app.
    //You can assume convertView will not be null here.
    //You may also consider using a ViewHolder pattern.
    override fun bindView(
        convertView: View,
        listPosition: Int,
        viewType: Int
    ) {
        Picasso.get().load(products[listPosition].imgUrl)
            .into(convertView.findViewById<ImageView>(R.id.ivProductImg))

        Picasso.get().load("https://upload.wikimedia.org/wikipedia/commons/thumb/5/52/Heart_icon_red_hollow.svg/1083px-Heart_icon_red_hollow.svg.png").into(convertView.findViewById<ImageView>(R.id.ivLike))
        convertView.findViewById<TextView>(R.id.tvProductName).text = products[listPosition].name
        convertView.findViewById<TextView>(R.id.tvProductDesc).text = products[listPosition].desc
        convertView.findViewById<TextView>(R.id.tvPrice).text = products[listPosition].price.toInt().toString()
        convertView.findViewById<TextView>(R.id.tvDiscount).text = products[listPosition].discount.toString()
    }
}