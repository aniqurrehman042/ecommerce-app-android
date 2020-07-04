package com.sahoolatkar.sahoolatkar.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.models.SliderItemModel
import com.smarteist.autoimageslider.SliderViewAdapter


class SliderAdapter(val context: Context) :
    SliderViewAdapter<SliderAdapter.SliderAdapterVH>() {
    private var mSliderItemModels: MutableList<SliderItemModel> = ArrayList()
    fun renewItems(sliderItemModels: MutableList<SliderItemModel>) {
        mSliderItemModels = sliderItemModels
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        mSliderItemModels.removeAt(position)
        notifyDataSetChanged()
    }

    fun addItem(sliderItemModel: SliderItemModel) {
        mSliderItemModels.add(sliderItemModel)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val inflate: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_home_top_slider_item, parent, false)
        return SliderAdapterVH(inflate)
    }

    override fun onBindViewHolder(
        viewHolder: SliderAdapterVH,
        position: Int
    ) {
        val sliderItemModel: SliderItemModel = mSliderItemModels[position]
        Glide.with(viewHolder.itemView)
            .load(sliderItemModel.imageUrl)
            .fitCenter()
            .into(viewHolder.ivBannerImage)
        viewHolder.itemView.setOnClickListener {
                Toast.makeText(context, "This is item in position $position", Toast.LENGTH_SHORT)
                    .show()
            }
    }

    override fun getCount(): Int {
        //slider view count could be dynamic size
        return mSliderItemModels.size
    }

    inner class SliderAdapterVH(itemView: View) :
        ViewHolder(itemView) {
        var ivBannerImage: ImageView = itemView.findViewById(R.id.ivBannerImage)
    }
}