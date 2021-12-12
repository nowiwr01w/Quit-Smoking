package com.nowiwr01.domain.utils.extensions

import com.nowiwr01.domain.model.error.auth.UserHighlightType
import com.nowiwr01.domain.model.error.auth.UserHighlightType.*
import java.math.RoundingMode

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

fun Long.round() = if (this >= 10) toString() else "0$this"

fun Int.round() = if (this >= 10) toString() else "0$this"

fun Double.setScale() = toBigDecimal().setScale(1, RoundingMode.UP).toDouble()

const val MILLIS_IN_SECOND = 1_000L
const val MILLIS_IN_MINUTE = 60 * MILLIS_IN_SECOND
const val MILLIS_IN_HOUR = 60 * MILLIS_IN_MINUTE
const val MILLIS_IN_DAY = 24 * MILLIS_IN_HOUR
const val MILLIS_IN_WEEK = 7 * MILLIS_IN_DAY
const val MILLIS_IN_MONTH = 31 * MILLIS_IN_DAY
const val MILLIS_IN_YEAR = 12 * MILLIS_IN_MONTH

const val MONEY_ROUND = 10000