package com.nowiwr01.stop_smoking.utils.extensions

import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.nowiwr01.stop_smoking.Const.DEFAULT_EMAIL
import com.nowiwr01.stop_smoking.Const.VK_AUTH_TYPE
import com.nowiwr01.stop_smoking.data.InfoVK
import com.nowiwr01.stop_smoking.domain.smoke_info.SmokeInfo
import com.nowiwr01.stop_smoking.domain.smoke_info.Subscription
import com.nowiwr01.stop_smoking.domain.user.User
import com.vk.api.sdk.auth.VKAccessToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

fun InfoVK.mapUser(token: VKAccessToken) = User(
    id = token.userId.toString(),
    username = String.format("%s %s", first_name, last_name),
    email = token.email ?: DEFAULT_EMAIL,
    subscription = Subscription(),
    smokeInfo = SmokeInfo(),
    authMethod = VK_AUTH_TYPE
)

fun FirebaseUser.mapUser(authFrom: String, name: String = "Котик, бросающий курить") = User(
    id = uid,
    username = displayName ?: name,
    email = email ?: DEFAULT_EMAIL,
    subscription = Subscription(),
    smokeInfo = SmokeInfo(),
    authMethod = authFrom
)

fun DataSnapshot.getUser() = getValue(User::class.java)!!

fun DataSnapshot.hasAccount(user: User): User? {
    return if (getUser().id == user.id) getUser() else null
}

fun getCallbackManager(
    successCallback: suspend (token: String) -> Unit
) = object : FacebookCallback<LoginResult> {
    override fun onSuccess(result: LoginResult) {
        val appToken = result.accessToken
        GraphRequest.newMeRequest(appToken) { _, _ ->
            CoroutineScope(Dispatchers.IO).launch {
                successCallback.invoke(appToken.token)
            }
        }.executeAndWait()
    }

    override fun onCancel() {}

    override fun onError(error: FacebookException) {
        Timber.tag("FacebookError").e("error = $error")
    }
}