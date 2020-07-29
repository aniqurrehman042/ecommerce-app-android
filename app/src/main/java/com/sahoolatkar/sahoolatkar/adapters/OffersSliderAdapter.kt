package com.sahoolatkar.sahoolatkar.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asksira.loopingviewpager.LoopingPagerAdapter
import com.makeramen.roundedimageview.RoundedImageView
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.api_models.product.ProductApiModel
import com.sahoolatkar.sahoolatkar.models.SliderItemModel
import com.squareup.picasso.Picasso

class OffersSliderAdapter(
    context: Context,
    var offers: List<ProductApiModel>,
    isInfinite: Boolean
) : LoopingPagerAdapter<ProductApiModel>(context, offers, isInfinite) {

    //This method will be triggered if the item View has not been inflated before.
    override fun inflateView(
        viewType: Int,
        container: ViewGroup,
        listPosition: Int
    ): View {
        return LayoutInflater.from(context)
            .inflate(R.layout.layout_home_top_slider_item, container, false)
    }

    //Bind your data with your item View here.
    //You can assume convertView will not be null here.
    override fun bindView(
        convertView: View,
        listPosition: Int,
        viewType: Int
    ) {
        Picasso.get().load(offers[listPosition].images[0].src)
            .into(convertView.findViewById<RoundedImageView>(R.id.ivSliderImg))
    }
}