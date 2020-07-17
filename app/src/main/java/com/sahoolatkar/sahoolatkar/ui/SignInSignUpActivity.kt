package com.sahoolatkar.sahoolatkar.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.utils.EditTextUtils
import com.sahoolatkar.sahoolatkar.utils.UIUtils
import com.sahoolatkar.sahoolatkar.utils.ValidationUtils
import com.sahoolatkar.sahoolatkar.utils.ViewUtils
import kotlinx.android.synthetic.main.activity_sign_in_sign_up.*
import kotlinx.android.synthetic.main.layout_loader.*
import java.util.*


class SignInSignUpActivity : AppCompatActivity() {

    private val dobCalendar = Calendar.getInstance()
    private val cnicExpiryCalendar = Calendar.getInstance()

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
        etFatherName.setOnEditorActionListener { textView: TextView, actionId, keyEvent ->
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
            ViewUtils.showView(llInitialDetails)
            ViewUtils.showView(etCnicNo)
            ViewUtils.showView(tvNext)
            ViewUtils.hideView(etCusId)
            ViewUtils.hideView(llMoreDetails)
            hideCustomerName()
            etCnicNo.text.clear()
        }

        tvExistingCustomer.setOnClickListener {
            ViewUtils.showView(llInitialDetails)
            ViewUtils.showView(etCusId)
            ViewUtils.showView(tvNext)
            ViewUtils.hideView(etCnicNo)
            ViewUtils.hideView(llMoreDetails)
            hideCustomerName()
            etCusId.text.clear()
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
                showLoader("Validating if user already exists")
                Handler().postDelayed(Runnable {
                    showLoader("Verifying CNIC from Nadra")
                    Handler().postDelayed(Runnable {
                        hideLoader()
                        hideTypeInitialShowMore()
                        showCustomerName("Irfan")
                    }, 2000)
                }, 2000)
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
        var verified = true
        val email = etEmail.text.toString()
        val phone = etPhone.text.toString()

        if (email.isEmpty()) {
            etEmail.error = "This field is required"
            verified = false
        } else if (!ValidationUtils.validateEmail(email)) {
            etEmail.error = "Email is not valid"
            verified = false
        }
        if (etFatherName.text.isEmpty()) {
            etFatherName.error = "This field is required"
            verified = false
        }
        if (etDob.text.isEmpty()) {
            etDob.error = "This field is required"
            verified = false
        }
        if (etCnicExpiry.text.isEmpty()) {
            etCnicExpiry.error = "This field is required"
            verified = false
        }
        if (phone.isEmpty()) {
            etPhone.error = "This field is required"
            verified = false
        } else if (!ValidationUtils.validatePakPhoneNo(phone)) {
            etPhone.error = "Phone number is not valid in Pakistan"
            verified = false
        }

        if (verified) {
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