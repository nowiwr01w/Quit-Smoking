package com.nowiwr01.domain.repository

import com.nowiwr01.domain.model.error.auth.SignInError
import com.nowiwr01.domain.model.error.auth.SignUpError
import com.nowiwr01.domain.model.user.UserDataSignIn
import com.nowiwr01.domain.model.user.UserDataSignUp

interface UserDataRepository {
    suspend fun isSignUpDataValid(userData: UserDataSignUp): SignUpError?
    suspend fun isSignInDataValid(userData: UserDataSignIn): SignInError?
}