package com.sahoolatkar.sahoolatkar.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.utils.EditTextUtils
import com.sahoolatkar.sahoolatkar.utils.UIUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sign_in_sign_up.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        UIUtils.setFullScreen(window)

        init()
    }

    private fun init() {
//        setUpEtsOnFocusStatusBarToggle()
        setUpBottomBar()
        setListeners()
    }

    private fun setUpEtsOnFocusStatusBarToggle() {
        EditTextUtils.setToggleStatusbarOnEtFocus(arrayOf(etSearch), window)
    }

    private fun setListeners() {
        ivNotifications.setOnClickListener {
            Navigation.createNavigateOnClickListener(R.id.notificationsFragment)
        }
    }

    private fun setUpBottomBar() {
        bnv_bottom_bar.setupWithNavController(Navigation.findNavController(this, R.id.navHostFragment))
    }
}