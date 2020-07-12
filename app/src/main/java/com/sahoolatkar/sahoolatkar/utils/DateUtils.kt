package com.sahoolatkar.sahoolatkar.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateUtils {

    companion object {

        fun stringToDateWithTime(dateString: String?): Date? {
            var date: Date? = null
            val dotUnicode = 0x00B7
            val format =
                SimpleDateFormat("dd/MM/yy " + dotUnicode.toChar() + " hh:mm a", Locale.US)
            try {
                date = format.parse(dateString)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return date
        }

        fun timeStringToDate(dateString: String?): Date? {
            var date: Date? = null
            val format = SimpleDateFormat("hh:mm a", Locale.US)
            try {
                date = format.parse(dateString)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return date
        }

        fun stringToDate(dateString: String?): Date? {
            var date: Date? = null
            val format = SimpleDateFormat("dd/MM/yy", Locale.US)
            try {
                date = format.parse(dateString)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return date
        }

        fun stringToOfferDate(dateString: String?): Date? {
            var date: Date? = null
            val format = SimpleDateFormat("MM/dd/yy", Locale.US)
            try {
                date = format.parse(dateString)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return date
        }

        fun dateToStringWithTime(date: Date?): String? {
            val dotUnicode = 0x00B7
            val format =
                SimpleDateFormat("dd/MM/yy " + dotUnicode.toChar() + " hh:mm a", Locale.US)
            return format.format(date)
        }

        fun dateToTimeString(date: Date?): String? {
            val format = SimpleDateFormat("hh:mm a", Locale.US)
            return format.format(date)
        }

        fun dateToString(date: Date?): String? {
            val format = SimpleDateFormat("dd/MM/yy", Locale.US)
            return format.format(date)
        }

        fun dateToOfferString(date: Date?): String? {
            val format = SimpleDateFormat("MM/dd/yy", Locale.US)
            return format.format(date)
        }

        fun getCurrentDateInString(): String? {
            val date: Date = Calendar.getInstance().getTime()
            val format = SimpleDateFormat("dd/MM/yy - hh:mm a", Locale.US)
            return format.format(date)
        }

        fun stringToCalendar(dateString: String) : Calendar {
            val cal = Calendar.getInstance()
            val sdf =
                SimpleDateFormat("dd/MM/yy", Locale.US)
            cal.time = sdf.parse(dateString)!!
            return cal
        }
    }
}