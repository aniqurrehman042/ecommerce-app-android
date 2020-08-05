package com.sahoolatkar.sahoolatkar.utils

import android.annotation.SuppressLint
import android.content.Context
import com.sahoolatkar.sahoolatkar.api_models.customer.Customer
import com.sahoolatkar.sahoolatkar.api_models.shared.Billing
import com.sahoolatkar.sahoolatkar.api_models.shared.Shipping
import com.sahoolatkar.sahoolatkar.globals.GlobalVariables

class SharedPrefsUtils {
    companion object {

        private val customerKeys = arrayOf(GlobalVariables.CUSTOMER_ID, GlobalVariables.CUSTOMER_FIRST_NAME, GlobalVariables.CUSTOMER_LAST_NAME,
            GlobalVariables.CUSTOMER_EMAIL, GlobalVariables.CUSTOMER_USERNAME,
            GlobalVariables.CUSTOMER_CNIC, GlobalVariables.CUSTOMER_PIN,
            GlobalVariables.CUSTOMER_ADDRESS, GlobalVariables.CUSTOMER_CITY,
            GlobalVariables.CUSTOMER_STATE, GlobalVariables.CUSTOMER_PHONE)

        @SuppressLint("ApplySharedPref")
        fun saveCustomer(context: Context, customer: Customer) {
            val sharedPreferences = context.getSharedPreferences(GlobalVariables.CUSTOMER_PREFS_KEY, Context.MODE_PRIVATE).edit()
            val values = arrayOf(customer.id.toString(), customer.firstName, customer.lastName, customer.email, customer.username, customer.cnic, customer.pin,
                customer.billing.address_1, customer.billing.city, customer.billing.state, customer.billing.phone)

            for (i in customerKeys.indices) {
                sharedPreferences.putString(customerKeys[i], values[i])
            }

            sharedPreferences.commit()
        }

        fun deleteCustomer(context: Context) {
            val sharedPreferences = context.getSharedPreferences(GlobalVariables.CUSTOMER_PREFS_KEY, Context.MODE_PRIVATE).edit()

            for (key in customerKeys) {
                sharedPreferences.putString(key, "")
            }

            sharedPreferences.apply()
        }

        fun getCustomer(context: Context): Customer? {
            val sharedPreferences = context.getSharedPreferences(GlobalVariables.CUSTOMER_PREFS_KEY, Context.MODE_PRIVATE)
            val customerValues: MutableMap<String, String> = HashMap()
            for (key in customerKeys) {
                val value = sharedPreferences.getString(key, "")
                if (!value.isNullOrEmpty() || key == GlobalVariables.CUSTOMER_PIN) {
                    customerValues[key] = value!!
                } else {
                    return null
                }
            }

            val billing = Billing(
                customerValues[GlobalVariables.CUSTOMER_FIRST_NAME]!!,
                customerValues[GlobalVariables.CUSTOMER_LAST_NAME]!!,
                customerValues[GlobalVariables.CUSTOMER_ADDRESS]!!,
                "",
                customerValues[GlobalVariables.CUSTOMER_CITY]!!,
                customerValues[GlobalVariables.CUSTOMER_STATE]!!,
                "",
                "Pakistan",
                customerValues[GlobalVariables.CUSTOMER_EMAIL]!!,
                customerValues[GlobalVariables.CUSTOMER_PHONE]!!
            )
            val shipping =
                Shipping(
                    customerValues[GlobalVariables.CUSTOMER_FIRST_NAME]!!,
                    customerValues[GlobalVariables.CUSTOMER_LAST_NAME]!!,
                    customerValues[GlobalVariables.CUSTOMER_ADDRESS]!!,
                    "",
                    customerValues[GlobalVariables.CUSTOMER_CITY]!!,
                    customerValues[GlobalVariables.CUSTOMER_STATE]!!,
                    "",
                    "Pakistan"
                )

            return Customer(customerValues[GlobalVariables.CUSTOMER_ID]!!.toInt(), customerValues[GlobalVariables.CUSTOMER_EMAIL]!!,
                customerValues[GlobalVariables.CUSTOMER_FIRST_NAME]!!, customerValues[GlobalVariables.CUSTOMER_LAST_NAME]!!,
                customerValues[GlobalVariables.CUSTOMER_USERNAME]!!, "", customerValues[GlobalVariables.CUSTOMER_PIN]!!,
                customerValues[GlobalVariables.CUSTOMER_CNIC]!!, billing, shipping)

        }

        fun saveCustomerPin(context: Context, pin: String) {
            val sharedPreferences = context.getSharedPreferences(GlobalVariables.CUSTOMER_PREFS_KEY, Context.MODE_PRIVATE).edit()
            sharedPreferences.putString(GlobalVariables.CUSTOMER_PIN, pin)
            sharedPreferences.apply()
        }

        fun isLoggedIn(context: Context): Boolean {
            val sharedPreferences = context.getSharedPreferences(GlobalVariables.CUSTOMER_PREFS_KEY, Context.MODE_PRIVATE)
            val username = sharedPreferences.getString(GlobalVariables.CUSTOMER_USERNAME, "")
            val pin = sharedPreferences.getString(GlobalVariables.CUSTOMER_PIN, "")
            return !username.isNullOrEmpty() && !pin.isNullOrEmpty()
        }

        fun isFirstRun(context: Context) : Boolean {
            val sharedPreferences = context.getSharedPreferences(GlobalVariables.CUSTOMER_PREFS_KEY, Context.MODE_PRIVATE)
            return sharedPreferences.getBoolean(GlobalVariables.FIRST_RUN, true)
        }

        fun setFirstRun(context: Context) {
            val sharedPreferences = context.getSharedPreferences(GlobalVariables.CUSTOMER_PREFS_KEY, Context.MODE_PRIVATE).edit()
            sharedPreferences.putBoolean(GlobalVariables.FIRST_RUN, false)
            sharedPreferences.apply()
        }

    }
}