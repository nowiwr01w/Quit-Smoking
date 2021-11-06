package com.nowiwr01.domain.repository

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.nowiwr01.domain.model.user.User
import com.nowiwr01.domain.model.user.UserDataSignIn
import com.nowiwr01.domain.model.user.UserDataSignUp

interface AuthRepository {
    suspend fun authVk(user: User): Pair<String, User>

    suspend fun authGoogle(account: GoogleSignInAccount): Pair<String, User>

    suspend fun authFacebook(token: String): Pair<String, User>

    suspend fun loginUser(userData: UserDataSignIn): Pair<String, User>

    suspend fun createUser(userData: UserDataSignUp): Pair<String, User>
}