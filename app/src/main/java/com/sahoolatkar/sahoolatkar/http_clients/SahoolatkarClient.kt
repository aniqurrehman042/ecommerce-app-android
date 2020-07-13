package com.sahoolatkar.sahoolatkar.http_clients

import ProductApiModel
import com.sahoolatkar.sahoolatkar.apis_clients.SahoolatkarApiClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SahoolatkarClient {

    companion object {

        const val BASE_URL = "http://192.168.42.13:8080/wordpress/wp-json/wc/v2/"

        fun getProductService(): SahoolatkarApiClient {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service: SahoolatkarApiClient = retrofit.create(SahoolatkarApiClient::class.java)
            return service
        }
    }

}