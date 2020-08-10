package com.sahoolatkar.sahoolatkar.diff_util_callbacks

import androidx.recyclerview.widget.DiffUtil
import com.sahoolatkar.sahoolatkar.api_models.product.Product

class ProductsDiffUtilCallback : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id && oldItem.name == newItem.name && oldItem.description == newItem.description
    }
}