package com.sahoolatkar.sahoolatkar.utils

import android.app.Activity
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class SyncEditTextUtils(var activity: Activity, var vCodeEts: Array<EditText>) {

    init {
        for (i in 0..vCodeEts.size - 2)
            vCodeEts[i].addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {

                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (p0!!.isNotEmpty()) {
                        vCodeEts[i + 1].requestFocus()
                    }
                }
            })
    }

}