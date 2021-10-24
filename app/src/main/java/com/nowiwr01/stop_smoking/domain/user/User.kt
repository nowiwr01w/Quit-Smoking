package com.nowiwr01.stop_smoking.domain.user

import com.nowiwr01.stop_smoking.domain.smoke_info.Subscription
import com.nowiwr01.stop_smoking.domain.smoke_info.SmokeInfo

data class User(
    val id: String,
    val email: String,
    val username: String,
    val subscription: Subscription,
    val smokeInfo: SmokeInfo
)