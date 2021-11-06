package com.nowiwr01.stop_smoking.utils.extensions

import com.nowiwr01.domain.model.user.User
import com.nowiwr01.domain.model.user.smoke_info.SmokeTime
import java.math.RoundingMode

fun User.getSavedMoney(): Pair<String, String> {
    val money = (getNotSmokedPacks() * smokeInfo.cigarettesPackCost).toInt().roundSavedMoney()
    return money to smokeInfo.currency
}

fun User.getNotSmokedPacks(): Double {
    val days = (System.currentTimeMillis() - smokeInfo.breakDate) / MILLIS_IN_DAY.toDouble()
    val result = smokeInfo.cigarettesPerDay * days / smokeInfo.cigarettesPerPack
    return result.setScale()
}

fun Double.setScale() = toBigDecimal().setScale(1, RoundingMode.UP).toDouble()

fun User.getSavedTime(): Pair<String, String> {
    val notSmokedDays = (System.currentTimeMillis() - smokeInfo.breakDate) / MILLIS_IN_DAY.toDouble()
    val minutes = smokeInfo.cigarettesPerDay * notSmokedDays * 5
    val hours = minutes / 60
    val days = hours / 24
    val month = days / 31
    val years = month / 12
    return when {
        years > 1 -> years.setScale().toString() to "лет"
        month > 1 -> month.setScale().toString() to "мес."
        days > 1 -> days.setScale().toString() to "д."
        hours > 1 -> hours.setScale().toString() to "ч."
        minutes > 0.1 -> minutes.setScale().toString() to "мин."
        else -> throw IllegalStateException("Хз чё тут такое")
    }
}

fun User.getTimerNotSmoked(): SmokeTime {
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

fun Long.round() = if (this >= 10) toString() else "0$this"

fun Int.round() = if (this >= 10) toString() else "0$this"

fun Int.roundSavedMoney() = if (this >= MONEY_ROUND) "${this / MONEY_ROUND}K" else toString()

const val MILLIS_IN_SECOND = 1_000L
const val MILLIS_IN_MINUTE = 60 * MILLIS_IN_SECOND
const val MILLIS_IN_HOUR = 60 * MILLIS_IN_MINUTE
const val MILLIS_IN_DAY = 24 * MILLIS_IN_HOUR
const val MILLIS_IN_MONTH = 31 * MILLIS_IN_DAY
const val MILLIS_IN_YEAR = 12 * MILLIS_IN_MONTH

const val MONEY_ROUND = 10000