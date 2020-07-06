package com.sahoolatkar.sahoolatkar.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.utils.AlertDialogUtils
import com.sahoolatkar.sahoolatkar.utils.EditTextUtils
import com.sahoolatkar.sahoolatkar.utils.SyncEditTextUtils
import com.sahoolatkar.sahoolatkar.utils.UIUtils
import kotlinx.android.synthetic.main.activity_pin_creation.*
import kotlinx.android.synthetic.main.activity_pin_creation.ivBack
import kotlinx.android.synthetic.main.activity_verification.tvContinue

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
        setListeners()
        setupIndexLineNavigation()
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
    }

    private fun continueClick() {
        var pinCode = EditTextUtils.getCombinedInputFromEtsArray(pCodeEts)
        var pinCodeConfirm = EditTextUtils.getCombinedInputFromEtsArray(pCodeConfirmEts)

        when {
            pinCode.length < 4 -> {
                AlertDialogUtils.showAlertWithMessage("Please enter a pin code", this)
            }
            pinCode != pinCodeConfirm -> {
                AlertDialogUtils.showAlertWithMessage("Pins don't match. Please try again.", this)
            }
            else -> {
                startRegistrationSuccessActivity()
            }
        }
    }

    private fun startRegistrationSuccessActivity() {
        startActivity(Intent(this, RegistrationSuccessActivity::class.java))
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