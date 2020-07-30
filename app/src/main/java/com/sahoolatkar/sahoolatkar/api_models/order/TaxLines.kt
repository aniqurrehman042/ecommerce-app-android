package com.sahoolatkar.sahoolatkar.api_models.order

import com.google.gson.annotations.SerializedName

data class TaxLines (

	@SerializedName("id") val id : Int,
	@SerializedName("rate_code") val rate_code : String,
	@SerializedName("rate_id") val rate_id : Int,
	@SerializedName("label") val label : String,
	@SerializedName("compound") val compound : Boolean,
	@SerializedName("tax_total") val tax_total : Double,
	@SerializedName("shipping_tax_total") val shipping_tax_total : Double,
	@SerializedName("meta_data") val meta_data : List<String>
)