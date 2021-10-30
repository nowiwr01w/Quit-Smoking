package com.nowiwr01.stop_smoking.logic.interactors

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.nowiwr01.stop_smoking.domain.user.User
import com.nowiwr01.stop_smoking.domain.user.UserDataSignIn
import com.nowiwr01.stop_smoking.domain.user.UserDataSignUp
import com.nowiwr01.stop_smoking.logic.errors.*
import com.nowiwr01.stop_smoking.logic.repositories.AuthRepository
import com.nowiwr01.stop_smoking.logic.repositories.VKRepository
import com.nowiwr01.stop_smoking.ui.base.Result
import com.nowiwr01.stop_smoking.utils.extensions.mapUser
import com.vk.api.sdk.auth.VKAccessToken

class AuthInteractor(
    private val vkRepository: VKRepository,
    private val authRepository: AuthRepository
) {

    suspend fun login(userData: UserDataSignIn): Result<User, SignInError> {
        return try {
            Result.Success(authRepository.loginUser(userData))
        } catch (throwable: Throwable) {
            Result.Fail(SignInError.SignInServerError())
        }
    }

    suspend fun signUp(userData: UserDataSignUp): Result<User, SignUpError> {
        return try {
            Result.Success(authRepository.createUser(userData))
        } catch (throwable: Throwable) {
            Result.Fail(SignUpError.SignUpServerError())
        }
    }

    suspend fun authVk(token: VKAccessToken): Result<User, VKAuthError> {
        return try {
            val user = vkRepository.getInfo(token).mapUser(token)
            Result.Success(authRepository.authVk(user))
        } catch (throwable: Throwable) {
            Result.Fail(VKAuthError())
        }
    }

    suspend fun authGoogle(account: GoogleSignInAccount): Result<User, GoogleAuthError> {
        return try {
            Result.Success(authRepository.authGoogle(account))
        } catch (throwable: Throwable) {
            Result.Fail(GoogleAuthError())
        }
    }

    suspend fun authFacebook(token: String): Result<User, FacebookAuthError> {
        return try {
            Result.Success(authRepository.authFacebook(token))
        } catch (throwable: Throwable) {
            Result.Fail(FacebookAuthError())
        }
    }
}