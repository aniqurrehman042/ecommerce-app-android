package com.sahoolatkar.sahoolatkar.utils

class ValidationUtils {

    companion object {

        fun validateCnic(cnic: String) : Boolean {
            val regex = Regex("^[0-9+]{5}-[0-9+]{7}-[0-9]{1}\$")
            return regex.matches(cnic)
        }

        fun validateEmail(email: String) : Boolean {
            val regex = Regex("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}\$")
            return regex.matches(email)
        }

        fun validatePakPhoneNo(phone: String) : Boolean {
            val regex = Regex("03[0-9]{2}(?!1234567)(?!1111111)(?!7654321)[0-9]{7}")
            return regex.matches(phone)
        }
    }

}