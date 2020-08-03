package com.sahoolatkar.sahoolatkar.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.lifecycle.lifecycleScope
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.api_models.customer.Customer
import com.sahoolatkar.sahoolatkar.api_utils.SahoolatKarApiUtils
import com.sahoolatkar.sahoolatkar.utils.SharedPrefsUtils
import com.sahoolatkar.sahoolatkar.utils.ToastUtils
import com.sahoolatkar.sahoolatkar.utils.ViewUtils
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.etEmail
import kotlinx.android.synthetic.main.activity_login.etPassword
import kotlinx.android.synthetic.main.layout_loader.*
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()
    }

    private fun init() {
        setListeners()
    }

    private fun setListeners() {
        tvLogin.setOnClickListener {
            login()
        }

        tvRegister.setOnClickListener {
            startRegisterActivity()
        }
    }

    private fun startRegisterActivity() {
        startActivity(Intent(this, RegisterActivity::class.java))
        finish()
    }

    private fun login() {
        if (validateFields()) {
            sendLoginRequest()
        }
    }

    private fun sendLoginRequest() {
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()

        showLoader("Logging In...")

        lifecycleScope.launch {
            val response = SahoolatKarApiUtils.getAllCustomers()

            if (response.isSuccessful && response.body() != null) {
                val customers = response.body()!!
                if (authenticateCustomer(customers, email)) {
                    ToastUtils.showLongToast(this@LoginActivity, "Login Successful")
                    startMainActivity()
                } else {
                    ToastUtils.showLongToast(this@LoginActivity, "Username Or Password Is Incorrect. Please Try Again.")
                }
            } else {
                ToastUtils.showLongToast(this@LoginActivity, "Couldn't Login. Please Try Again.")
            }

            hideLoader()
        }
    }

    private fun authenticateCustomer(
        customers: List<Customer>,
        email: String
    ): Boolean {
        for (customer in customers) {
            if (customer.email == email) {
                SharedPrefsUtils.saveCustomer(this@LoginActivity, customer)
                return true
            }
        }

        return false
    }

    private fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun validateFields() : Boolean {
        return when {
            etEmail.text.isNullOrEmpty() -> false
            else -> !etPassword.text.isNullOrEmpty()
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