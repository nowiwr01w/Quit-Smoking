package com.nowiwr01.stop_smoking.domain.smoke_info

data class Crave(
    val date: Int,
    val smoke: Boolean,
    val desirePower: Int,
    val desireTimes: Int,
    val comment: String,
    val emotions: Emotions
)