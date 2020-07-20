package com.sahoolatkar.sahoolatkar.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sahoolatkar.sahoolatkar.R;
import com.sahoolatkar.sahoolatkar.models.DealModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DealsAdapter extends RecyclerView.Adapter <DealsAdapter.DealsView> {

    List<DealModel> listDeals;
    public DealsAdapter(List<DealModel> listDeals)
    {
        this.listDeals=listDeals;
    }
    @NonNull
    @Override
    public DealsView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_deals_item,parent,false);
        return new DealsView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DealsView holder, int position) {
        Picasso.get().load(listDeals.get(position).getImageUrl()).into(holder.DealBanner);
    }

    @Override
    public int getItemCount() {
        return listDeals.size();
    }

    public class DealsView extends RecyclerView.ViewHolder {
        ImageView DealBanner;
        public DealsView(@NonNull View itemView) {
            super(itemView);
            DealBanner=itemView.findViewById(R.id.ivDealBanner);
        }
    }
}
