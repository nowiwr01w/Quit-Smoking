package com.nowiwr01.domain.usecase

import com.nowiwr01.domain.model.error.AuthError
import com.nowiwr01.domain.model.user.UserData
import com.nowiwr01.domain.model.user.UserDataSignIn
import com.nowiwr01.domain.model.user.UserDataSignUp
import com.nowiwr01.domain.repository.UserDataRepository
import com.nowiwr01.domain.model.base.Result

class UserDataUseCase(
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