package com.sahoolatkar.sahoolatkar.apis_clients

import ProductApiModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface SahoolatkarApiClient {

    @GET("products")
    fun getAllProducts(): Call<List<ProductApiModel?>?>?

}