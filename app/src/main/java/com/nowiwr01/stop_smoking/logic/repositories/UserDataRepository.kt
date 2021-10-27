package com.nowiwr01.stop_smoking.logic.repositories

import com.nowiwr01.stop_smoking.domain.user.UserDataSignIn
import com.nowiwr01.stop_smoking.domain.user.UserDataSignUp
import com.nowiwr01.stop_smoking.logic.errors.SignInError
import com.nowiwr01.stop_smoking.logic.errors.SignUpError

interface UserDataRepository {
    suspend fun isSignUpDataValid(userData: UserDataSignUp): SignUpError?
    suspend fun isSignInDataValid(userData: UserDataSignIn): SignInError?
}