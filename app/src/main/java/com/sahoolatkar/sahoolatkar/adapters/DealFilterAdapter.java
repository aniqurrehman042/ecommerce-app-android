package com.sahoolatkar.sahoolatkar.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sahoolatkar.sahoolatkar.R;
import com.sahoolatkar.sahoolatkar.models.CategoryModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DealFilterAdapter extends RecyclerView.Adapter<DealFilterAdapter.DealFilter> {
    List<CategoryModel> listCategory;
    public DealFilterAdapter(List<CategoryModel> listCategory)
    {
        listCategory = this.listCategory;
    }

    @NonNull
    @Override
    public DealFilter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_deal_filter_item, parent, false);

        // return itemView
        return new DealFilter(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DealFilter holder, int position) {
    holder.CategoryName.setText(listCategory.get(position).getName());
    Picasso.get().load(listCategory.get(position).getImageUrl()).into(holder.CategoryImage);
    }

    @Override
    public int getItemCount() {
        return listCategory.size();
    }

    public class DealFilter extends RecyclerView.ViewHolder {
        ImageView CategoryImage;
        TextView CategoryName;
        public DealFilter(@NonNull View itemView) {
            super(itemView);
            CategoryImage = itemView.findViewById(R.id.ivCategoryImage);
            CategoryName = itemView.findViewById(R.id.tvCategoryName);
        }
    }
}
