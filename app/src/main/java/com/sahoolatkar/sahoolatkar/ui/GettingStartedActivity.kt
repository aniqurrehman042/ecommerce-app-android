package com.sahoolatkar.sahoolatkar.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentPagerAdapter
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.adapters.GettingStartedPagerAdapter
import com.sahoolatkar.sahoolatkar.utils.GettingStartedIndicatorUtils
import com.sahoolatkar.sahoolatkar.utils.SharedPrefsUtils
import com.sahoolatkar.sahoolatkar.utils.UIUtils
import kotlinx.android.synthetic.main.activity_getting_started.*

class GettingStartedActivity : AppCompatActivity() {

    private lateinit var gettingStartedPagerAdapter: GettingStartedPagerAdapter
    private lateinit var gettingStartedIndicatorUtils: GettingStartedIndicatorUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_getting_started)
        UIUtils.setFullScreen(window)
        gettingStartedIndicatorUtils = GettingStartedIndicatorUtils(this, vpGettingStarted)

        findViews()
        init()
    }

    private fun findViews() {

    }

    private fun init() {
        setupViewPager()
        setClickListeners()
    }

    private fun setClickListeners() {
        llNext.setOnClickListener {
            when {
                llWelcome.visibility == View.VISIBLE -> {
                    llWelcome.visibility = View.GONE
                    ivWelcome.visibility = View.GONE
                    gettingStartedIndicatorUtils.nextClicked()
                    llIndicators.visibility = View.VISIBLE
                }
                vpGettingStarted.currentItem < 3 -> {
                    vpGettingStarted.currentItem = vpGettingStarted.currentItem + 1
                }
                else -> {
                    startSignInSignUpActivity()
                }
            }
        }

        llSkip.setOnClickListener {
            startSignInSignUpActivity()
        }
    }

    private fun startSignInSignUpActivity() {
        SharedPrefsUtils.setFirstRun(this)
        startActivity(Intent(this, SignInSignUpActivity::class.java))
    }

    private fun setupViewPager() {
        gettingStartedPagerAdapter = GettingStartedPagerAdapter(
            supportFragmentManager,
            FragmentPagerAdapter.POSITION_UNCHANGED
        )
        vpGettingStarted.adapter = gettingStartedPagerAdapter
    }
}