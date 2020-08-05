package com.sahoolatkar.sahoolatkar.apis_clients

import com.sahoolatkar.sahoolatkar.api_models.NadraResponse
import com.sahoolatkar.sahoolatkar.api_models.customer.Customer
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SahoolatkarCustomersApiClient {

    @POST("create_customer_pin.php")
    suspend fun createCustomerPin(@Body customer: Customer) : Response<Boolean>

    @FormUrlEncoded
    @POST("get_nadra_details.php")
    suspend fun getNadraDetails(@Field("cnic") cnic: String) : Response<NadraResponse>

    @FormUrlEncoded
    @POST("get_customer.php")
    suspend fun getCustomer(@Field("cnic") cnic: String) : Response<Customer>

}