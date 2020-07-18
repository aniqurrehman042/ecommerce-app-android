package com.sahoolatkar.sahoolatkar.api_utils

import ProductApiModel
import android.content.Context
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.api_callbacks.IGetAllProductsCallback
import com.sahoolatkar.sahoolatkar.apis_clients.SahoolatkarApiClient
import com.sahoolatkar.sahoolatkar.http_services.SahoolatkarRestApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SahoolatKarApiUtils {

    companion object {
        fun getAllProducts(context: Context, getAllProductsCallback: IGetAllProductsCallback) {

            SahoolatkarRestApiService.createService(SahoolatkarApiClient::class.java, context.getString(
                R.string.WOOCOMMERCE_CONSUMER_KEY), context.getString(
                R.string.WOOCOMMERCE_CONSUMER_SECRET)).getAllProducts()?.enqueue(object :
                Callback<List<ProductApiModel?>?> {
                override fun onFailure(call: Call<List<ProductApiModel?>?>, t: Throwable) {
                    getAllProductsCallback.onGetProducts(ArrayList())
//                    Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<List<ProductApiModel?>?>,
                    response: Response<List<ProductApiModel?>?>
                ) {
                    if (response.body() != null) {
                        getAllProductsCallback.onGetProducts(response.body() as MutableList<ProductApiModel>)
//                        Toast.makeText(context, response.message(), Toast.LENGTH_LONG).show()
                    } else {
                        getAllProductsCallback.onGetProducts(ArrayList())
//                        Toast.makeText(context, "Response is null", Toast.LENGTH_LONG).show()
                    }
                }
            })
        }

        fun getProductsByCategory(context: Context, category: String, getProductsByCategoryCallback: IGetAllProductsCallback) {

            SahoolatkarRestApiService.createService(SahoolatkarApiClient::class.java, context.getString(
                R.string.WOOCOMMERCE_CONSUMER_KEY), context.getString(
                R.string.WOOCOMMERCE_CONSUMER_SECRET)).getProductsByCategory(category)?.enqueue(object :
                Callback<List<ProductApiModel?>?> {
                override fun onFailure(call: Call<List<ProductApiModel?>?>, t: Throwable) {
                    getProductsByCategoryCallback.onGetProducts(ArrayList())
//                    Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<List<ProductApiModel?>?>,
                    response: Response<List<ProductApiModel?>?>
                ) {
                    if (response.body() != null) {
                        getProductsByCategoryCallback.onGetProducts(response.body() as MutableList<ProductApiModel>)
//                        Toast.makeText(context, response.message(), Toast.LENGTH_LONG).show()
                    } else {
                        getProductsByCategoryCallback.onGetProducts(ArrayList())
//                        Toast.makeText(context, "Response is null", Toast.LENGTH_LONG).show()
                    }
                }
            })
        }
    }

}