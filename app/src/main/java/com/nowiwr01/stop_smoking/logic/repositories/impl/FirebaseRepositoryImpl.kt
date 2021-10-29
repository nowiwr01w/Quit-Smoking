package com.nowiwr01.stop_smoking.logic.repositories.impl

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.FirebaseDatabase
import com.nowiwr01.stop_smoking.Const.TYPE_VK
import com.nowiwr01.stop_smoking.Const.USERS_REFERENCE
import com.nowiwr01.stop_smoking.domain.user.User
import com.nowiwr01.stop_smoking.logic.DispatchersProvider
import com.nowiwr01.stop_smoking.logic.repositories.FirebaseRepository
import com.nowiwr01.stop_smoking.ui.base.ResultRemote
import com.nowiwr01.stop_smoking.utils.extensions.getUser
import com.nowiwr01.stop_smoking.utils.extensions.hasAccount
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class FirebaseRepositoryImpl(
    private val auth: FirebaseAuth,
    private val database: FirebaseDatabase,
    private val dispatchers: DispatchersProvider,
) : FirebaseRepository {

    override suspend fun authVk(user: User) = withContext(dispatchers.io) {
        var resultUser: User? = null
        database.getReference(USERS_REFERENCE).get().await().children.forEach {
            if (it.hasAccount(TYPE_VK, user)) resultUser = it.getUser()
        }
        if (resultUser == null) {
            database.getReference(USERS_REFERENCE).child(user.id).setValue(user).await()
            user
        } else {
            resultUser!!
        }
    }

    override suspend fun authGoogle(account: GoogleSignInAccount) = withContext(dispatchers.io) {
        try {
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            val response = auth.signInWithCredential(credential).await()
            ResultRemote.success(response.user)
        } catch (throwable: Throwable) {
            ResultRemote.error("Google auth error.")
        }
    }

    override suspend fun loginUser(email: String, password: String) = withContext(Dispatchers.IO) {
        try {
            val response = auth.signInWithEmailAndPassword(email, password).await()
            ResultRemote.success(response.user)
        } catch (throwable: Throwable) {
            ResultRemote.error("Firebase log in error.")
        }
    }

    override suspend fun createUser(email: String, password: String) = withContext(dispatchers.io) {
        try {
            val response = auth.createUserWithEmailAndPassword(email, password).await()
            ResultRemote.success(response.user)
        } catch (throwable: Throwable) {
            ResultRemote.error("Firebase sign up error.")
        }
    }
}