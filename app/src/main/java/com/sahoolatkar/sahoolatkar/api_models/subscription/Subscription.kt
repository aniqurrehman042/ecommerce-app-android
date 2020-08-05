package com.sahoolatkar.sahoolatkar.api_models.subscription

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.sahoolatkar.sahoolatkar.api_models.shared.Billing
import com.sahoolatkar.sahoolatkar.api_models.shared.LineItems
import com.sahoolatkar.sahoolatkar.api_models.shared.Shipping

data class Subscription(
    @Expose @SerializedName("id") var id: Int,
    @Expose @SerializedName("parent_id") var orderId: Int,
    @Expose @SerializedName("status") var status: String,
    @Expose @SerializedName("customer_id") var customerId: Int,
    @Expose @SerializedName("total") var total: String,
    @Expose @SerializedName("billing") var billing: Billing,
    @Expose @SerializedName("shipping") var shipping: Shipping,
    @Expose @SerializedName("payment_method") var paymentMethod: String,
    @Expose @SerializedName("payment_method_title") var paymentMethodTitle: String,
    @Expose @SerializedName("line_items") var lineItems: LineItems,
    @Expose @SerializedName("billing_period") var billingPeriod: String,
    @Expose @SerializedName("billing_interval") var billingInterval: String,
    @Expose @SerializedName("start_date") var startDate: String,
    @Expose @SerializedName("next_payment_date") var nextPaymentDate: String,
    @Expose @SerializedName("end_date") var endDate: String
)