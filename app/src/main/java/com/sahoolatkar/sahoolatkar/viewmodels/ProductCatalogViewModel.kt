package com.sahoolatkar.sahoolatkar.viewmodels

import ProductApiModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.sahoolatkar.sahoolatkar.data_sources.ProductDataSource
import com.sahoolatkar.sahoolatkar.globals.GlobalVariables

class ProductCatalogViewModel(val categoryId: String) : ViewModel() {
    val products: LiveData<PagedList<ProductApiModel>> by lazy<LiveData<PagedList<ProductApiModel>>> {
        val config = PagedList.Config.Builder()
            .setPageSize(GlobalVariables.PRODUCTS_PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()
        initializeProductsPagedListBuilder(config).build()

    }

    private fun initializeProductsPagedListBuilder(config: PagedList.Config): LivePagedListBuilder<Int, ProductApiModel> {
        val productDataSourceFactory = object: DataSource.Factory<Int, ProductApiModel>() {
            override fun create(): DataSource<Int, ProductApiModel> {
                return ProductDataSource(categoryId, viewModelScope)
            }
        }

        return LivePagedListBuilder<Int, ProductApiModel>(productDataSourceFactory, config)
    }
}