package com.sahoolatkar.sahoolatkar.utils

import android.content.Context
import android.widget.Toast

class ToastUtils {
    companion object {

        fun showLongToast(context: Context, text: String) {
            Toast.makeText(
                context,
                text,
                Toast.LENGTH_LONG
            ).show()
        }

        fun showShortToast(context: Context, text: String) {
            Toast.makeText(
                context,
                text,
                Toast.LENGTH_SHORT
            ).show()
        }

    }
}