package com.sahoolatkar.sahoolatkar.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.adapters.ProductsPagedRecyclerAdapter
import com.sahoolatkar.sahoolatkar.globals.GlobalVariables
import com.sahoolatkar.sahoolatkar.ui.MainActivity
import com.sahoolatkar.sahoolatkar.utils.LoadingUtils
import com.sahoolatkar.sahoolatkar.utils.ViewUtils
import com.sahoolatkar.sahoolatkar.viewmodels.ProductCatalogViewModel
import kotlinx.android.synthetic.main.fragment_products_catalog.*
import kotlinx.android.synthetic.main.layout_loader.*

class ProductsCatalogFragment : Fragment() {

    private lateinit var mainActivity: MainActivity
    private lateinit var productsPagedRecyclerAdapter: ProductsPagedRecyclerAdapter
    private var productsCatalogViewModel: ProductCatalogViewModel? = null

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
        setCategoryText()
        initializeAdapter()
        setUpProductsRecycler()
        setUpViewModel()
    }

    private fun setCategoryText() {
        tvCategory.text = args.categoryName
    }

    private fun initializeAdapter() {
        productsPagedRecyclerAdapter =
            ProductsPagedRecyclerAdapter(mainActivity, GlobalVariables.PRODUCT_CATALOG_FRAGMENT)
    }

    private fun setUpViewModel() {
        LoadingUtils.showLoader(mainActivity, llLoader, "Loading Products...")
        if (productsCatalogViewModel == null) {
            productsCatalogViewModel = ProductCatalogViewModel(args.categoryId)
        } else {
            LoadingUtils.hideLoader(mainActivity, llLoader)
        }
        productsCatalogViewModel!!.products.observe(viewLifecycleOwner, Observer {
            productsPagedRecyclerAdapter.submitList(it)
        })
        productsPagedRecyclerAdapter.registerAdapterDataObserver(object :
            RecyclerView.AdapterDataObserver() {
            private var initialized = false
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                super.onItemRangeInserted(positionStart, itemCount)
                if (initialized)
                    LoadingUtils.hideLoader(mainActivity, llLoader)
                if (!initialized)
                    initialized = true
            }
        })
    }

    private fun setUpProductsRecycler() {
        rvProducts.layoutManager = GridLayoutManager(context, 2)
        rvProducts.adapter = productsPagedRecyclerAdapter
    }
}