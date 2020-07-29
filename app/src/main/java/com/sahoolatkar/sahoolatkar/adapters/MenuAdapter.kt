package com.sahoolatkar.sahoolatkar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.models.MenuItemModel
import com.sahoolatkar.sahoolatkar.ui.MainActivity

class MenuAdapter(private val mainActivity: MainActivity, private val infoList: MutableList<MenuItemModel>) :
    RecyclerView.Adapter<MenuAdapter.MyView>() {

    inner class MyView(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var countryName: TextView = itemView.findViewById(R.id.tvTitle)
        var llMain: LinearLayout = itemView.findViewById(R.id.llMain)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyView {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_menu_item, parent, false)
        // return itemView
        return MyView(itemView)
    }

    override fun onBindViewHolder(
        holder: MyView,
        position: Int
    ) {
        holder.countryName.text = infoList[position].title
        if (infoList[position].title == "Wish List") {
            holder.llMain.setOnClickListener {
                Navigation.findNavController(mainActivity, R.id.navHostFragment).navigate(R.id.wishListFragment)
                mainActivity.closeMenu()
            }
        }
    }

    override fun getItemCount(): Int {
        return infoList.size
    }

}
