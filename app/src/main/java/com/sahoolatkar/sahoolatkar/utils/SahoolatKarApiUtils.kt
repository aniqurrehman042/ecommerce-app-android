package com.sahoolatkar.sahoolatkar.utils

import ProductApiModel
import android.content.Context
import com.sahoolatkar.sahoolatkar.api_callbacks.IGetAllProductsCallback
import com.sahoolatkar.sahoolatkar.http_clients.SahoolatkarClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SahoolatKarApiUtils {

    companion object {
        fun getAllProducts(context: Context, getAllProductsCallback: IGetAllProductsCallback) {

            SahoolatkarClient.getProductService().getAllProducts()?.enqueue(object :
                Callback<List<ProductApiModel?>?> {
                override fun onFailure(call: Call<List<ProductApiModel?>?>, t: Throwable) {
                    getAllProductsCallback.onGetAllProducts(ArrayList())
                }

                override fun onResponse(
                    call: Call<List<ProductApiModel?>?>,
                    response: Response<List<ProductApiModel?>?>
                ) {
                    if (response.body() != null) {
                        getAllProductsCallback.onGetAllProducts(response.body() as MutableList<ProductApiModel>)
                    } else {
                        getAllProductsCallback.onGetAllProducts(ArrayList())
                    }
                }
            })
        }
    }

}