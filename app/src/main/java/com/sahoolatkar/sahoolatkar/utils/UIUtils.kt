package com.sahoolatkar.sahoolatkar.utils

import android.view.Window
import android.view.WindowManager

class UIUtils {

    companion object {
        public fun setFullScreen(window: Window) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }
}