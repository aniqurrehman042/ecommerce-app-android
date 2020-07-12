package com.sahoolatkar.sahoolatkar.ui

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.utils.EditTextUtils
import com.sahoolatkar.sahoolatkar.utils.UIUtils
import kotlinx.android.synthetic.main.activity_optional_info.*
import kotlinx.android.synthetic.main.activity_optional_info.ivBack
import kotlinx.android.synthetic.main.activity_optional_info.tvNext
import kotlinx.android.synthetic.main.activity_sign_in_sign_up.*

class OptionalInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_optional_info)
        UIUtils.setFullScreen(window)

        init()
    }

    private fun init() {
        setUpEtsOnFocusStatusBarToggle()
        setListeners()
    }

    private fun setUpEtsOnFocusStatusBarToggle() {
        EditTextUtils.setToggleStatusbarOnEtFocus(arrayOf(etHouseNo, etStreet, etArea, etCity), window)
    }

    private fun setListeners() {
        tvNext.setOnClickListener {
            startWorkInfoActivity()
        }

        tvSkip.setOnClickListener {
            startWorkInfoActivity()
        }

        ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun startWorkInfoActivity() {
        startActivity(Intent(this, WorkInfoActivity::class.java))
    }
}