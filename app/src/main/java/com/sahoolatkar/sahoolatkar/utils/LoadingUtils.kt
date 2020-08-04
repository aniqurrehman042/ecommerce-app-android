package com.sahoolatkar.sahoolatkar.utils

import android.app.Activity
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import com.sahoolatkar.sahoolatkar.R

class LoadingUtils {

    companion object {

        fun showLoader(activity: Activity, llLoader: View, loadingText: String) {
            ViewUtils.showView(llLoader)
            llLoader.findViewById<TextView>(R.id.tvLoadingMsg).text = loadingText
            disableUserInteraction(activity)
        }

        fun hideLoader(activity: Activity, llLoader: View) {
            ViewUtils.hideView(llLoader)
            enableUserInteraction(activity)
        }

        fun disableUserInteraction(activity: Activity) {
            activity.window.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )
        }

        fun enableUserInteraction(activity: Activity) {
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }

    }

}