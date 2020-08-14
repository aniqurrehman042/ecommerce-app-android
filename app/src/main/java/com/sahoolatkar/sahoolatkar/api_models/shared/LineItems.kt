package com.sahoolatkar.sahoolatkar.api_models.shared

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LineItems (

//	@SerializedName("id") val id : Int,
	@Expose() @SerializedName("product_id") val product_id : Int,
	@Expose() @SerializedName("quantity") val quantity : Int,
	@Expose() @SerializedName("variation_id") var variation_id : Int = 0,
	@Expose(serialize = false, deserialize = true) @SerializedName("name") val name : String = "",
//	@SerializedName("tax_class") val tax_class : String,
	@Expose(serialize = false, deserialize = true) @SerializedName("subtotal") val subtotal : Double = 0.0,
//	@SerializedName("subtotal_tax") val subtotal_tax : Double,
	@Expose(serialize = false, deserialize = true) @SerializedName("total") val total : Double = 0.0,
//	@SerializedName("total_tax") val total_tax : Double,
//	@SerializedName("taxes") val taxes : List<Taxes>,
//	@SerializedName("meta_data") val meta_data : List<String>,
//	@SerializedName("sku") val sku : String,
	@Expose(serialize = false, deserialize = true) @SerializedName("price") val price : Int = 0
)