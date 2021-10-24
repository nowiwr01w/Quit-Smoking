package com.nowiwr01.stop_smoking.logic.interactors

import com.nowiwr01.stop_smoking.domain.UserData
import com.nowiwr01.stop_smoking.domain.UserDataSignIn
import com.nowiwr01.stop_smoking.domain.UserDataSignUp
import com.nowiwr01.stop_smoking.logic.errors.AuthError
import com.nowiwr01.stop_smoking.logic.repositories.UserDataRepository
import com.nowiwr01.stop_smoking.ui.base.Result

class UserDataInteractor(
    private val userDataRepository: UserDataRepository
) {

    suspend fun checkUserInput(userData: UserData): Result<UserData, AuthError> {
        val error = when (userData) {
            is UserDataSignIn -> userDataRepository.isSignInDataValid(userData)
            is UserDataSignUp -> userDataRepository.isSignUpDataValid(userData)
            else -> throw IllegalStateException("Wrong user data type.")
        }
        return if (error == null) Result.Success(userData) else Result.Fail(error)
    }
}