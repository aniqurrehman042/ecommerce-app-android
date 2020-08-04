package com.sahoolatkar.sahoolatkar.utils

import org.apache.commons.lang3.RandomStringUtils
import java.security.SecureRandom


class PasswordUtils {
    companion object {

        fun getRandomPassword() : String {
            val possibleCharacters: CharArray =
                "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?".toCharArray()
            return RandomStringUtils.random(
                15,
                0,
                possibleCharacters.size - 1,
                false,
                false,
                possibleCharacters,
                SecureRandom()
            )
        }

    }
}