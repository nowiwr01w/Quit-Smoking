package com.nowiwr01.stop_smoking.logic.interactors

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.nowiwr01.stop_smoking.domain.user.User
import com.nowiwr01.stop_smoking.logic.errors.GoogleAuthError
import com.nowiwr01.stop_smoking.logic.errors.SignInError
import com.nowiwr01.stop_smoking.logic.errors.SignUpError
import com.nowiwr01.stop_smoking.logic.errors.VKAuthError
import com.nowiwr01.stop_smoking.logic.repositories.FirebaseRepository
import com.nowiwr01.stop_smoking.logic.repositories.VKRepository
import com.nowiwr01.stop_smoking.ui.base.Result
import com.nowiwr01.stop_smoking.ui.base.ResultRemote
import com.nowiwr01.stop_smoking.utils.extensions.mapUser
import com.vk.api.sdk.auth.VKAccessToken

class AuthInteractor(
    private val vkRepository: VKRepository,
    private val firebaseRepository: FirebaseRepository
) {

    suspend fun login(email: String, password: String): Result<User, SignInError> {
        val user = firebaseRepository.loginUser(email, password)
        return if (user.status == ResultRemote.Status.SUCCESS && user.data != null) {
            Result.Success(user.data.mapUser())
        } else {
            Result.Fail(SignInError.SignInServerError())
        }
    }

    suspend fun signUp(email: String, password: String): Result<User, SignUpError> {
        val user = firebaseRepository.createUser(email, password)
        return if (user.status == ResultRemote.Status.SUCCESS && user.data != null) {
            Result.Success(user.data.mapUser())
        } else {
            Result.Fail(SignUpError.SignUpServerError())
        }
    }

    suspend fun authVk(token: VKAccessToken): Result<User, VKAuthError> {
        return try {
            val user = vkRepository.getInfo(token).mapUser(token)
            Result.Success(firebaseRepository.authVk(user))
        } catch (throwable: Throwable) {
            Result.Fail(VKAuthError())
        }
    }

    suspend fun authGoogle(account: GoogleSignInAccount): Result<User, GoogleAuthError> {
        val user = firebaseRepository.authGoogle(account)
        return if (user.status == ResultRemote.Status.SUCCESS && user.data != null) {
            Result.Success(user.data.mapUser())
        } else {
            Result.Fail(GoogleAuthError())
        }
    }
}