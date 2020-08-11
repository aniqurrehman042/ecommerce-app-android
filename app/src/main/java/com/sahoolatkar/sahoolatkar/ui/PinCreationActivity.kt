package com.sahoolatkar.sahoolatkar.ui

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.api_utils.SahoolatKarApiUtils
import com.sahoolatkar.sahoolatkar.utils.*
import kotlinx.android.synthetic.main.activity_pin_creation.*
import kotlinx.android.synthetic.main.activity_pin_creation.ivBack
import kotlinx.android.synthetic.main.activity_verification.tvContinue
import kotlinx.coroutines.launch

class PinCreationActivity : AppCompatActivity() {

    private lateinit var pCodeEts: Array<EditText>
    private lateinit var pCodeConfirmEts: Array<EditText>
    private lateinit var syncPCodeEtsUtils: SyncEditTextUtils
    private lateinit var syncPCodeConfirmEtsUtils: SyncEditTextUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin_creation)
        UIUtils.setFullScreen(window)

        init()
    }

    private fun init() {
        setupIndexLineNavigation()
        setUpEtsOnFocusStatusBarToggle()
        setListeners()
    }

    private fun setUpEtsOnFocusStatusBarToggle() {
        EditTextUtils.setToggleStatusbarOnEtFocus(pCodeEts + pCodeConfirmEts, window)
    }

    private fun syncEditTexts() {
        syncPCodeEtsUtils = SyncEditTextUtils(this, pCodeEts)
        syncPCodeConfirmEtsUtils = SyncEditTextUtils(this, pCodeConfirmEts)
    }

    private fun setListeners() {
        tvContinue.setOnClickListener {
            continueClick()
        }

        ivBack.setOnClickListener {
            onBackPressed()
        }

        EditTextUtils.moveToEtOnInputComplete(etPCode4, etPCodeConfirm1, 1)
        EditTextUtils.hideKeyboardOnInputSize(etPCodeConfirm4, this, 1)
    }

    private fun continueClick() {
        if (validateFields()) {

            lifecycleScope.launch {
                val customer = SharedPrefsUtils.getCustomer(this@PinCreationActivity)
                if (customer != null) {
                    customer.pin = EditTextUtils.getCombinedInputFromEtsArray(pCodeEts)
                    val response = SahoolatKarApiUtils.createCustomerPin(customer)

                    if (response.isSuccessful) {
                        if (response.body() != null && response.body()!!) {
                            SharedPrefsUtils.saveCustomerPin(this@PinCreationActivity, customer.pin)
                            startRegistrationSuccessActivity()
                        } else {
                            ToastUtils.showLongToast(
                                this@PinCreationActivity,
                                "Failed To Create Pin. Please Try Again."
                            )
                        }
                    } else {
                        ToastUtils.showLongToast(
                            this@PinCreationActivity,
                            "Failed To Create Pin. Please Try Again."
                        )
                    }
                } else {
                    ToastUtils.showLongToast(
                        this@PinCreationActivity,
                        "Failed To Register. Please Try Again."
                    )
                    SharedPrefsUtils.deleteCustomer(this@PinCreationActivity)
                    startSignInSignUpActivity()
                }
            }
        }
    }

    private fun validateFields(): Boolean {
        val pinCode = EditTextUtils.getCombinedInputFromEtsArray(pCodeEts)
        val pinCodeConfirm = EditTextUtils.getCombinedInputFromEtsArray(pCodeConfirmEts)

        return when {
            pinCode.length < 4 -> {
                AlertDialogUtils.showAlertWithMessage("Please enter a pin code", this)
                false
            }
            pinCode != pinCodeConfirm -> {
                AlertDialogUtils.showAlertWithMessage("Pins don't match. Please try again.", this)
                false
            }
            else -> {
                true
            }
        }
    }
    private fun startSignInSignUpActivity() {
        startActivity(Intent(this, SignInSignUpActivity::class.java))
        finish()
    }

    private fun startRegistrationSuccessActivity() {
        startActivity(Intent(this, RegistrationSuccessActivity::class.java))
        finish()
    }

    private fun setupIndexLineNavigation() {
        populateEtsArrays()
        syncEditTexts()
    }

    private fun populateEtsArrays() {
        pCodeEts = Array(4) { EditText(this) }
        pCodeEts[0] = etPCode1
        pCodeEts[1] = etPCode2
        pCodeEts[2] = etPCode3
        pCodeEts[3] = etPCode4

        pCodeConfirmEts = Array(4) { EditText(this) }
        pCodeConfirmEts[0] = etPCodeConfirm1
        pCodeConfirmEts[1] = etPCodeConfirm2
        pCodeConfirmEts[2] = etPCodeConfirm3
        pCodeConfirmEts[3] = etPCodeConfirm4
    }
}