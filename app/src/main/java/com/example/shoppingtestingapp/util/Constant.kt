package com.example.shoppingtestingapp.util

import java.util.regex.Matcher
import java.util.regex.Pattern

object Constant {

    const val BASE_URL="https://hasbni.com/api/customer/";

    var androidV=""




    val EMAIL_ADDRESS_PATTERN: Pattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )


    fun isValidPassword(password: String?): Boolean {
        val pattern: Pattern
        val matcher: Matcher
        val PASSWORD_REGEX = "(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20}$"
        pattern = Pattern.compile(PASSWORD_REGEX)
        matcher = pattern.matcher(password)
        return matcher.matches()
    }
}