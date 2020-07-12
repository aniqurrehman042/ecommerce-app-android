package com.sahoolatkar.sahoolatkar.utils

import android.app.Activity
import android.text.Editable
import android.text.TextWatcher
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_verification.*


class EditTextUtils {

    companion object {

        fun setCnicHyphensAdder(et: EditText, activity: Activity) {
            et.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {

                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (p0 != null && p2 < p3 && (p0.length == 5 || p0.length == 13)) {
                        et.setText("${et.text}-")
                        et.setSelection(et.text.length)
                    } else if (p0 != null && p0.length == 15) {
                        hideKeyboardFrom(activity)
                        et.clearFocus()
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

        fun setToggleStatusbarOnEtFocus(ets : Array<EditText>, window: Window) {
            for (et in ets) {
                et.setOnFocusChangeListener { view, hasFocus ->
                    if (hasFocus) {
                        UIUtils.unsetFullScreen(window)
                    } else {
                        UIUtils.setFullScreen(window)
                    }
                }
            }
        }

        fun hideKeyboardFrom(activity: Activity) {
            val imm: InputMethodManager =
                activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
        }

        fun hideKeyboardOnInputSize(et: EditText, activity: Activity, size: Int) {
            et.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {

                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (p0 != null && p0.length == size) {
                        hideKeyboardFrom(activity)
                        et.clearFocus()
                    }
                }
            })
        }

        fun moveToEtOnInputComplete(et: EditText, nextEt: EditText, size: Int) {
            et.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {

                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (p0 != null && p0.length == size) {
                        et.clearFocus()
                        nextEt.requestFocus()
                    }
                }
            })
        }
    }

}