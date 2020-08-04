package com.sahoolatkar.sahoolatkar.apis_clients

import com.sahoolatkar.sahoolatkar.api_models.customer.Customer
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SahoolatkarCustomersApiClient {

    @POST("create_customer_pin.php")
    fun createCustomerPin(@Body customer: Customer) : Response<Boolean>

}