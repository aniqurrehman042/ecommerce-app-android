package com.sahoolatkar.sahoolatkar.ui.fragments

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.adapters.ProductsAdapter
import com.sahoolatkar.sahoolatkar.adapters.ProductsPagedRecyclerAdapter
import com.sahoolatkar.sahoolatkar.globals.GlobalVariables
import com.sahoolatkar.sahoolatkar.models.ProductModel
import com.sahoolatkar.sahoolatkar.ui.MainActivity
import com.sahoolatkar.sahoolatkar.utils.ViewUtils
import com.sahoolatkar.sahoolatkar.viewmodels.ProductCatalogViewModel
import kotlinx.android.synthetic.main.fragment_products_catalog.*
import kotlinx.android.synthetic.main.layout_loader.*

class ProductsCatalogFragment : Fragment() {

    private lateinit var mainActivity: MainActivity
    private lateinit var productsCatalogViewModel: ProductCatalogViewModel
    private lateinit var productsPagedRecyclerAdapter: ProductsPagedRecyclerAdapter

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
        initializeAdapter()
        setUpProductsRecycler()
        setUpViewModel()
    }

    private fun initializeAdapter() {
        productsPagedRecyclerAdapter = ProductsPagedRecyclerAdapter(mainActivity, GlobalVariables.PRODUCT_CATALOG_FRAGMENT)
    }

    private fun setUpViewModel() {
        productsCatalogViewModel = ProductCatalogViewModel(args.categoryId)
        showLoader("Loading Products...")
        productsCatalogViewModel.products.observe(viewLifecycleOwner, Observer {
            productsPagedRecyclerAdapter.submitList(it)
            hideLoader()
        })
    }

    private fun setUpProductsRecycler() {
        rvProducts.layoutManager = GridLayoutManager(context, 2)
        rvProducts.adapter = productsPagedRecyclerAdapter
    }

    private fun showLoader(loadingText: String) {
        ViewUtils.showView(llLoader)
        tvLoadingMsg.text = loadingText
        disableUserInteraction()
    }

    private fun hideLoader() {
        ViewUtils.hideView(llLoader)
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