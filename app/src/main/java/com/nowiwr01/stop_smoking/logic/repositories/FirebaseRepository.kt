package com.nowiwr01.stop_smoking.logic.repositories

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseUser
import com.nowiwr01.stop_smoking.domain.user.User
import com.nowiwr01.stop_smoking.ui.base.ResultRemote

interface FirebaseRepository {
    suspend fun authVk(
        user: User
    ): User

    suspend fun authGoogle(
        account: GoogleSignInAccount
    ): ResultRemote<FirebaseUser?>

    suspend fun loginUser(
        email: String,
        password: String
    ): ResultRemote<FirebaseUser?>

    suspend fun createUser(
        email: String,
        password: String
    ): ResultRemote<FirebaseUser?>
}