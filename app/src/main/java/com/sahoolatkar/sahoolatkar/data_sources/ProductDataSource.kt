package com.sahoolatkar.sahoolatkar.data_sources

import ProductApiModel
import androidx.paging.PageKeyedDataSource

class ProductDataSource : PageKeyedDataSource<String, ProductApiModel>() {
    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, ProductApiModel>
    ) {
        val items = params.requestedLoadSize
    }

    override fun loadAfter(
        params: LoadParams<String>,
        callback: LoadCallback<String, ProductApiModel>
    ) {
        TODO("Not yet implemented")
    }

    override fun loadBefore(
        params: LoadParams<String>,
        callback: LoadCallback<String, ProductApiModel>
    ) {
        TODO("Not yet implemented")
    }
}