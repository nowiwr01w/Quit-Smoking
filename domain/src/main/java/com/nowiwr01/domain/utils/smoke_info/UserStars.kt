package com.nowiwr01.domain.utils.smoke_info

import com.nowiwr01.domain.model.user.User
import com.nowiwr01.domain.model.user.smoke_info.Star
import com.nowiwr01.domain.utils.extensions.*

object UserStars {

    fun getStars(user: User): List<Star> {
        var diff = System.currentTimeMillis() - user.smokeInfo.breakDate
        // years
        var years = diff / MILLIS_IN_YEAR
        years = if (years > 0) years else 0
        diff -= years * MILLIS_IN_YEAR
        // month
        var month = diff / MILLIS_IN_MONTH
        month = if (month > 0) month else 0
        diff -= month * MILLIS_IN_MONTH
        // weeks
        var weeks = diff / MILLIS_IN_WEEK
        weeks = if (weeks > 0) weeks else 0
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
            years > 0 -> addStar(
                years.toInt() to 5
            )
            month in 6..11 -> addStar(
                1 to 4
            )
            month in 1..5 -> addStar(
                month.toInt() to 3
            )
            weeks in 1..4 -> addStar(
                weeks.toInt() to 2
            )
            days in 1..6 -> addStar(
                days.toInt() to 1
            )
            else -> listOf()
        }
    }

    private fun addStar(first: Pair<Int, Int>): List<Star> {
        val result = mutableListOf<Star>()
        repeat(first.first) {
            result.add(Star(first.second))
        }
        return result
    }
}