package com.sahoolatkar.sahoolatkar.ui

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.api_models.customer.Customer
import com.sahoolatkar.sahoolatkar.api_models.shared.Billing
import com.sahoolatkar.sahoolatkar.api_models.shared.Shipping
import com.sahoolatkar.sahoolatkar.api_utils.SahoolatKarApiUtils
import com.sahoolatkar.sahoolatkar.utils.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.layout_loader.*
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        init()
    }

    private fun init() {
        setListeners()
    }

    private fun setListeners() {
        tvRegister.setOnClickListener {
            if (validateFields()) {
                sendCreateCustomerRequest()
            }
        }

        EditTextUtils.setCnicHyphensAdder(etCnic, this)
    }

    private fun sendCreateCustomerRequest() {
        val firstName = etFirstName.text.toString()
        val lastName = etLastName.text.toString()
        val email = etEmail.text.toString()
        val cnic = etCnic.text.toString()
        val address = etAddress.text.toString()
        val city = etCity.text.toString()
        val province = etProvince.text.toString()
        val phone = etPhone.text.toString()

        val password = PasswordUtils.getRandomPassword()

        val billing =
            Billing(
                firstName,
                lastName,
                address,
                "",
                city,
                province,
                "",
                "Pakistan",
                email,
                phone
            )
        val shipping = Shipping(
            firstName,
            lastName,
            address,
            "",
            city,
            province,
            "",
            "Pakistan"
        )

        val customer = Customer(
            0,
            email,
            firstName,
            lastName,
            phone,
            password,
            "",
            cnic,
            billing,
            shipping
        )

        showLoader("Registering Profile...")

        lifecycleScope.launch {
            val response = SahoolatKarApiUtils.createCustomer(customer)
            if (response.isSuccessful && response.body() != null) {
                ToastUtils.showLongToast(
                    this@RegisterActivity,
                    "Registered Successfully. Please Create Your Pin."
                )
                customer.id = response.body()!!.id
                SharedPrefsUtils.saveCustomer(this@RegisterActivity, customer)
                startPinCreationActivity()
            } else {
                ToastUtils.showLongToast(
                    this@RegisterActivity,
                    "Couldn't register. Please try again."
                )
            }
            hideLoader()
        }
    }

    private fun startPinCreationActivity() {
        startActivity(Intent(this, PinCreationActivity::class.java))
        finish()
    }

    private fun validateFields(): Boolean {
        return when {
            etFirstName.text.isNullOrEmpty() -> false
            etLastName.text.isNullOrEmpty() -> false
            etEmail.text.isNullOrEmpty() -> false
            etCnic.text.isNullOrEmpty() -> false
            etAddress.text.isNullOrEmpty() -> false
            etCity.text.isNullOrEmpty() -> false
            etProvince.text.isNullOrEmpty() -> false
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