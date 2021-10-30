package com.nowiwr01.domain.model.user

import com.nowiwr01.domain.model.user.smoke_info.Subscription
import com.nowiwr01.domain.model.user.smoke_info.SmokeInfo

data class User(
    var id: String = "",
    var email: String = "",
    var username: String = "",
    var authMethod: String = "",
    var subscription: Subscription = Subscription(),
    var smokeInfo: SmokeInfo = SmokeInfo(),
)