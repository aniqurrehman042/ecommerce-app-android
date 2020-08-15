package com.sahoolatkar.sahoolatkar.apis_clients

import com.sahoolatkar.sahoolatkar.api_models.NadraResponse
import com.sahoolatkar.sahoolatkar.api_models.customer.Customer
import retrofit2.Response
import retrofit2.http.*

interface SahoolatkarGeneralApiClient {

    @POST("/api/create_customer_pin.php")
    suspend fun createCustomerPin(@Body customer: Customer) : Response<Boolean>

    @FormUrlEncoded
    @POST("/api/get_nadra_details.php")
    suspend fun getNadraDetails(@Field("cnic") cnic: String) : Response<NadraResponse>

    @FormUrlEncoded
    @POST("/api/get_customer.php")
    suspend fun getCustomer(@Field("cnic") cnic: String) : Response<Customer>

}