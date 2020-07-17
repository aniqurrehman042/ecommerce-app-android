package com.sahoolatkar.sahoolatkar.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.models.CategoryModel


class SmallCategoriesRecyclerAdapter(var activity: Activity, var horizontalItems: MutableList<CategoryModel>) :
    RecyclerView.Adapter<SmallCategoriesRecyclerAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivItemImg = itemView.findViewById<ImageView>(R.id.ivItemImg)
        val tvItemName = itemView.findViewById<TextView>(R.id.tvItemName)
        val llMain = itemView.findViewById<LinearLayout>(R.id.llMain)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_small_category_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return horizontalItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val horizontalItem = horizontalItems[position]

//        Picasso.get().load(horizontalItem.imageUrl)
//            .into(holder.ivItemImg)

        holder.ivItemImg.setImageResource(horizontalItem.imageId)

        holder.tvItemName.text = horizontalItem.name

        holder.llMain.setOnClickListener {
            startProductsCatalogFragment()
        }
    }

    private fun startProductsCatalogFragment() {
        Navigation.findNavController(activity.findViewById(R.id.navHostFragment)).navigate(R.id.action_home_to_productsCatalogFragment)
    }
}