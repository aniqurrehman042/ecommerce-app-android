package com.sahoolatkar.sahoolatkar.apis_clients

import ProductApiModel
import com.sahoolatkar.sahoolatkar.globals.GlobalVariables
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface SahoolatkarApiClient {

    @GET("products")
    fun getAllProducts(): Call<List<ProductApiModel?>?>?

    @GET("products")
    fun getProductsByCategory(@Query("category") categoryId: String): Call<List<ProductApiModel?>?>?

    @GET("products")
    suspend fun getProductsByCategoryWithCo(@Query("category") categoryId: String, @Query("page") pageNo: Int, @Query("per_page") pageSize: Int = GlobalVariables.PRODUCTS_PAGE_SIZE): Response<List<ProductApiModel>>

}