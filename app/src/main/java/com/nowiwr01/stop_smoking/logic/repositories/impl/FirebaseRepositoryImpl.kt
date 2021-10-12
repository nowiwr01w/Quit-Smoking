package com.nowiwr01.stop_smoking.logic.repositories.impl

import com.google.firebase.auth.FirebaseAuth
import com.nowiwr01.stop_smoking.logic.repositories.FirebaseRepository
import com.nowiwr01.stop_smoking.ui.base.ResultRemote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class FirebaseRepositoryImpl(
    private val auth: FirebaseAuth,
): FirebaseRepository {

    override suspend fun loginUser(email: String, password: String) = withContext(Dispatchers.IO) {
        try {
            val response = auth.signInWithEmailAndPassword(email, password).await()
            ResultRemote.success(response.user)
        } catch (throwable: Throwable) {
            ResultRemote.error("Хуйня, а не авторизация")
        }
    }

    override suspend fun createUser(email: String, password: String) = withContext(Dispatchers.IO) {
        try {
            val response = auth.createUserWithEmailAndPassword(email, password).await()
            ResultRemote.success(response.user)
        } catch (throwable: Throwable) {
            ResultRemote.error("Хуйня, а не регистрация")
        }
    }

    override suspend fun logout() {

    }
}