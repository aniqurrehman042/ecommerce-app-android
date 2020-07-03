package com.sahoolatkar.sahoolatkar.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.utils.UIUtils
import com.sahoolatkar.sahoolatkar.utils.ViewUtils
import kotlinx.android.synthetic.main.activity_work_info.*

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
    }
}