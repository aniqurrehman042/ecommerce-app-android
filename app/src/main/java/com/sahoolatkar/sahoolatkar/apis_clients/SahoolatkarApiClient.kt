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

    @GET("v3/products")
    fun getAllProducts(): Call<List<ProductApiModel?>?>?

    @GET("v3/products")
    fun getProductsByCategory(@Query("category") categoryId: String): Call<List<ProductApiModel?>?>?

    @GET("v3/products")
    suspend fun getProductsWithCo(@Query("category") categoryId: String?, @Query("featured") featured: Boolean?, @Query("page") pageNo: Int, @Query("per_page") pageSize: Int = GlobalVariables.PRODUCTS_PAGE_SIZE): Response<List<ProductApiModel>>

    @POST("v3/orders")
    suspend fun postOrder(@Body order: Order) : Response<Order>

    @POST("v3/customers")
    suspend fun createCustomer(@Body customer: Customer) : Response<Customer>

    @GET("v3/customers")
    suspend fun getAllCustomers() : Response<List<Customer>>

    @GET("v3/customers/{id}")
    suspend fun getCustomer(@Path("id") id: Int) : Response<Customer>

    @GET("v1/subscriptions")
    suspend fun getSubscriptionsOfPerson(@Query("customer") cusId: Int) : Response<Subscription>

}