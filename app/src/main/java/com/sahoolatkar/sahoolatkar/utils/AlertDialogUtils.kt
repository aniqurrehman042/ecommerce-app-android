package com.sahoolatkar.sahoolatkar.utils

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog

class AlertDialogUtils {

    companion object {

        fun showAlertWithMessage(message: String, context: Context) {
            AlertDialog.Builder(context)
                .setTitle("Pin Confirmation")
                .setMessage(message)
                .setNeutralButton("Retry") { dialogInterface, _ ->
                    dialogInterface.dismiss()
                }.show()
        }

    }

}