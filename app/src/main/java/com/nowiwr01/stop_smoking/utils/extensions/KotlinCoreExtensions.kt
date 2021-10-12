package com.nowiwr01.stop_smoking.utils.extensions

fun String.hasUpperChar(): Boolean {
    var cur = 0
    toCharArray().forEach {
        if (it.isLetter() && it.isUpperCase()) cur += 1
    }
    return cur > 0
}

fun String.isLongPassword() = length >= 8

fun String.isValidEmail() =
    isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()