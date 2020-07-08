package com.sahoolatkar.sahoolatkar.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.adapters.ProductsAdapter
import com.sahoolatkar.sahoolatkar.models.ProductModel
import kotlinx.android.synthetic.main.activity_products.*

class ProductsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        init()
    }

    private fun init() {
        setUpProductsRecycler()
    }

    private fun setUpProductsRecycler() {
        val products: MutableList<ProductModel> = ArrayList()
        products.add(ProductModel("Microwave", "Best Microwave on Earth", 10000f, 10))
        products.add(ProductModel("Microwave", "Best Microwave on Earth", 10000f, 10))
        products.add(ProductModel("Microwave", "Best Microwave on Earth", 10000f, 10))
        products.add(ProductModel("Microwave", "Best Microwave on Earth", 10000f, 10))
        products.add(ProductModel("Microwave", "Best Microwave on Earth", 10000f, 10))
        products.add(ProductModel("Microwave", "Best Microwave on Earth", 10000f, 10))
        products.add(ProductModel("Microwave", "Best Microwave on Earth", 10000f, 10))
        products.add(ProductModel("Microwave", "Best Microwave on Earth", 10000f, 10))
        products.add(ProductModel("Microwave", "Best Microwave on Earth", 10000f, 10))
        products.add(ProductModel("Microwave", "Best Microwave on Earth", 10000f, 10))
        products.add(ProductModel("Microwave", "Best Microwave on Earth", 10000f, 10))
        val productsAdapter = ProductsAdapter(products)
        rvProducts.layoutManager = GridLayoutManager(this, 2)
        rvProducts.adapter = productsAdapter
    }
}