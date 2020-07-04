package com.sahoolatkar.sahoolatkar.ui

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.utils.SyncEditTextUtils
import com.sahoolatkar.sahoolatkar.utils.UIUtils
import kotlinx.android.synthetic.main.activity_pin_creation.*
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
    }

    private fun continueClick() {
        var pinCode = getCombinedInputFromEtsArray(pCodeEts)
        var pinCodeConfirm = getCombinedInputFromEtsArray(pCodeConfirmEts)

        if (pinCode == pinCodeConfirm) {
            startRegistrationSuccessActivity()
        } else {
            AlertDialog.Builder(this)
                .setTitle("Pin Confirmation")
                .setMessage("Pins don't match. Please try again.")
                .setNeutralButton("Retry", DialogInterface.OnClickListener { dialogInterface, i ->
                    dialogInterface.dismiss()
                }).show()
        }
    }

    private fun getCombinedInputFromEtsArray(etsArray: Array<EditText>): String {
        var combinedInputOfEts = ""

        for (et in etsArray) {
            combinedInputOfEts += et.text
        }

        return combinedInputOfEts
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