package com.sahoolatkar.sahoolatkar.api_models.product

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/*
Copyright (c) 2020 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */

data class ProductApiModel (
	@Expose @SerializedName("id") val id : Int,
	@Expose @SerializedName("name") val name : String,
	@Expose @SerializedName("slug") val slug : String,
	@Expose @SerializedName("date_created") val date_created : String,
	@SerializedName("date_created_gmt") val date_created_gmt : String,
	@SerializedName("permalink") val permalink : String,
	@SerializedName("date_modified") val date_modified : String,
	@SerializedName("date_modified_gmt") val date_modified_gmt : String,
	@SerializedName("type") val type : String,
	@SerializedName("status") val status : String,
	@Expose @SerializedName("featured") val featured : Boolean,
	@SerializedName("catalog_visibility") val catalog_visibility : String,
	@Expose @SerializedName("description") val description : String,
	@SerializedName("short_description") val short_description : String,
	@SerializedName("sku") val sku : String,
	@SerializedName("price") val price : String,
	@SerializedName("regular_price") val regular_price : String,
	@SerializedName("sale_price") val sale_price : String,
	@SerializedName("date_on_sale_from") val date_on_sale_from : String,
	@SerializedName("date_on_sale_from_gmt") val date_on_sale_from_gmt : String,
	@SerializedName("date_on_sale_to") val date_on_sale_to : String,
	@SerializedName("date_on_sale_to_gmt") val date_on_sale_to_gmt : String,
	@SerializedName("price_html") val price_html : String,
	@SerializedName("on_sale") val on_sale : Boolean,
	@SerializedName("purchasable") val purchasable : Boolean,
	@SerializedName("total_sales") val total_sales : Int,
	@SerializedName("virtual") val virtual : Boolean,
	@SerializedName("downloadable") val downloadable : Boolean,
	@Expose @SerializedName("downloads") val downloads : List<String>,
	@SerializedName("download_limit") val download_limit : Int,
	@SerializedName("download_expiry") val download_expiry : Int,
	@SerializedName("external_url") val external_url : String,
	@SerializedName("button_text") val button_text : String,
	@SerializedName("tax_status") val tax_status : String,
	@SerializedName("tax_class") val tax_class : String,
	@SerializedName("manage_stock") val manage_stock : Boolean,
	@SerializedName("stock_quantity") val stock_quantity : String,
	@SerializedName("in_stock") val in_stock : Boolean,
	@SerializedName("backorders") val backorders : String,
	@SerializedName("backorders_allowed") val backorders_allowed : Boolean,
	@SerializedName("backordered") val backordered : Boolean,
	@SerializedName("sold_individually") val sold_individually : Boolean,
	@SerializedName("weight") val weight : String,
	@SerializedName("dimensions") val dimensions : Dimensions,
	@SerializedName("shipping_required") val shipping_required : Boolean,
	@SerializedName("shipping_taxable") val shipping_taxable : Boolean,
	@SerializedName("shipping_class") val shipping_class : String,
	@SerializedName("shipping_class_id") val shipping_class_id : Int,
	@SerializedName("reviews_allowed") val reviews_allowed : Boolean,
	@SerializedName("average_rating") val average_rating : Double,
	@SerializedName("rating_count") val rating_count : Int,
	@SerializedName("related_ids") val related_ids : List<Int>,
	@SerializedName("upsell_ids") val upsell_ids : List<String>,
	@SerializedName("cross_sell_ids") val cross_sell_ids : List<String>,
	@SerializedName("parent_id") val parent_id : Int,
	@SerializedName("purchase_note") val purchase_note : String,
	@SerializedName("categories") val categories : List<Categories>,
	@SerializedName("tags") val tags : List<String>,
	@Expose @SerializedName("images") val images : List<Images>,
	@SerializedName("attributes") val attributes : List<Attributes>,
	@SerializedName("default_attributes") val default_attributes : List<String>,
	@SerializedName("variations") val variations : List<String>,
	@SerializedName("grouped_products") val grouped_products : List<String>,
	@SerializedName("menu_order") val menu_order : Int,
	@SerializedName("meta_data") val meta_data : List<Meta_data>,
	@SerializedName("brands") val brands : List<Brands>,
	@SerializedName("_links") val _links : _links,
	var wishListed: Boolean = false
) : Serializable