package com.sahoolatkar.sahoolatkar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.adapters.DealsAdapter.DealsView
import com.sahoolatkar.sahoolatkar.models.DealModel
import com.squareup.picasso.Picasso

class DealsAdapter(var listDeals: List<DealModel>) :
    RecyclerView.Adapter<DealsView>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealsView {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_deals_item, parent, false)
        return DealsView(itemView)
    }

    override fun onBindViewHolder(holder: DealsView, position: Int) {
        Picasso.get().load(listDeals[position].imageUrl).into(holder.DealBanner)
    }

    override fun getItemCount(): Int {
        return listDeals.size
    }

    inner class DealsView(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var DealBanner: ImageView

        init {
            DealBanner = itemView.findViewById(R.id.ivDealBanner)
        }
    }

}