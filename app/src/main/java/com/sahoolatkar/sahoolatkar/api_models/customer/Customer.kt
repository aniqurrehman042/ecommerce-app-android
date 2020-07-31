package com.sahoolatkar.sahoolatkar.api_models.customer

import com.google.gson.annotations.SerializedName
import com.sahoolatkar.sahoolatkar.api_models.order.Billing
import com.sahoolatkar.sahoolatkar.api_models.order.Shipping

data class Customer(
    @SerializedName("email") var email: String,
    @SerializedName("first_name") var firstName: String,
    @SerializedName("last_name") var lastName: String,
    @SerializedName("username") var username: String,
    @SerializedName("password") var password: String,
    @SerializedName("billing") var billing: Billing,
    @SerializedName("shipping") var shipping: Shipping
)