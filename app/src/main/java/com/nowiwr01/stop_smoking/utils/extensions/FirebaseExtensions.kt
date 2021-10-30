package com.nowiwr01.stop_smoking.utils.extensions

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.nowiwr01.stop_smoking.Const.DEFAULT_EMAIL
import com.nowiwr01.stop_smoking.data.InfoVK
import com.nowiwr01.stop_smoking.domain.smoke_info.SmokeInfo
import com.nowiwr01.stop_smoking.domain.smoke_info.Subscription
import com.nowiwr01.stop_smoking.domain.user.User
import com.vk.api.sdk.auth.VKAccessToken

fun InfoVK.fromVK(token: VKAccessToken) = User(
    id = token.userId.toString(),
    username = String.format("%s %s", first_name, last_name),
    email = token.email ?: DEFAULT_EMAIL,
    subscription = Subscription(),
    smokeInfo = SmokeInfo(),
    authMethod = "vk"
)

fun FirebaseUser.fromFirebase(name: String = "Котик, бросающий курить") = User(
    id = uid,
    username = displayName ?: name,
    email = email ?: DEFAULT_EMAIL,
    subscription = Subscription(),
    smokeInfo = SmokeInfo(),
    authMethod = "firebase"
)

fun FirebaseUser.fromGoogle(name: String = "Котик, бросающий курить") = User(
    id = uid,
    username = displayName ?: name,
    email = email ?: DEFAULT_EMAIL,
    subscription = Subscription(),
    smokeInfo = SmokeInfo(),
    authMethod = "google"
)

fun DataSnapshot.getUser() = getValue(User::class.java)!!

fun DataSnapshot.hasAccount(user: User): User? {
    return if (getUser().id == user.id) getUser() else null
}