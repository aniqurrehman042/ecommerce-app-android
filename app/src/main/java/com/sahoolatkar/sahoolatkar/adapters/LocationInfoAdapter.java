package com.sahoolatkar.sahoolatkar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sahoolatkar.sahoolatkar.R;
import com.sahoolatkar.sahoolatkar.models.LocationInfoModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LocationInfoAdapter extends RecyclerView.Adapter<LocationInfoAdapter.MyView> {
    private List<LocationInfoModel> infoList;
    public LocationInfoAdapter(List<LocationInfoModel>infoList)
    {
        this.infoList = infoList;
    }
    public class MyView extends RecyclerView.ViewHolder
    {
        TextView countryName ;
        TextView language ;
     //Once drawable are available only then
     //  ImageView countryFlag
        public MyView(@NonNull View itemView) {
            super(itemView);
            countryName = itemView.findViewById(R.id.tvCompanyName);
            language    = itemView.findViewById(R.id.tvLanguage);
        //    countryFlag = itemView.findViewById(R.id.ivCountryFlag);
        }
    }
    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_location_info_item, parent, false);
        // return itemView
        return new MyView(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
        holder.countryName.setText(infoList.get(position).getCountryName());
        holder.language.setText(infoList.get(position).getLanguageName());
        //Picasso.get().load(infoList.get(position).getImageUrl()).into(holder.countryFlag);
    }
    @Override
    public int getItemCount() {
        return infoList.size();
    }
}
