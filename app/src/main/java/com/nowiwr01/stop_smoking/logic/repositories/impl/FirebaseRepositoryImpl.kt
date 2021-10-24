package com.nowiwr01.stop_smoking.logic.repositories.impl

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.nowiwr01.stop_smoking.Const.USERS_REFERENCE
import com.nowiwr01.stop_smoking.domain.user.User
import com.nowiwr01.stop_smoking.logic.DispatchersProvider
import com.nowiwr01.stop_smoking.logic.repositories.FirebaseRepository
import com.nowiwr01.stop_smoking.ui.base.ResultRemote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class FirebaseRepositoryImpl(
    private val auth: FirebaseAuth,
    private val database: FirebaseFirestore,
    private val dispatchers: DispatchersProvider,
) : FirebaseRepository {

    override suspend fun loginUser(email: String, password: String) = withContext(Dispatchers.IO) {
        try {
            val response = auth.signInWithEmailAndPassword(email, password).await()
            ResultRemote.success(response.user)
        } catch (throwable: Throwable) {
            ResultRemote.error("Хуйня, а не авторизация")
        }
    }

    override suspend fun createUser(email: String, password: String) = withContext(dispatchers.io) {
        try {
            val response = auth.createUserWithEmailAndPassword(email, password).await()
            ResultRemote.success(response.user)
        } catch (throwable: Throwable) {
            ResultRemote.error("Хуйня, а не регистрация")
        }
    }

    override suspend fun logout() = withContext(dispatchers.default) {
        auth.signOut()
    }

    override suspend fun addUser(user: User) = withContext(dispatchers.io) {
        database.collection(USERS_REFERENCE).document(user.id).set(user).await()
        true
    }
}