package com.sahoolatkar.sahoolatkar.ui

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.utils.DateUtils
import com.sahoolatkar.sahoolatkar.utils.UIUtils
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
        setListeners()
    }

    private fun setListeners() {
        tvNext.setOnClickListener {
            if (ViewUtils.isVisible(llInitialDetails)) {
                validateInitialDetailsAndProceed()
            } else if (ViewUtils.isVisible(llMoreDetails)) {
                validateMoreDetailsAndProceed()
            }

        }

        tvNewCustomer.setOnClickListener {
            ViewUtils.showView(llInitialDetails)
            ViewUtils.showView(etCnicNo)
            ViewUtils.hideView(etCusId)
            ViewUtils.hideView(llMoreDetails)
            hideCustomerName()
            etCnicNo.text.clear()
        }

        tvExistingCustomer.setOnClickListener {
            ViewUtils.showView(llInitialDetails)
            ViewUtils.showView(etCusId)
            ViewUtils.hideView(etCnicNo)
            ViewUtils.hideView(llMoreDetails)
            hideCustomerName()
            etCusId.text.clear()
        }

        ViewUtils.setDatePicker(etDob, this)

        ViewUtils.setDatePicker(etCnicExpiry, this)
    }

    private fun validateInitialDetailsAndProceed() {
        if (ViewUtils.isVisible(etCnicNo) && etCnicNo.text.isNotEmpty()) {
            showLoader("Validating if user already exists")
            Handler().postDelayed(Runnable {
                showLoader("Verifying CNIC from Nadra")
                Handler().postDelayed(Runnable {
                    hideLoader()
                    ViewUtils.hideView(llInitialDetails)
                    ViewUtils.showView(llMoreDetails)
                    showCustomerName("Irfan")
                }, 2000)
            }, 2000)
        } else if (ViewUtils.isVisible(etCusId) && etCusId.text.isNotEmpty()) {
            showLoader("Validating if user exists")
            Handler().postDelayed(Runnable {
                hideLoader()
                ViewUtils.hideView(llInitialDetails)
                ViewUtils.showView(llMoreDetails)
                showCustomerName("Irfan")
            }, 2000)
        } else {
            etCnicNo.error = "This field is required"
            etCusId.error = "This field is required"
        }
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
        var verified: Boolean = true
        if (etEmail.text.isEmpty()) {
            etEmail.error = "This field is required"
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
        if (etPhone.text.isEmpty()) {
            etPhone.error = "This field is required"
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
}