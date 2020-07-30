package com.sahoolatkar.sahoolatkar.http_services

import android.text.TextUtils
import com.google.gson.GsonBuilder
import com.sahoolatkar.sahoolatkar.interceptors.AuthenticationInterceptor
import okhttp3.Credentials
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class SahoolatkarRestApiService {

    companion object {

        private const val API_BASE_URL = "https://sahoolatkar.com/wp-json/wc/v2/"
        private val httpClient = OkHttpClient.Builder()
        private val builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
                )
            )
        private var retrofit = builder.build()

        fun <S> createService(
            serviceClass: Class<S>?, username: String?, password: String?
        ): S {
            if (username!!.isNotEmpty()
                && password!!.isNotEmpty()
            ) {
                val authToken: String = Credentials.basic(username, password)
                return createService(serviceClass, authToken)
            }
            return createService(serviceClass, null)
        }

        private fun <S> createService(
            serviceClass: Class<S>?, authToken: String?
        ): S {
            if (!TextUtils.isEmpty(authToken)) {
                val interceptor = AuthenticationInterceptor(authToken!!)
                if (!httpClient.interceptors().contains(interceptor)) {
                    httpClient.addInterceptor(interceptor)
                    builder.client(httpClient.build())
                    retrofit = builder.build()
                }
            }
            return retrofit.create(serviceClass!!)
        }
    }

}