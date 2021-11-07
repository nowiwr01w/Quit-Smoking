package com.nowiwr01.stop_smoking.model.health

import android.content.Context
import com.nowiwr01.stop_smoking.R
import com.nowiwr01.stop_smoking.utils.extensions.MILLIS_IN_DAY
import com.nowiwr01.stop_smoking.utils.extensions.MILLIS_IN_HOUR
import com.nowiwr01.stop_smoking.utils.extensions.MILLIS_IN_MINUTE

object HealthBuilder {
    fun buildHealthList(context: Context) =  listOf(
        HealthItem(
            context.getString(R.string.health_20_min_title),
            context.getString(R.string.health_20_min),
            20 * MILLIS_IN_MINUTE
        ),
        HealthItem(
            context.getString(R.string.health_2_hours_title),
            context.getString(R.string.health_2_hours),
            2 * MILLIS_IN_HOUR
        ),
        HealthItem(
            context.getString(R.string.health_8_hours_title),
            context.getString(R.string.health_8_hours),
            8 * MILLIS_IN_HOUR
        ),
        HealthItem(
            context.getString(R.string.health_12_hours_title),
            context.getString(R.string.health_12_hours),
            12 * MILLIS_IN_HOUR
        ),
        HealthItem(
            context.getString(R.string.health_1_day_title),
            context.getString(R.string.health_1_day),
            1 * MILLIS_IN_DAY
        ),
        HealthItem(
            context.getString(R.string.health_2_days_title),
            context.getString(R.string.health_2_days),
            2 * MILLIS_IN_DAY
        ),
        HealthItem(
            context.getString(R.string.health_4_days_title),
            context.getString(R.string.health_4_days),
            4 * MILLIS_IN_DAY
        ),
        HealthItem(
            context.getString(R.string.health_5_days_title),
            context.getString(R.string.health_5_days),
            5 * MILLIS_IN_DAY
        ),
        HealthItem(
            context.getString(R.string.health_8_days_title),
            context.getString(R.string.health_8_days),
            8 * MILLIS_IN_DAY
        ),
    )
}