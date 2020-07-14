package com.sahoolatkar.sahoolatkar.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.utils.EditTextUtils
import com.sahoolatkar.sahoolatkar.utils.UIUtils
import kotlinx.android.synthetic.main.activity_main.*

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

        var navOptions = NavOptions.Builder()
            .setEnterAnim(R.anim.enter_from_left)
            .setExitAnim(R.anim.exit_to_left)
            .build()

        ivNotifications.setOnClickListener {
            Navigation.findNavController(this@MainActivity, R.id.navHostFragment).navigate(R.id.notificationsFragment, null, navOptions, null)
        }
    }

    private fun setUpBottomBar() {
        bnv_bottom_bar.setupWithNavController(Navigation.findNavController(this, R.id.navHostFragment))
    }
}