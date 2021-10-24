package com.nowiwr01.stop_smoking.logic.repositories

import com.google.firebase.auth.FirebaseUser
import com.nowiwr01.stop_smoking.domain.user.User
import com.nowiwr01.stop_smoking.ui.base.ResultRemote

interface FirebaseRepository {
    suspend fun loginUser(email: String, password: String): ResultRemote<FirebaseUser?>
    suspend fun createUser(email: String, password: String): ResultRemote<FirebaseUser?>
    suspend fun logout()

    suspend fun addUser(user: User): Boolean
}