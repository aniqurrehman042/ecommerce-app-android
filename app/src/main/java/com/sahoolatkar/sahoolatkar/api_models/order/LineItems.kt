package com.sahoolatkar.sahoolatkar.api_models.order

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LineItems (

//	@SerializedName("id") val id : Int,
//	@SerializedName("name") val name : String,
	@Expose() @SerializedName("product_id") val product_id : Int,
//	@SerializedName("variation_id") val variation_id : Int,
	@Expose() @SerializedName("quantity") val quantity : Int
//	@SerializedName("tax_class") val tax_class : String,
//	@SerializedName("subtotal") val subtotal : Double,
//	@SerializedName("subtotal_tax") val subtotal_tax : Double,
//	@SerializedName("total") val total : Double,
//	@SerializedName("total_tax") val total_tax : Double,
//	@SerializedName("taxes") val taxes : List<Taxes>,
//	@SerializedName("meta_data") val meta_data : List<String>,
//	@SerializedName("sku") val sku : String,
//	@SerializedName("price") val price : Int
)