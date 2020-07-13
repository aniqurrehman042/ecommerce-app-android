package com.sahoolatkar.sahoolatkar.ui

import ProductApiModel
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.WindowInsetsController
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.http_clients.SahoolatkarClient
import com.sahoolatkar.sahoolatkar.utils.UIUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        UIUtils.setFullScreen(window)

        init()
    }

    private fun init() {
        hideNavigationBar()
        setTimeoutForNextActivity()
        Log.d("ret response", "Started")
        SahoolatkarClient.getProductService().getAllProducts()?.enqueue(object : Callback<List<ProductApiModel?>?> {
            override fun onFailure(call: Call<List<ProductApiModel?>?>, t: Throwable) {
                Log.d("ret response", t.message.toString())
                Toast.makeText(this@SplashActivity, t.message.toString(), Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<List<ProductApiModel?>?>,
                response: Response<List<ProductApiModel?>?>
            ) {
                Log.d("ret response", response.message())
                Toast.makeText(this@SplashActivity, response.message(), Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun hideNavigationBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            window.insetsController!!.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_BARS_BY_SWIPE
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        }
    }

    private fun setTimeoutForNextActivity() {
        Handler().postDelayed(Runnable() {
            startActivity(Intent(this@SplashActivity, GettingStartedActivity::class.java))
        }, 2000)
    }
}