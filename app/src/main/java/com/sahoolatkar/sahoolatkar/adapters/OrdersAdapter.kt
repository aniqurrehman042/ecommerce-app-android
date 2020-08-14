package com.sahoolatkar.sahoolatkar.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.api_models.order.Order

class OrdersAdapter(
    private val context: Context,
    private val orders: List<Order>
) : RecyclerView.Adapter<OrdersAdapter.MyView>() {
    inner class MyView(view: View) : RecyclerView.ViewHolder(view) {
        // Text View
        var tvOrderNo: TextView = view.findViewById(R.id.tvOrderNo)
        var paymentMethod: TextView = view.findViewById(R.id.tvMethodName)
        var total: TextView = view.findViewById(R.id.tvTotalAmount)
        var rvLineItems: RecyclerView = view.findViewById(R.id.rvLineItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersAdapter.MyView {

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
        return orders.size
    }

    override fun onBindViewHolder(holder: OrdersAdapter.MyView, position: Int) {
        holder.tvOrderNo.text = orders[position].id.toString()
        holder.paymentMethod.text = orders[position].payment_method_title
        holder.total.text = orders[position].total.toInt().toString()

        holder.rvLineItems.layoutManager = LinearLayoutManager(context)
        holder.rvLineItems.adapter = LineItemsRecyclerAdapter(orders[position].line_items)
    }
}