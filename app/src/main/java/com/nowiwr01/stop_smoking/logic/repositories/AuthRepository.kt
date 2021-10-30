package com.nowiwr01.stop_smoking.logic.repositories

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.nowiwr01.stop_smoking.domain.user.User
import com.nowiwr01.stop_smoking.domain.user.UserDataSignIn
import com.nowiwr01.stop_smoking.domain.user.UserDataSignUp

interface AuthRepository {
    suspend fun authVk(user: User): User

    suspend fun authGoogle(account: GoogleSignInAccount): User

    suspend fun authFacebook(token: String): User

    suspend fun loginUser(userData: UserDataSignIn): User

    suspend fun createUser(userData: UserDataSignUp): User
}