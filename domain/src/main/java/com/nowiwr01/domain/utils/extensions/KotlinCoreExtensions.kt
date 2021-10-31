package com.nowiwr01.domain.utils.extensions

import androidx.core.util.PatternsCompat.EMAIL_ADDRESS
import com.nowiwr01.domain.model.error.UserHighlightType
import com.nowiwr01.domain.model.error.UserHighlightType.*

fun String.hasUpperChar(): Boolean {
    var cur = 0
    toCharArray().forEach {
        if (it.isLetter() && it.isUpperCase()) cur += 1
    }
    return cur > 0
}

fun String.isLongPassword() = length >= 8

fun String.isValidEmail() = isNotEmpty() && EMAIL_ADDRESS.matcher(this).matches()

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