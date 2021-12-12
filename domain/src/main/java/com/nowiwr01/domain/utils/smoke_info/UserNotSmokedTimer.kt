package com.nowiwr01.domain.utils.smoke_info

import com.nowiwr01.domain.R
import com.nowiwr01.domain.model.user.User
import com.nowiwr01.domain.model.user.smoke_info.SmokeTime
import com.nowiwr01.domain.utils.extensions.*

object UserNotSmokedTimer {

    fun getTimerNotSmoked(user: User): SmokeTime {
        var diff = System.currentTimeMillis() - user.smokeInfo.breakDate
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
                R.string.years to years.round(),
                R.string.months to month.round(),
                R.string.days to days.round(),
                R.string.hours to hours.round()
            )
            month > 0 -> SmokeTime(
                R.string.months to month.round(),
                R.string.days to days.round(),
                R.string.hours to hours.round(),
                R.string.minutes to minutes.round()
            )
            else -> SmokeTime(
                R.string.days to days.round(),
                R.string.hours to hours.round(),
                R.string.minutes to minutes.round(),
                R.string.seconds to seconds.round()
            )
        }
    }
}