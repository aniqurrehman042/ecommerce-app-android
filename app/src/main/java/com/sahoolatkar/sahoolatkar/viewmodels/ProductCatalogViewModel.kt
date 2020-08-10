package com.sahoolatkar.sahoolatkar.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.sahoolatkar.sahoolatkar.api_models.product.Product
import com.sahoolatkar.sahoolatkar.data_sources.ProductDataSource
import com.sahoolatkar.sahoolatkar.globals.GlobalVariables

class ProductCatalogViewModel(val categoryId: String) : ViewModel() {
    val products: LiveData<PagedList<Product>> by lazy<LiveData<PagedList<Product>>> {
        val config = PagedList.Config.Builder()
            .setPageSize(GlobalVariables.PRODUCTS_PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()
        initializeProductsPagedListBuilder(config).build()

    }

    private fun initializeProductsPagedListBuilder(config: PagedList.Config): LivePagedListBuilder<Int, Product> {
        val productDataSourceFactory = object: DataSource.Factory<Int, Product>() {
            override fun create(): DataSource<Int, Product> {
                return ProductDataSource(categoryId, viewModelScope)
            }
        }

        return LivePagedListBuilder<Int, Product>(productDataSourceFactory, config)
    }
}