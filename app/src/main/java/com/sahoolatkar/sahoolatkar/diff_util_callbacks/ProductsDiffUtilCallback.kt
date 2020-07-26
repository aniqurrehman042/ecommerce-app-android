package com.sahoolatkar.sahoolatkar.diff_util_callbacks

import ProductApiModel
import androidx.recyclerview.widget.DiffUtil

class ProductsDiffUtilCallback : DiffUtil.ItemCallback<ProductApiModel>() {
    override fun areItemsTheSame(oldItem: ProductApiModel, newItem: ProductApiModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ProductApiModel, newItem: ProductApiModel): Boolean {
        return oldItem.id == newItem.id && oldItem.name == newItem.name && oldItem.description == newItem.description
    }
}