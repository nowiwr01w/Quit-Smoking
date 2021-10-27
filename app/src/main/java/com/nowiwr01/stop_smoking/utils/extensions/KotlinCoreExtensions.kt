package com.nowiwr01.stop_smoking.utils.extensions

import com.nowiwr01.stop_smoking.ui.main.login.data.UserHighlightType
import com.nowiwr01.stop_smoking.ui.main.login.data.UserHighlightType.*

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

fun List<UserHighlightType>.getFieldNames() = mutableListOf<String>().apply {
    this@getFieldNames.forEach {
        when (it) {
            EMAIL_FIELD_ERROR -> add("email")
            USERNAME_FIELD_ERROR -> add("username")
            PASSWORD_FIELD_ERROR -> add("password")
            PASSWORD_AGAIN_FIELD_ERROR -> add("password_repeat")
        }
    }
}