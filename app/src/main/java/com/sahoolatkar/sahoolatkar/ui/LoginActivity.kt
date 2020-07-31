package com.sahoolatkar.sahoolatkar.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sahoolatkar.sahoolatkar.R
import kotlinx.android.synthetic.main.activity_login.*

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
    }

    private fun login() {
        if (validateFields()) {

        }
    }

    private fun validateFields() : Boolean {
        return when {
            etEmail.text.isNullOrEmpty() -> false
            else -> !etPassword.text.isNullOrEmpty()
        }
    }
}