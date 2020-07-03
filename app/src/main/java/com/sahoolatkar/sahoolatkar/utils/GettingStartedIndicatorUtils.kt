package com.sahoolatkar.sahoolatkar.utils

import android.app.Activity
import android.view.View
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.sahoolatkar.sahoolatkar.R

class GettingStartedIndicatorUtils(private val activity: Activity, private val vpGettingStarted: ViewPager) {

    private val views: Array<View> = Array(4) {View(activity)}
    private val indicatorIds: Array<Int> = Array(4) {0}
    private lateinit var selectedIndicator: View

    init {
        findViews()
        setUpIndicatorClickListeners()
        setUpFragmentChangeListener()
    }

    private fun setUpFragmentChangeListener() {
        vpGettingStarted.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                switchIndicator(position)
            }
        })
    }

    fun nextClicked() {
        selectedIndicator = views[0]
    }

    private fun findViews() {
        indicatorIds[0] = R.id.vIndicator1
        indicatorIds[1] = R.id.vIndicator2
        indicatorIds[2] = R.id.vIndicator3
        indicatorIds[3] = R.id.vIndicator4

        for (i in indicatorIds.indices) {
            views[i] = activity.findViewById(indicatorIds[i])
        }
    }

    private fun setUpIndicatorClickListeners() {
        for (i in indicatorIds.indices) {
            views[i].setOnClickListener {
                switchIndicator(i)
            }
        }
    }

    private fun switchIndicator(position: Int) {
        selectedIndicator.background = ContextCompat.getDrawable(activity, R.drawable.bg_dark_grey_circle)
        views[position].background = ContextCompat.getDrawable(activity, R.drawable.bg_red_circle)
        selectedIndicator = views[position]
        vpGettingStarted.currentItem = position
    }

}