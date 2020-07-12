package com.sahoolatkar.sahoolatkar.utils

import android.app.DatePickerDialog
import android.content.Context
import android.view.View
import android.widget.EditText
import java.util.*

class ViewUtils {
    companion object {
        fun showView(view: View) {
            view.visibility = View.VISIBLE
        }

        fun hideView(view: View) {
            view.visibility = View.GONE
        }

        fun isVisible(view: View): Boolean = view.visibility == View.VISIBLE

        fun setDatePicker(editText: EditText, context: Context) {
            editText.setOnClickListener {
                showDatePicker(editText, context)
            }
        }

        fun showDatePicker(editText: EditText, context: Context) {
            val text = editText.text.toString()
            var calendar: Calendar =
                if (text.isEmpty()) Calendar.getInstance()
                else DateUtils.stringToCalendar(text)
            DatePickerDialog(
                context,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    calendar.set(Calendar.YEAR, year)
                    calendar.set(Calendar.MONTH, monthOfYear)
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    editText.setText(DateUtils.dateToString(calendar.time))
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }
}