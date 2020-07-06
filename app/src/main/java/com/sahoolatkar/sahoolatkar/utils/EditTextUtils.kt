package com.sahoolatkar.sahoolatkar.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class EditTextUtils {

    companion object {

        fun setCnicHyphensAdder(et: EditText) {
            et.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {

                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (p0 != null && p2 < p3 && (p0.length == 5 || p0.length == 13)) {
                        et.setText("${et.text}-")
                        et.setSelection(et.text.length)
                    }
                }
            })
        }

        fun getCombinedInputFromEtsArray(etsArray: Array<EditText>): String {
            var combinedInputOfEts = ""

            for (et in etsArray) {
                combinedInputOfEts += et.text
            }

            return combinedInputOfEts
        }
    }

}