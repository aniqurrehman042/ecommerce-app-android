package com.sahoolatkar.sahoolatkar.http_services

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SahoolatkarGeneralService {

    companion object {

        //        private const val API_BASE_URL = "https://mygreatdubai.com/sahoolatkar/api/"
        private const val API_BASE_URL = "https://sahoolatkar.com/"
        private val httpClient = OkHttpClient.Builder()
        private val builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd").create()
                )
            )
        private var retrofit = builder.build()

        fun <S> createService(serviceClass: Class<S>?): S {
            builder.client(httpClient.build())
            retrofit = builder.build()
            return retrofit.create(serviceClass!!)
        }
    }

}