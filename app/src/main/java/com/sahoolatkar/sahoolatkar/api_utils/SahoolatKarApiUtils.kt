package com.sahoolatkar.sahoolatkar.api_utils

import ProductApiModel
import android.content.Context
import android.widget.Toast
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.api_callbacks.IGetAllProductsCallback
import com.sahoolatkar.sahoolatkar.apis_clients.SahoolatkarApiClient
import com.sahoolatkar.sahoolatkar.globals.GlobalVariables
import com.sahoolatkar.sahoolatkar.http_services.SahoolatkarRestApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SahoolatKarApiUtils {

    companion object {

        private val sahoolatkarApiClient: SahoolatkarApiClient = SahoolatkarRestApiService.createService(SahoolatkarApiClient::class.java, GlobalVariables.WOOCOMMERCE_CONSUMER_KEY, GlobalVariables.WOOCOMMERCE_CONSUMER_SECRET)

        fun getAllProducts(context: Context, getAllProductsCallback: IGetAllProductsCallback) {

            SahoolatkarRestApiService.createService(SahoolatkarApiClient::class.java, context.getString(
                R.string.WOOCOMMERCE_CONSUMER_KEY), context.getString(
                R.string.WOOCOMMERCE_CONSUMER_SECRET)).getAllProducts()?.enqueue(object :
                Callback<List<ProductApiModel?>?> {
                override fun onFailure(call: Call<List<ProductApiModel?>?>, t: Throwable) {
                    getAllProductsCallback.onGetProducts(ArrayList())
                }

                override fun onResponse(
                    call: Call<List<ProductApiModel?>?>,
                    response: Response<List<ProductApiModel?>?>
                ) {
                    if (response.body() != null) {
                        getAllProductsCallback.onGetProducts(response.body() as MutableList<ProductApiModel>)
                    } else {
                        getAllProductsCallback.onGetProducts(ArrayList())
                    }
                }
            })
        }

        fun getProductsByCategory(context: Context, categoryId: String, getProductsByCategoryCallback: IGetAllProductsCallback) {
            SahoolatkarRestApiService.createService(SahoolatkarApiClient::class.java, context.getString(
                R.string.WOOCOMMERCE_CONSUMER_KEY), context.getString(
                R.string.WOOCOMMERCE_CONSUMER_SECRET)).getProductsByCategory(categoryId)?.enqueue(object :
                Callback<List<ProductApiModel?>?> {
                override fun onFailure(call: Call<List<ProductApiModel?>?>, t: Throwable) {
                    getProductsByCategoryCallback.onGetProducts(ArrayList())
                    Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<List<ProductApiModel?>?>,
                    response: Response<List<ProductApiModel?>?>
                ) {
                    if (response.body() != null) {
                        getProductsByCategoryCallback.onGetProducts(response.body() as MutableList<ProductApiModel>)
                    } else {
                        getProductsByCategoryCallback.onGetProducts(ArrayList())
                    }
                }
            })
        }

        suspend fun getProductsByCategoryWithCo(categoryId: String, pageNo: Int) : Response<List<ProductApiModel>> = sahoolatkarApiClient.getProductsByCategoryWithCo(categoryId, pageNo)

    }

}