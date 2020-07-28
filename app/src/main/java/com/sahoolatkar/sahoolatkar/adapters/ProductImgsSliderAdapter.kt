package com.sahoolatkar.sahoolatkar.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asksira.loopingviewpager.LoopingPagerAdapter
import com.makeramen.roundedimageview.RoundedImageView
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.api_models.product.Images
import com.squareup.picasso.Picasso

class ProductImgsSliderAdapter(
    context: Context,
    var productImgs: List<Images>,
    isInfinite: Boolean
) : LoopingPagerAdapter<Images>(context, productImgs, isInfinite) {
    override fun bindView(convertView: View, listPosition: Int, viewType: Int) {
        Picasso.get().load(productImgs[listPosition].src)
            .into(convertView.findViewById<RoundedImageView>(R.id.ivSliderImg))
    }

    override fun inflateView(viewType: Int, container: ViewGroup, listPosition: Int): View {
        return LayoutInflater.from(context)
            .inflate(R.layout.layout_home_top_slider_item, container, false)
    }

}