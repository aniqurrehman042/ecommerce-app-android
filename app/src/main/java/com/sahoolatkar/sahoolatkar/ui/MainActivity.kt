package com.sahoolatkar.sahoolatkar.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.utils.UIUtils

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        UIUtils.setFullScreen(window)
    }
}