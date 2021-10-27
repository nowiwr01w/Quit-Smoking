package com.nowiwr01.stop_smoking.domain.user

import com.nowiwr01.stop_smoking.domain.smoke_info.Subscription
import com.nowiwr01.stop_smoking.domain.smoke_info.SmokeInfo

data class User(
    var id: String = "",
    var email: String = "",
    var username: String = "",
    var subscription: Subscription = Subscription(),
    var smokeInfo: SmokeInfo = SmokeInfo(),
    var vkId: Int = -1,
    var googleToken: String = "",
    var firebaseToken: String = ""
)