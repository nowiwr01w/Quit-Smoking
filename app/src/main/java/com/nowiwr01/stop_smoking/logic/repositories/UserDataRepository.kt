package com.nowiwr01.stop_smoking.logic.repositories

import com.nowiwr01.stop_smoking.domain.UserDataSignIn
import com.nowiwr01.stop_smoking.domain.UserDataSignUp
import com.nowiwr01.stop_smoking.logic.errors.SignInTextError
import com.nowiwr01.stop_smoking.logic.errors.SignUpTextError

interface UserDataRepository {
    suspend fun isSignUpDataValid(userData: UserDataSignUp): SignUpTextError?
    suspend fun isSignInDataValid(userData: UserDataSignIn): SignInTextError?
}