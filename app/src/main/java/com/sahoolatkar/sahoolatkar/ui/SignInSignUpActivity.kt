package com.sahoolatkar.sahoolatkar.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.api_models.NadraResponse
import com.sahoolatkar.sahoolatkar.api_models.customer.Customer
import com.sahoolatkar.sahoolatkar.api_models.order.Billing
import com.sahoolatkar.sahoolatkar.api_models.order.Shipping
import com.sahoolatkar.sahoolatkar.api_utils.SahoolatKarApiUtils
import com.sahoolatkar.sahoolatkar.utils.*
import kotlinx.android.synthetic.main.activity_sign_in_sign_up.*
import kotlinx.android.synthetic.main.layout_loader.*
import kotlinx.coroutines.launch


class SignInSignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in_sign_up)
        UIUtils.setFullScreen(window)

        init()
    }

    private fun init() {
        setUpEtsOnFocusStatusBarToggle()
        setListeners()
    }

    private fun setUpEditTextNavigation() {
        etFatherName.setOnEditorActionListener { _: TextView, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                EditTextUtils.hideKeyboardFrom(this)
                ViewUtils.showDatePicker(etDob, this)
                etFatherName.clearFocus()
            }
            true
        }
    }

    private fun setUpEtsOnFocusStatusBarToggle() {
        EditTextUtils.setToggleStatusbarOnEtFocus(
            arrayOf(
                etCnicNo,
                etCusId,
                etEmail,
                etFatherName,
                etDob,
                etCnicExpiry,
                etPhone
            ), window
        )
    }

    private fun setListeners() {
        tvNext.setOnClickListener {
            nextClick()
        }

        tvNewCustomer.setOnClickListener {
            initialDetailsClick(true)
        }

        tvExistingCustomer.setOnClickListener {
            initialDetailsClick(false)
        }

        ivBack.setOnClickListener {
            onBackPressed()
        }

        setUpHideKeyboardOnPhoneInput()

        EditTextUtils.setCnicHyphensAdder(etCnicNo, this)

        setUpEditTextNavigation()

        ViewUtils.setDatePicker(etDob, this)

        ViewUtils.setDatePicker(etCnicExpiry, this)

        llSignInSignUp.setOnClickListener {
            EditTextUtils.hideKeyboardFrom(this)
        }
    }

    private fun initialDetailsClick(newCustomer: Boolean) {
        if (newCustomer) {
            ViewUtils.showView(etCnicNo)
            ViewUtils.hideView(etCusId)
            etCnicNo.text.clear()
        } else {
            ViewUtils.showView(etCusId)
            ViewUtils.hideView(etCnicNo)
            etCusId.text.clear()
        }
        ViewUtils.showView(llInitialDetails)
        ViewUtils.showView(tvNext)
        ViewUtils.hideView(llMoreDetails)
        hideCustomerName()
    }

    private fun setUpHideKeyboardOnPhoneInput() {
        EditTextUtils.hideKeyboardOnInputSize(etPhone, this@SignInSignUpActivity, 11)
    }

    private fun nextClick() {
        if (ViewUtils.isVisible(llInitialDetails)) {
            validateInitialDetailsAndProceed()
        } else if (ViewUtils.isVisible(llMoreDetails)) {
            validateMoreDetailsAndProceed()
        }
    }

    private fun validateInitialDetailsAndProceed() {

        val cnicNo = etCnicNo.text.toString()
        val cusId = etCusId.text.toString()

        if (ViewUtils.isVisible(etCnicNo)) {
            if (cnicNo.isEmpty()) {
                etCnicNo.error = "This field is required"
            } else if (!ValidationUtils.validateCnic(cnicNo)) {
                etCnicNo.error = "CNIC format is not valid"
            } else {

                lifecycleScope.launch {

                    showLoader("Verifying if user already exists")

                    val customerRegisteredResponse = SahoolatKarApiUtils.isCustomerRegistered(cnicNo)

                    if (customerRegisteredResponse.isSuccessful) {
                        if (customerRegisteredResponse.body()!!) {

                        } else {
                            showLoader("Verifying CNIC from Nadra")

                            val nadraDetailsResponse = SahoolatKarApiUtils.getNadraDetails(cnicNo)
                            if (nadraDetailsResponse.isSuccessful) {
                                val nadraResponse: NadraResponse = nadraDetailsResponse.body()!!
                                when (nadraResponse.name) {
                                    "not found" -> {
                                        ToastUtils.showLongToast(
                                            this@SignInSignUpActivity,
                                            "Cnic doesn't exist."
                                        )
                                    }

                                    "no cnic" -> {
                                        ToastUtils.showLongToast(
                                            this@SignInSignUpActivity,
                                            "Cnic wasn't received by Nadra server."
                                        )
                                    }

                                    else -> {
                                        sendCreateCustomerRequest(nadraResponse)
                                    }
                                }
                            } else {
                                ToastUtils.showLongToast(
                                    this@SignInSignUpActivity,
                                    "Failed to get data from Nadra. Please try again."
                                )
                            }
                            hideLoader()
                        }

                    } else {

                    }
                }
            }
        } else if (ViewUtils.isVisible(etCusId) && cusId.isNotEmpty()) {
            showLoader("Validating if user exists")
            Handler().postDelayed(Runnable {
                hideLoader()
                hideTypeInitialShowMore()
                showCustomerName("Irfan")
            }, 2000)
        } else {
            etCusId.error = "This field is required"
        }
    }

    private fun sendCreateCustomerRequest(nadraResponse: NadraResponse) {
        val firstName = nadraResponse.name
        val lastName = ""
        val email = etEmail.text.toString()
        val cnic = etCnicNo.text.toString()
        val address = nadraResponse.confirmAddress
        val city = "Lahore"
        val province = "Punjab"
        val phone = etPhone.text.toString()

        val password = PasswordUtils.getRandomPassword()

        val billing =
            Billing(firstName, lastName, address, "", city, province, "", "Pakistan", email, phone)
        val shipping = Shipping(firstName, lastName, address, "", city, province, "", "Pakistan")

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
                    this@SignInSignUpActivity,
                    "Registered Successfully. Please Create Your Pin."
                )
                customer.id = response.body()!!.id
                SharedPrefsUtils.saveCustomer(this@SignInSignUpActivity, customer)
                startPinCreationActivity()
            } else {
                ToastUtils.showLongToast(
                    this@SignInSignUpActivity,
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

    private fun hideTypeInitialShowMore() {
        ViewUtils.hideView(llInitialDetails)
        ViewUtils.hideView(llCustomerType)
        ViewUtils.showView(llMoreDetails)
    }

    private fun showCustomerName(name: String) {
        ViewUtils.showView(tvCusNameHeading)
        tvMainHeading.text = "Welcome"
        tvCusNameHeading.text = name
    }

    private fun hideCustomerName() {
        tvMainHeading.text = "REGISTRATION"
        ViewUtils.hideView(tvCusNameHeading)
    }

    private fun showLoader(loadingText: String) {
        ViewUtils.showView(llLoader)
        tvLoadingMsg.text = loadingText
        disableUserInteraction()
    }

    private fun hideLoader() {
        ViewUtils.hideView(llLoader)
        tvLoadingMsg.text = "Loading..."
        enableUserInteraction()
    }

    private fun enableUserInteraction() {
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    private fun disableUserInteraction() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
    }

    private fun validateMoreDetailsAndProceed() {
        var validationSuccess = true
        val email = etEmail.text.toString()
        val phone = etPhone.text.toString()

        if (email.isEmpty()) {
            etEmail.error = "This field is required"
            validationSuccess = false
        } else if (!ValidationUtils.validateEmail(email)) {
            etEmail.error = "Email is not valid"
            validationSuccess = false
        }
//        if (etFatherName.text.isEmpty()) {
//            etFatherName.error = "This field is required"
//            verified = false
//        }
//        if (etDob.text.isEmpty()) {
//            etDob.error = "This field is required"
//            verified = false
//        }
//        if (etCnicExpiry.text.isEmpty()) {
//            etCnicExpiry.error = "This field is required"
//            verified = false
//        }
        if (phone.isEmpty()) {
            etPhone.error = "This field is required"
            validationSuccess = false
        } else if (!ValidationUtils.validatePakPhoneNo(phone)) {
            etPhone.error = "Phone number is not valid in Pakistan"
            validationSuccess = false
        }

        if (validationSuccess) {
            showLoader("Verifying Details")
            Handler().postDelayed(Runnable {
                hideLoader()
                startVerificationActivity()
            }, 2000)
        }
    }

    private fun startVerificationActivity() {
        startActivity(Intent(this@SignInSignUpActivity, VerificationActivity::class.java))
    }

    override fun onBackPressed() {
        if (ViewUtils.isVisible(llMoreDetails)) {
            setInitialViews()
            hideCustomerName()
            return
        }

        super.onBackPressed()
    }

    private fun setInitialViews() {
        ViewUtils.hideView(llMoreDetails)
        ViewUtils.showView(llCustomerType)
        ViewUtils.hideView(tvNext)
    }
}