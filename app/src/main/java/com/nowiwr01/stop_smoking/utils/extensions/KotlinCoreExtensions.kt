package com.nowiwr01.stop_smoking.utils.extensions

import com.nowiwr01.domain.model.user.User
import com.nowiwr01.domain.model.user.smoke_info.SmokeTime
import java.math.RoundingMode

fun User.getNotSmokedTime(): SmokeTime {
    var diff = System.currentTimeMillis() - smokeInfo.breakDate
    // years
    var years = diff / MILLIS_IN_YEAR
    years = if (years > 0) years else 0
    diff -= years * MILLIS_IN_YEAR
    // month
    var month = diff / MILLIS_IN_MONTH
    month = if (month > 0) month else 0
    diff -= month * MILLIS_IN_MONTH
    // days
    var days = diff / MILLIS_IN_DAY
    days = if (days > 0) days else 0
    diff -= days * MILLIS_IN_DAY
    // hours
    var hours = diff / MILLIS_IN_HOUR
    hours = if (hours > 0) hours else 0
    diff -= hours * MILLIS_IN_HOUR
    // minutes
    var minutes = diff / MILLIS_IN_MINUTE
    minutes = if (minutes > 0) minutes else 0
    diff -= minutes * MILLIS_IN_MINUTE
    // seconds
    var seconds = diff / MILLIS_IN_SECOND
    seconds = if (seconds > 0) seconds else 0
    diff -= seconds * MILLIS_IN_SECOND

    return when {
        years > 0 -> SmokeTime(
            "лет" to years, "месяцев" to month, "дней" to days, "часов" to hours
        )
        month > 0 -> SmokeTime(
            "месяцев" to month, "дней" to days, "часов" to hours, "минут" to minutes
        )
        else -> SmokeTime(
            "дней" to days, "часов" to hours, "минут" to minutes, "секунд" to seconds
        )
    }
}

fun User.getNotSmokedPacks(): Double {
    val days = (System.currentTimeMillis() - smokeInfo.breakDate) / MILLIS_IN_DAY.toDouble()
    val result = smokeInfo.cigarettesPerDay * days / smokeInfo.cigarettesPerPack
    return result.toBigDecimal().setScale(result.round(), RoundingMode.UP).toDouble()
}

fun User.getNotSmokedDays(): Double {
    val days = (System.currentTimeMillis() - smokeInfo.breakDate) / MILLIS_IN_DAY.toDouble()
    return days.toBigDecimal().setScale(days.round(), RoundingMode.UP).toDouble()
}

fun Long.round() = if (this >= 10) toString() else "0$this"

fun Double.round() = if (this >= DAYS_AND_PACK_ROUND) 1 else 2

const val MILLIS_IN_SECOND = 1_000L
const val MILLIS_IN_MINUTE = 60 * MILLIS_IN_SECOND
const val MILLIS_IN_HOUR = 60 * MILLIS_IN_MINUTE
const val MILLIS_IN_DAY = 24 * MILLIS_IN_HOUR
const val MILLIS_IN_MONTH = 31 * MILLIS_IN_DAY
const val MILLIS_IN_YEAR = 12 * MILLIS_IN_MONTH

const val DAYS_AND_PACK_ROUND = 10L