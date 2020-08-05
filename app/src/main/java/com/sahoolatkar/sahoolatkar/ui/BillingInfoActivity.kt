package com.sahoolatkar.sahoolatkar.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.api_models.shared.Billing
import com.sahoolatkar.sahoolatkar.api_models.shared.LineItems
import com.sahoolatkar.sahoolatkar.api_models.order.Order
import com.sahoolatkar.sahoolatkar.api_models.shared.Shipping
import com.sahoolatkar.sahoolatkar.api_utils.SahoolatKarApiUtils
import com.sahoolatkar.sahoolatkar.models.CartProduct
import com.sahoolatkar.sahoolatkar.utils.ViewUtils
import kotlinx.android.synthetic.main.activity_billing_info.*
import kotlinx.android.synthetic.main.layout_loader.*
import kotlinx.coroutines.launch
import retrofit2.Response

class BillingInfoActivity : AppCompatActivity() {

    private var cartProducts: MutableList<CartProduct>? = null

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
            cartProducts = intent.extras?.getSerializable("cartProducts") as MutableList<CartProduct>
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
                "", "54000", "Pakistan", etEmail.text.toString(),
                etPhone.text.toString()
            )
            var shipping =
                Shipping(
                    etFirstName.text.toString(), etLastName.text.toString(),
                    etAddress.text.toString(), "", etCity.text.toString(),
                    "", "54000", "Pakistan"
                )
            val lineItems = ArrayList<LineItems>()

            for (cartProduct in cartProducts!!) {
                lineItems.add(
                    LineItems(
                        cartProduct.product.id,
                        cartProduct.quantity
                    )
                )
            }
            val order =
                Order(0, billing, shipping, "cod", "Cash on delivery", false, lineItems, ArrayList())

            showLoader("Placing your order...")

            lifecycleScope.launch {
                val response: Response<Order> = SahoolatKarApiUtils.postOrder(order)
                hideLoader()
                if (response.isSuccessful) {
                    Toast.makeText(this@BillingInfoActivity, "Order Placed Successfully", Toast.LENGTH_LONG).show()
                    setResult(Activity.RESULT_OK, Intent().apply { putExtra("orderPlacementSuccessful", true) })
                    finish()
                } else {
                    Toast.makeText(this@BillingInfoActivity, "Failed to place order. Error: " + response.message().toString(), Toast.LENGTH_LONG).show()
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

    private fun showLoader(loadingText: String) {
        ViewUtils.showView(llLoader)
        tvLoadingMsg.text = loadingText
        disableUserInteraction()
    }

    private fun hideLoader() {
        ViewUtils.hideView(llLoader)
        enableUserInteraction()
    }

    private fun disableUserInteraction() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
    }

    private fun enableUserInteraction() {
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }
}