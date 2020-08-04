package com.sahoolatkar.sahoolatkar.api_models.order

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Order (

//	@Expose(serialize = false, deserialize = true) @SerializedName("id") val id : Int,
//	@SerializedName("parent_id") val parent_id : Int,
//	@SerializedName("number") val number : Int,
//	@SerializedName("order_key") val order_order_key : String,
//	@SerializedName("created_via") val created_via : String,
//	@SerializedName("version") val version : String,
//	@SerializedName("status") val status : String,
//	@SerializedName("currency") val currency : String,
//	@SerializedName("date_created") val date_created : String,
//	@SerializedName("date_created_gmt") val date_created_gmt : String,
//	@SerializedName("date_modified") val date_modified : String,
//	@SerializedName("date_modified_gmt") val date_modified_gmt : String,
//	@SerializedName("discount_total") val discount_total : Double,
//	@SerializedName("discount_tax") val discount_tax : Double,
//	@SerializedName("shipping_total") val shipping_total : Double,
//	@SerializedName("shipping_tax") val shipping_tax : Double,
//	@SerializedName("cart_tax") val cart_tax : Double,
//	@SerializedName("total") val total : Double,
//	@SerializedName("total_tax") val total_tax : Double,
//	@SerializedName("prices_include_tax") val prices_include_tax : Boolean,
	@Expose() @SerializedName("customer_id") val customer_id : Int,
//	@SerializedName("customer_ip_address") val customer_ip_address : String,
//	@SerializedName("customer_user_agent") val customer_user_agent : String,
//	@SerializedName("customer_note") val customer_note : String,
	@Expose() @SerializedName("billing") val billing : Billing,
	@Expose() @SerializedName("shipping") val shipping : Shipping,
	@Expose() @SerializedName("payment_method") val payment_method : String,
	@Expose() @SerializedName("payment_method_title") val payment_method_title : String,
	@Expose(serialize = true, deserialize = false) @SerializedName("set_paid") val set_paid : Boolean,
//	@SerializedName("transaction_id") val transaction_id : String,
//	@SerializedName("date_paid") val date_paid : String,
//	@SerializedName("date_paid_gmt") val date_paid_gmt : String,
//	@SerializedName("date_completed") val date_completed : String,
//	@SerializedName("date_completed_gmt") val date_completed_gmt : String,
//	@SerializedName("cart_hash") val cart_hash : String,
//	@SerializedName("meta_data") val meta_data : List<Meta_data>,
	@Expose() @SerializedName("line_items") val line_items : List<LineItems>,
//	@SerializedName("tax_lines") val tax_lines : List<Tax_lines>,
	@Expose() @SerializedName("shipping_lines") val shipping_lines : List<ShippingLines>
//	@SerializedName("fee_lines") val fee_lines : List<String>,
//	@SerializedName("coupon_lines") val coupon_lines : List<String>,
//	@SerializedName("refunds") val refunds : List<String>,
//	@SerializedName("_links") val _links : _links
)