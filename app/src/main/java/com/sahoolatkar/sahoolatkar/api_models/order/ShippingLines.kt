package com.sahoolatkar.sahoolatkar.api_models.order

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ShippingLines (

//	@SerializedName("id") val id : Int,
	@Expose() @SerializedName("method_title") val method_title : String,
	@Expose() @SerializedName("method_id") val method_id : String,
	@Expose() @SerializedName("total") val total : Double
//	@SerializedName("total_tax") val total_tax : Double,
//	@SerializedName("taxes") val taxes : List<String>,
//	@SerializedName("meta_data") val meta_data : List<String>
)