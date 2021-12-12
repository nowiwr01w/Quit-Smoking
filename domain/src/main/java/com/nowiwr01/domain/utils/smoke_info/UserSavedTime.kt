package com.nowiwr01.domain.utils.smoke_info

import com.nowiwr01.domain.R
import com.nowiwr01.domain.model.user.User
import com.nowiwr01.domain.utils.extensions.MILLIS_IN_DAY
import com.nowiwr01.domain.utils.extensions.setScale

object UserSavedTime {

    fun getSavedTime(user: User): Pair<String, Int> {
        val notSmokedDays = (System.currentTimeMillis() - user.smokeInfo.breakDate) / MILLIS_IN_DAY.toDouble()
        val minutes = user.smokeInfo.cigarettesPerDay * notSmokedDays * 5
        val hours = minutes / 60
        val days = hours / 24
        val month = days / 31
        val years = month / 12
        return when {
            years > 1 -> years.setScale().toString() to R.string.years_short
            month > 1 -> month.setScale().toString() to R.string.months_short
            days > 1 -> days.setScale().toString() to R.string.days_short
            hours > 1 -> hours.setScale().toString() to R.string.hours_short
            minutes > 0.1 -> minutes.setScale().toString() to R.string.minutes_short
            else -> throw IllegalStateException("Хз чё тут такое")
        }
    }
}