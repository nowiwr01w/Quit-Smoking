package com.nowiwr01.stop_smoking.domain.smoke_info

import org.joda.time.DateTime

data class Crave(
    val date: DateTime,
    val smoke: Boolean,
    val desirePower: Int,
    val desireTimes: Int,
    val comment: String,
    val emotions: Emotions
)