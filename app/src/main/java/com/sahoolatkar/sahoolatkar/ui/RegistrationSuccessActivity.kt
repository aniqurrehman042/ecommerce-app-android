package com.sahoolatkar.sahoolatkar.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.utils.UIUtils
import kotlinx.android.synthetic.main.activity_verification.*

class RegistrationSuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_success)
        UIUtils.setFullScreen(window)

        init()
    }

    private fun init() {
        setListeners()
    }

    private fun setListeners() {
        tvContinue.setOnClickListener {
            startMainActivity()
        }

        ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}