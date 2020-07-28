package com.sahoolatkar.sahoolatkar.api_utils

import android.content.Context
import android.widget.Toast
import com.sahoolatkar.sahoolatkar.api_callbacks.IGetAllProductsCallback
import com.sahoolatkar.sahoolatkar.api_models.product.ProductApiModel
import com.sahoolatkar.sahoolatkar.apis_clients.SahoolatkarApiClient
import com.sahoolatkar.sahoolatkar.globals.GlobalVariables
import com.sahoolatkar.sahoolatkar.http_services.SahoolatkarRestApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("UNCHECKED_CAST")
class SahoolatKarApiUtils {

    companion object {

        private val sahoolatkarApiClient: SahoolatkarApiClient =
            SahoolatkarRestApiService.createService(
                SahoolatkarApiClient::class.java,
                GlobalVariables.WOOCOMMERCE_CONSUMER_KEY,
                GlobalVariables.WOOCOMMERCE_CONSUMER_SECRET
            )

        fun getAllProducts(context: Context, getAllProductsCallback: IGetAllProductsCallback) {

            try {
                sahoolatkarApiClient.getAllProducts()?.enqueue(object :
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
            } catch (e: Exception) {
                getAllProducts(context, getAllProductsCallback)
            }
        }

        fun getProductsByCategory(
            context: Context,
            categoryId: String,
            getProductsByCategoryCallback: IGetAllProductsCallback
        ) {
            try {
                sahoolatkarApiClient.getProductsByCategory(categoryId)?.enqueue(object :
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
            } catch (e: Exception) {
                getProductsByCategory(context, categoryId, getProductsByCategoryCallback)
            }
        }

        suspend fun getProductsWithCo(
            categoryId: String?,
            featured: Boolean?,
            pageNo: Int
        ): Response<List<ProductApiModel>> {
            return try {
                sahoolatkarApiClient.getProductsWithCo(categoryId, featured, pageNo)
            } catch (e: Exception) {
                getProductsWithCo(categoryId, featured, pageNo)
            }
        }

    }

}