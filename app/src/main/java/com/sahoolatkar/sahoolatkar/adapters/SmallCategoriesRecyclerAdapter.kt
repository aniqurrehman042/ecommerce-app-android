package com.sahoolatkar.sahoolatkar.adapters

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.models.CategoryModel
import com.sahoolatkar.sahoolatkar.ui.ProductsActivity
import com.squareup.picasso.Picasso


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

        Picasso.get().load(horizontalItem.imageUrl)
            .into(holder.ivItemImg)

        holder.tvItemName.text = horizontalItem.name

        holder.llMain.setOnClickListener {
            startProductsActivity(horizontalItem)
        }
    }

    private fun startProductsActivity(category: CategoryModel) {
        activity.startActivity(Intent(activity, ProductsActivity::class.java))
    }
}