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
import kotlinx.android.synthetic.main.layout_product_item.view.*

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

        var liked = false

        Picasso.get().load(products[listPosition].imgUrl)
            .into(convertView.findViewById<ImageView>(R.id.ivProductImg))

        convertView.findViewById<TextView>(R.id.tvProductName).text = products[listPosition].name
        convertView.findViewById<TextView>(R.id.tvProductDesc).text = products[listPosition].desc
        convertView.findViewById<TextView>(R.id.tvPrice).text = products[listPosition].price.toInt().toString()
        convertView.findViewById<TextView>(R.id.tvDiscount).text = products[listPosition].discount.toString()

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
}