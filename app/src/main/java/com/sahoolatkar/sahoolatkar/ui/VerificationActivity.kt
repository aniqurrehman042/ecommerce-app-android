package com.sahoolatkar.sahoolatkar.ui

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.utils.AlertDialogUtils
import com.sahoolatkar.sahoolatkar.utils.EditTextUtils
import com.sahoolatkar.sahoolatkar.utils.SyncEditTextUtils
import com.sahoolatkar.sahoolatkar.utils.UIUtils
import kotlinx.android.synthetic.main.activity_verification.*

class VerificationActivity : AppCompatActivity() {

    private lateinit var vCodeEts: Array<EditText>
    private lateinit var syncVCodeUtils: SyncEditTextUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)
        UIUtils.setFullScreen(window)

        init()
    }

    private fun init() {
        setListeners()
        setupIndexLineNavigation()
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
        if (EditTextUtils.getCombinedInputFromEtsArray(vCodeEts).length < 4) {
            AlertDialogUtils.showAlertWithMessage("Please enter a pin code.", this)
        } else {
            startOptionalInfoActivity()
        }
    }

    private fun startOptionalInfoActivity() {
        startActivity(Intent(this, OptionalInfoActivity::class.java))
    }

    private fun setupIndexLineNavigation() {

        populateEtsArray()
        syncEditTexts()
    }

    private fun syncEditTexts() {
        syncVCodeUtils = SyncEditTextUtils(this, vCodeEts)
    }

    private fun populateEtsArray() {
        vCodeEts = Array(4) { EditText(this) }
        vCodeEts[0] = etVCode1
        vCodeEts[1] = etVCode2
        vCodeEts[2] = etVCode3
        vCodeEts[3] = etVCode4
    }
}