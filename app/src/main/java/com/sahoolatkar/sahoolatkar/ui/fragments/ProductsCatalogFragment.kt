package com.sahoolatkar.sahoolatkar.ui.fragments

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.adapters.ProductsAdapter
import com.sahoolatkar.sahoolatkar.models.ProductModel
import kotlinx.android.synthetic.main.fragment_products_catalog.*

class ProductsCatalogFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_products_catalog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        setUpProductsRecycler()
    }

    private fun setUpProductsRecycler() {
        val products: MutableList<ProductModel> = ArrayList()
        products.add(ProductModel("Washing Machine", "Best Washing Machine in the world", 20000f, 5))
        products.add(ProductModel("Washing Machine", "Best Washing Machine in the world", 20000f, 5))
        products.add(ProductModel("Washing Machine", "Best Washing Machine in the world", 20000f, 5))
        products.add(ProductModel("Washing Machine", "Best Washing Machine in the world", 20000f, 5))
        products.add(ProductModel("Washing Machine", "Best Washing Machine in the world", 20000f, 5))
        products.add(ProductModel("Washing Machine", "Best Washing Machine in the world", 20000f, 5))
        products.add(ProductModel("Washing Machine", "Best Washing Machine in the world", 20000f, 5))
        products.add(ProductModel("Washing Machine", "Best Washing Machine in the world", 20000f, 5))
        products.add(ProductModel("Washing Machine", "Best Washing Machine in the world", 20000f, 5))
        products.add(ProductModel("Washing Machine", "Best Washing Machine in the world", 20000f, 5))
        rvProducts.layoutManager = GridLayoutManager(context, 2)
        val productsAdapter: ProductsAdapter = ProductsAdapter(activity as Activity, products)
        rvProducts.adapter = productsAdapter
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProductsCatalogFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}