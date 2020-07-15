package com.sahoolatkar.sahoolatkar.ui

import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.utils.EditTextUtils
import com.sahoolatkar.sahoolatkar.utils.UIUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_menu.*

class MainActivity : AppCompatActivity() {

    private lateinit var animEnterFromLeft: Animation
    private lateinit var animExitToLeft: Animation
    private lateinit var animFadeIn: Animation
    private lateinit var animFadeOut: Animation
    private var menuOpen = false

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
        setMenuAnimations()
    }

    private fun setMenuAnimations() {
        animEnterFromLeft =
            AnimationUtils.loadAnimation(this, R.anim.enter_from_left).apply { fillAfter = true }
        animExitToLeft =
            AnimationUtils.loadAnimation(this, R.anim.exit_to_left).apply { fillAfter = true; setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(p0: Animation?) {

                }

                override fun onAnimationEnd(p0: Animation?) {
                    clMenuContainer.visibility = View.GONE
                }

                override fun onAnimationStart(p0: Animation?) {

                }
            }) }
        animFadeIn = AlphaAnimation(0f, 1f).apply { fillAfter = true; duration = 500 }
        animFadeOut = AlphaAnimation(1f, 0f).apply { fillAfter = true; duration = 500 }
        val animExitToLeftFast =
            AnimationUtils.loadAnimation(this, R.anim.exit_to_left_fast).apply { fillAfter = true }
        val animFadeOutFast = AlphaAnimation(1f, 0f).apply { fillAfter = true; duration = 0 }

        clMenu.startAnimation(animExitToLeftFast)
        vOverlay.startAnimation(animFadeOutFast)
        clMenuContainer.visibility = View.GONE
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
            Navigation.findNavController(this@MainActivity, R.id.navHostFragment)
                .navigate(R.id.notificationsFragment, null, navOptions, null)
        }

        ivMenu.setOnClickListener {
            if (menuOpen) {
                closeMenu()
            } else {
                openMenu()
            }
        }

        vPlaceholder.setOnClickListener {
            closeMenu()
        }

    }

    private fun openMenu() {
        clMenu.startAnimation(animEnterFromLeft)
        vOverlay.startAnimation(animFadeIn)
        clMenuContainer.visibility = View.VISIBLE
        menuOpen = true
    }

    private fun closeMenu() {
        clMenu.startAnimation(animExitToLeft)
        vOverlay.startAnimation(animFadeOut)
        menuOpen = false
    }

    private fun setUpBottomBar() {
        bnv_bottom_bar.setupWithNavController(
            Navigation.findNavController(
                this,
                R.id.navHostFragment
            )
        )
    }
}