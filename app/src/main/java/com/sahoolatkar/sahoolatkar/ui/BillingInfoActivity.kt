package com.sahoolatkar.sahoolatkar.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.api_models.order.Billing
import com.sahoolatkar.sahoolatkar.api_models.order.LineItems
import com.sahoolatkar.sahoolatkar.api_models.order.OrderApiModel
import com.sahoolatkar.sahoolatkar.api_models.order.Shipping
import com.sahoolatkar.sahoolatkar.api_utils.SahoolatKarApiUtils
import com.sahoolatkar.sahoolatkar.models.CartProduct
import kotlinx.android.synthetic.main.activity_billing_info.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class BillingInfoActivity : AppCompatActivity() {

    private var cartProducts: List<CartProduct>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_billing_info)

        init()
    }

    private fun init() {
        getIntentValues()
        setListeners()
    }

    private fun getIntentValues() {
        if (intent.extras != null)
            cartProducts = intent.extras?.get("cartProducts") as List<CartProduct>
    }

    private fun setListeners() {
        tvPlaceOrder.setOnClickListener {
            if (validateFields()) {
                placeOrder()
            }
        }
    }

    private fun placeOrder() {
        if (!cartProducts.isNullOrEmpty()) {
            var billing = Billing(
                etFirstName.text.toString(), etLastName.text.toString(),
                etAddress.text.toString(), "", etCity.text.toString(),
                "", 54000, "Pakistan", etEmail.text.toString(),
                etPhone.text.toString()
            )
            var shipping = Shipping(
                etFirstName.text.toString(), etLastName.text.toString(),
                etAddress.text.toString(), "", etCity.text.toString(),
                "", 54000, "Pakistan"
            )
            val lineItems = ArrayList<LineItems>()

            for (cartProduct in cartProducts!!) {
                lineItems.add(LineItems(cartProduct.product.id,  cartProduct.quantity))
            }
            val order =
                OrderApiModel(billing, shipping, "cod", "Cash on delivery", false, lineItems, ArrayList())

            lifecycleScope.launch {
                val response: Response<OrderApiModel> = SahoolatKarApiUtils.postOrder(order)
                if (response.isSuccessful) {
                    Toast.makeText(this@BillingInfoActivity, "Order Placed Successfully", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    Toast.makeText(this@BillingInfoActivity, "Failed to place order. Error: " + response.message(), Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun validateFields(): Boolean {
        return when {
            etFirstName.text.isNullOrEmpty() -> {
                false
            }
            etLastName.text.isNullOrEmpty() -> {
                false
            }
            etAddress.text.isNullOrEmpty() -> {
                false
            }
            etCity.text.isNullOrEmpty() -> {
                false
            }
            etEmail.text.isNullOrEmpty() -> {
                false
            }
            else -> !etPhone.text.isNullOrEmpty()
        }
    }
}