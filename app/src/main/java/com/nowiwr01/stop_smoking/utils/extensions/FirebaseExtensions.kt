package com.nowiwr01.stop_smoking.utils.extensions

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.nowiwr01.stop_smoking.Const.DEFAULT_EMAIL
import com.nowiwr01.stop_smoking.Const.TYPE_GOOGLE
import com.nowiwr01.stop_smoking.Const.TYPE_VK
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
    smokeInfo = SmokeInfo(),
    vkId = token.userId
)

fun FirebaseUser.mapUser() = User(
    id = uid,
    username = displayName ?: "Котик, бросающий курить",
    email = email ?: DEFAULT_EMAIL,
    subscription = Subscription(),
    smokeInfo = SmokeInfo(),
)

fun DataSnapshot.getUser() = getValue(User::class.java)!!

fun DataSnapshot.hasAccount(authType: String, user: User) = when (authType) {
    TYPE_VK -> getUser().vkId == user.vkId
    TYPE_GOOGLE -> getUser().googleToken == user.googleToken
    else -> throw IllegalStateException("Wrong auth type.")
}