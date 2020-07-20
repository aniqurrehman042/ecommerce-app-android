package com.sahoolatkar.sahoolatkar.ui.fragments

import ProductApiModel
import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.adapters.ProductsAdapter
import com.sahoolatkar.sahoolatkar.api_callbacks.IGetAllProductsCallback
import com.sahoolatkar.sahoolatkar.models.ProductModel
import com.sahoolatkar.sahoolatkar.ui.MainActivity
import com.sahoolatkar.sahoolatkar.api_utils.SahoolatKarApiUtils
import com.sahoolatkar.sahoolatkar.utils.ViewUtils
import kotlinx.android.synthetic.main.fragment_products_catalog.*
import kotlinx.android.synthetic.main.layout_loader.*

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

        var productsList: MutableList<ProductApiModel>?

        showLoader("Loading Products...")

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
                hideLoader()
            }
        })


    }

    private fun showLoader(loadingText: String) {
        ViewUtils.showView(llLoader)
        tvLoadingMsg.text = loadingText
        disableUserInteraction()
    }

    private fun hideLoader() {
        ViewUtils.hideView(llLoader)
        tvLoadingMsg.text = "Loading..."
        enableUserInteraction()
    }

    private fun enableUserInteraction() {
        mainActivity.window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    private fun disableUserInteraction() {
        mainActivity.window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
    }
}