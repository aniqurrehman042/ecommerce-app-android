package com.sahoolatkar.sahoolatkar.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.utils.UIUtils
import kotlinx.android.synthetic.main.activity_optional_info.*
import kotlinx.android.synthetic.main.activity_optional_info.tvNext

class OptionalInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_optional_info)
        UIUtils.setFullScreen(window)

        init()
    }

    private fun init() {
        setListeners()
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