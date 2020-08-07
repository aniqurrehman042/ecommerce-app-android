package com.sahoolatkar.sahoolatkar.api_utils

import android.content.Context
import android.widget.Toast
import com.sahoolatkar.sahoolatkar.api_callbacks.IGetAllProductsCallback
import com.sahoolatkar.sahoolatkar.api_models.NadraResponse
import com.sahoolatkar.sahoolatkar.api_models.customer.Customer
import com.sahoolatkar.sahoolatkar.api_models.order.Order
import com.sahoolatkar.sahoolatkar.api_models.product.ProductApiModel
import com.sahoolatkar.sahoolatkar.api_models.subscription.Subscription
import com.sahoolatkar.sahoolatkar.apis_clients.SahoolatkarApiClient
import com.sahoolatkar.sahoolatkar.apis_clients.SahoolatkarCustomersApiClient
import com.sahoolatkar.sahoolatkar.globals.GlobalVariables
import com.sahoolatkar.sahoolatkar.http_services.SahoolatkarCustomersService
import com.sahoolatkar.sahoolatkar.http_services.SahoolatkarWoocommerceApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("UNCHECKED_CAST")
class SahoolatKarApiUtils {

    companion object {

        private val sahoolatkarApiClient: SahoolatkarApiClient =
            SahoolatkarWoocommerceApiService.createService(
                SahoolatkarApiClient::class.java,
                GlobalVariables.WOOCOMMERCE_CONSUMER_KEY,
                GlobalVariables.WOOCOMMERCE_CONSUMER_SECRET
            )

        private val sahoolatkarCustomersApiClient: SahoolatkarCustomersApiClient =
            SahoolatkarCustomersService.createService(SahoolatkarCustomersApiClient::class.java)

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

        suspend fun postOrder(order: Order) : Response<Order> {
            return try {
                sahoolatkarApiClient.postOrder(order)
            } catch (e: Exception) {
                postOrder(order)
            }
        }

        suspend fun createCustomer(customer: Customer) : Response<Customer> {
            return try {
                sahoolatkarApiClient.createCustomer(customer)
            } catch (e: Exception) {
                createCustomer(customer)
            }
        }

        suspend fun getAllCustomers() : Response<List<Customer>> {
            return try {
                sahoolatkarApiClient.getAllCustomers()
            } catch (e: Exception) {
                getAllCustomers()
            }
        }

        suspend fun createCustomerPin(customer: Customer) : Response<Boolean> {
            return try{
                sahoolatkarCustomersApiClient.createCustomerPin(customer)
            } catch (e: Exception) {
                createCustomerPin(customer)
            }
        }

        suspend fun getNadraDetails(cnic: String) : Response<NadraResponse> {
            return try{
                sahoolatkarCustomersApiClient.getNadraDetails(cnic)
            } catch (e: Exception) {
                getNadraDetails(cnic)
            }
        }

        suspend fun getCustomer(cnic: String) : Response<Customer> {
            return try{
                sahoolatkarCustomersApiClient.getCustomer(cnic)
            } catch (e: Exception) {
                getCustomer(cnic)
            }
        }

        suspend fun getWooCommerceCustomer(id: Int) : Response<Customer> {
            return try{
                sahoolatkarApiClient.getCustomer(id)
            } catch (e: Exception) {
                getWooCommerceCustomer(id)
            }
        }

        suspend fun createSubscription(subscription: Subscription) : Response<Subscription> {
            return try{
                sahoolatkarApiClient.createSubscription(subscription)
            } catch (e: Exception) {
                createSubscription(subscription)
            }
        }

    }

}