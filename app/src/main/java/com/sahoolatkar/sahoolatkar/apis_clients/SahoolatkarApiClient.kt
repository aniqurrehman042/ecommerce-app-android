package com.sahoolatkar.sahoolatkar.apis_clients

import com.sahoolatkar.sahoolatkar.api_models.customer.Customer
import com.sahoolatkar.sahoolatkar.api_models.order.Order
import com.sahoolatkar.sahoolatkar.api_models.product.ProductApiModel
import com.sahoolatkar.sahoolatkar.globals.GlobalVariables
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface SahoolatkarApiClient {

    @GET("products")
    fun getAllProducts(): Call<List<ProductApiModel?>?>?

    @GET("products")
    fun getProductsByCategory(@Query("category") categoryId: String): Call<List<ProductApiModel?>?>?

    @GET("products")
    suspend fun getProductsWithCo(@Query("category") categoryId: String?, @Query("featured") featured: Boolean?, @Query("page") pageNo: Int, @Query("per_page") pageSize: Int = GlobalVariables.PRODUCTS_PAGE_SIZE): Response<List<ProductApiModel>>

    @POST("orders")
    suspend fun postOrder(@Body order: Order) : Response<Order>

    @POST("customers")
    suspend fun createCustomer(@Body customer: Customer) : Response<Customer>

    @GET("customers")
    suspend fun getAllCustomers() : Response<List<Customer>>

}