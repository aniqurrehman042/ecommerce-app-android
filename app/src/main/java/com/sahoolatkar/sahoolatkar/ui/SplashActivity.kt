package com.sahoolatkar.sahoolatkar.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.utils.UIUtils

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        UIUtils.setFullScreen(window)

        init()
    }

    private fun init() {
        setTimeoutForNextActivity()
    }

    private fun setTimeoutForNextActivity() {
        Handler().postDelayed(Runnable() {
            startActivity(Intent(this@SplashActivity, GettingStartedActivity::class.java))
        }, 2000)
    }
}