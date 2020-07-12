package com.sahoolatkar.sahoolatkar.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asksira.loopingviewpager.LoopingPagerAdapter
import com.makeramen.roundedimageview.RoundedImageView
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.models.SliderItemModel
import com.squareup.picasso.Picasso

class OffersSliderAdapter(
    context: Context,
    var offers: ArrayList<SliderItemModel>,
    isInfinite: Boolean
) : LoopingPagerAdapter<SliderItemModel>(context, offers, isInfinite) {

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
    //Below is just an example in the demo app.
    //You can assume convertView will not be null here.
    //You may also consider using a ViewHolder pattern.
    override fun bindView(
        convertView: View,
        listPosition: Int,
        viewType: Int
    ) {
        Picasso.get().load(offers[listPosition].imageUrl)
            .into(convertView.findViewById<RoundedImageView>(R.id.ivSliderImg))
    }
}