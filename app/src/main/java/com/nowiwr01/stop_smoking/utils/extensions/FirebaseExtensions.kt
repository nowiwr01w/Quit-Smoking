package com.nowiwr01.stop_smoking.utils.extensions

import com.google.firebase.auth.FirebaseUser
import com.nowiwr01.stop_smoking.Const.DEFAULT_EMAIL
import com.nowiwr01.stop_smoking.data.InfoVK
import com.nowiwr01.stop_smoking.domain.smoke_info.SmokeInfo
import com.nowiwr01.stop_smoking.domain.smoke_info.Subscription
import com.nowiwr01.stop_smoking.domain.user.User
import com.vk.api.sdk.auth.VKAccessToken

fun InfoVK.mapUser(token: VKAccessToken) = User(
    id = token.accessToken,
    username = String.format("%s %s", first_name, last_name),
    email = token.email ?: DEFAULT_EMAIL,
    subscription = Subscription(),
    smokeInfo = SmokeInfo()
)

fun FirebaseUser.mapUser() = User(
    id = uid,
    username = displayName ?: "Котик, бросающий курить",
    email = email ?: DEFAULT_EMAIL,
    subscription = Subscription(),
    smokeInfo = SmokeInfo()
)