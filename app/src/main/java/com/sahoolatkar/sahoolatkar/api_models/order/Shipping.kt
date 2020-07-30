package com.sahoolatkar.sahoolatkar.api_models.order

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Shipping (

	@Expose() @SerializedName("first_name") val first_name : String,
	@Expose() @SerializedName("last_name") val last_name : String,
//	@SerializedName("company") val company : String,
	@Expose() @SerializedName("address_1") val address_1 : String,
	@Expose() @SerializedName("address_2") val address_2 : String,
	@Expose() @SerializedName("city") val city : String,
	@Expose() @SerializedName("state") val state : String,
	@Expose() @SerializedName("postcode") val postcode : Int,
	@Expose() @SerializedName("country") val country : String
)