package com.sahoolatkar.sahoolatkar.api_models.customer

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.sahoolatkar.sahoolatkar.api_models.shared.Billing
import com.sahoolatkar.sahoolatkar.api_models.shared.Shipping

data class Customer(
    @Expose @SerializedName("id") var id: Int,
    @Expose @SerializedName("email") var email: String,
    @Expose @SerializedName("first_name") var firstName: String,
    @Expose @SerializedName("last_name") var lastName: String,
    @Expose @SerializedName("username") var username: String,
    @Expose @SerializedName("password") var password: String,
    @Expose @SerializedName("pin") var pin: String,
    @Expose @SerializedName("cnic") var cnic: String,
    @Expose @SerializedName("billing") var billing: Billing,
    @Expose @SerializedName("shipping") var shipping: Shipping
)