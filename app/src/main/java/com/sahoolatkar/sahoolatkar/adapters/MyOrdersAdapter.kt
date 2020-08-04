package com.sahoolatkar.sahoolatkar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.models.CityFilterModel
import com.sahoolatkar.sahoolatkar.models.UserOrderModel
import com.sahoolatkar.sahoolatkar.ui.fragments.StoreLocatorFragment

class MyOrdersAdapter (
    private val orderList: MutableList<UserOrderModel>
) : RecyclerView.Adapter<MyOrdersAdapter.MyView>() {
    inner class MyView(view: View) : RecyclerView.ViewHolder(view) {
        // Text View
        var productName: TextView = view.findViewById(R.id.tvProductName)
        var subTotal   : TextView = view.findViewById(R.id.tvSubTotalAmount)
        var paymentMethod   : TextView = view.findViewById(R.id.tvMethodName)
        var total   : TextView = view.findViewById(R.id.tvTotalAmount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyOrdersAdapter.MyView {

        // Inflate item.xml using LayoutInflator
        val itemView: View = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.layout_order_item,
                parent,
                false
            )
        return MyView(itemView)
    }

    override fun getItemCount(): Int {
     return orderList.size
    }

    override fun onBindViewHolder(holder: MyOrdersAdapter.MyView, position: Int) {
        holder.productName.setText(orderList[position].productName)
        holder.subTotal.setText(orderList[position].subTotal)
        holder.paymentMethod.setText(orderList[position].paymentMethod)
        holder.total.setText(orderList[position].total)
    }
}