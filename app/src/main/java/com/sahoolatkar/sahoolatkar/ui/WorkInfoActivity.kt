package com.sahoolatkar.sahoolatkar.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.utils.UIUtils
import com.sahoolatkar.sahoolatkar.utils.ViewUtils
import kotlinx.android.synthetic.main.activity_work_info.*
import kotlinx.android.synthetic.main.activity_work_info.ivBack
import kotlinx.android.synthetic.main.activity_work_info.tvNext

class WorkInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_info)
        UIUtils.setFullScreen(window)

        init()
    }

    private fun init() {
        setListeners()
    }

    private fun setListeners() {
        ViewUtils.setDatePicker(etPaycheckDate, this)

        tvNext.setOnClickListener {
            startPinCreationActivity()
        }

        tvSkip.setOnClickListener {
            startPinCreationActivity()
        }

        ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun startPinCreationActivity() {
        startActivity(Intent(this, PinCreationActivity::class.java))
    }
}