package com.sahoolatkar.sahoolatkar.data_sources

import androidx.paging.PageKeyedDataSource
import com.sahoolatkar.sahoolatkar.api_models.product.ProductApiModel
import com.sahoolatkar.sahoolatkar.api_utils.SahoolatKarApiUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class ProductDataSource(private val categoryId: String, private val coroutineScope: CoroutineScope) : PageKeyedDataSource<Int, ProductApiModel>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ProductApiModel>
    ) {
        coroutineScope.launch {
            val response = SahoolatKarApiUtils.getProductsWithCo(categoryId, null, 1)
            if (response.isSuccessful) {
                callback.onResult(response.body()!!, null, 2)
            } else {
                callback.onResult(ArrayList(), null, 2)
            }
        }

    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, ProductApiModel>
    ) {
        coroutineScope.launch {
            val response = SahoolatKarApiUtils.getProductsWithCo(categoryId, null, params.key)
            if (response.isSuccessful) {
                callback.onResult(response.body()!!, params.key + 1)
            } else {
                callback.onResult(ArrayList(), params.key + 1)
            }
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, ProductApiModel>
    ) {
        coroutineScope.launch {
            val response = SahoolatKarApiUtils.getProductsWithCo(categoryId, null, params.key)
            if (response.isSuccessful) {
                callback.onResult(response.body()!!, params.key + 1)
            } else {
                callback.onResult(ArrayList(), params.key + 1)
            }
        }
    }

    override fun invalidate() {
        super.invalidate()
        coroutineScope.cancel("end")
    }
}