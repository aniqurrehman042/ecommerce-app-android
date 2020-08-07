package com.sahoolatkar.sahoolatkar.apis_clients

import com.sahoolatkar.sahoolatkar.api_models.customer.Customer
import com.sahoolatkar.sahoolatkar.api_models.order.Order
import com.sahoolatkar.sahoolatkar.api_models.product.ProductApiModel
import com.sahoolatkar.sahoolatkar.api_models.subscription.Subscription
import com.sahoolatkar.sahoolatkar.globals.GlobalVariables
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface SahoolatkarApiClient {

    @GET("/wp-json/wc/v3/products")
    fun getAllProducts(): Call<List<ProductApiModel?>?>?

    @GET("/wp-json/wc/v3/products")
    fun getProductsByCategory(@Query("category") categoryId: String): Call<List<ProductApiModel?>?>?

    @GET("/wp-json/wc/v3/products")
    suspend fun getProductsWithCo(@Query("category") categoryId: String?, @Query("featured") featured: Boolean?, @Query("page") pageNo: Int, @Query("per_page") pageSize: Int = GlobalVariables.PRODUCTS_PAGE_SIZE): Response<List<ProductApiModel>>

    @POST("/wp-json/wc/v3/orders")
    suspend fun postOrder(@Body order: Order) : Response<Order>

    @POST("/wp-json/wc/v3/customers")
    suspend fun createCustomer(@Body customer: Customer) : Response<Customer>

    @GET("/wp-json/wc/v3/customers")
    suspend fun getAllCustomers() : Response<List<Customer>>

    @GET("/wp-json/wc/v3/customers/{id}")
    suspend fun getCustomer(@Path("id") id: Int) : Response<Customer>

    @GET("/wp-json/wc/v1/subscriptions")
    suspend fun getSubscriptionsOfPerson(@Query("customer") cusId: Int) : Response<Subscription>

    @POST("/wp-json/wc/v1/subscriptions")
    suspend fun createSubscription(@Body subscription: Subscription) : Response<Subscription>

    @POST("/wp-json/wc/v1/subscriptions/{id}")
    suspend fun updateSubscription(@Path("id") subId: Int, @Body subscription: Subscription) : Response<Subscription>

    @DELETE("/wp-json/wc/v1/subscriptions/{id}")
    suspend fun deleteSubscription(@Path("id") subId: Int, @Body subscription: Subscription, @Query("force") force: Boolean = true) : Response<Subscription>

}