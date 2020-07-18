package com.sahoolatkar.sahoolatkar.ui.fragments

import ProductApiModel
import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.adapters.ProductsAdapter
import com.sahoolatkar.sahoolatkar.api_callbacks.IGetAllProductsCallback
import com.sahoolatkar.sahoolatkar.models.ProductModel
import com.sahoolatkar.sahoolatkar.ui.MainActivity
import com.sahoolatkar.sahoolatkar.api_utils.SahoolatKarApiUtils
import kotlinx.android.synthetic.main.fragment_products_catalog.*

class ProductsCatalogFragment : Fragment() {

    private lateinit var mainActivity: MainActivity

    val args: ProductsCatalogFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainActivity = requireActivity() as MainActivity
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
//        products.add(ProductModel("Mobile", "Best Mobile in the world", "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg", 20000f, 5))
//        products.add(ProductModel("Mobile", "Best Mobile in the world", "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg", 20000f, 5))
//        products.add(ProductModel("Mobile", "Best Mobile in the world", "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg", 20000f, 5))
//        products.add(ProductModel("Mobile", "Best Mobile in the world", "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg", 20000f, 5))
//        products.add(ProductModel("Mobile", "Best Mobile in the world", "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg", 20000f, 5))
//        products.add(ProductModel("Mobile", "Best Mobile in the world", "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg", 20000f, 5))
//        products.add(ProductModel("Mobile", "Best Mobile in the world", "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg", 20000f, 5))
//        products.add(ProductModel("Mobile", "Best Mobile in the world", "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg", 20000f, 5))
//        products.add(ProductModel("Mobile", "Best Mobile in the world", "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg", 20000f, 5))
//        products.add(ProductModel("Mobile", "Best Mobile in the world", "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg", 20000f, 5))

        var productsList: MutableList<ProductApiModel>? = null

        SahoolatKarApiUtils.getProductsByCategory(mainActivity, args.category, object : IGetAllProductsCallback {
            override fun onGetProducts(productItems: MutableList<ProductApiModel>) {
                productsList = productItems

                if (productsList!!.isNotEmpty()) {
                    for (productItem in productsList!!) {
                        products.add(ProductModel(productItem.name, productItem.description, productItem.images[0].src, 0f, 0))
                    }
                }

                rvProducts.layoutManager = GridLayoutManager(context, 2)
                val productsAdapter = ProductsAdapter(activity as Activity, products, getString(R.string.fragment_products_catalog))
                rvProducts.adapter = productsAdapter
            }
        })


    }
}