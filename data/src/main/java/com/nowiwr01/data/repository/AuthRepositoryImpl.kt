package com.nowiwr01.data.repository

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.FirebaseDatabase
import com.nowiwr01.basecoroutines.DispatchersProvider
import com.nowiwr01.data.storage.LocalStorageDao
import com.nowiwr01.domain.model.user.User
import com.nowiwr01.domain.model.user.UserDataSignIn
import com.nowiwr01.domain.model.user.UserDataSignUp
import com.nowiwr01.domain.repository.AuthRepository
import com.nowiwr01.domain.utils.Const.FACEBOOK_AUTH_TYPE
import com.nowiwr01.domain.utils.Const.FIREBASE_AUTH_TYPE
import com.nowiwr01.domain.utils.Const.GOOGLE_AUTH_TYPE
import com.nowiwr01.domain.utils.Const.USERS_REFERENCE
import com.nowiwr01.domain.utils.extensions.hasAccount
import com.nowiwr01.domain.utils.extensions.mapUser
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class AuthRepositoryImpl(
    private val auth: FirebaseAuth,
    private val prefs: LocalStorageDao,
    private val database: FirebaseDatabase,
    private val dispatchers: DispatchersProvider,
) : AuthRepository {

    override suspend fun authVk(user: User) = withContext(dispatchers.io) {
        val existingUser = checkExistingAccount(user)
        saveFirebaseUser(user, existingUser)
    }

    override suspend fun authGoogle(account: GoogleSignInAccount) = withContext(dispatchers.io) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        val user = auth.signInWithCredential(credential).await().user!!.mapUser(GOOGLE_AUTH_TYPE)
        val existingUser = checkExistingAccount(user)
        saveFirebaseUser(user, existingUser)
    }

    override suspend fun authFacebook(token: String) = withContext(dispatchers.io) {
        val credential = FacebookAuthProvider.getCredential(token)
        val user = auth.signInWithCredential(credential).await().user!!.mapUser(FACEBOOK_AUTH_TYPE)
        val existingUser = checkExistingAccount(user)
        saveFirebaseUser(user, existingUser)
    }

    override suspend fun loginUser(userData: UserDataSignIn) = withContext(dispatchers.io) {
        val user = auth.signInWithEmailAndPassword(userData.email, userData.password)
            .await()
            .user!!.mapUser(FIREBASE_AUTH_TYPE)
        checkExistingAccount(user) ?: throw IllegalStateException("Always have an account.")
    }

    override suspend fun createUser(userData: UserDataSignUp) = withContext(dispatchers.io) {
        val user = auth.createUserWithEmailAndPassword(userData.email, userData.password)
            .await()
            .user!!.mapUser(FIREBASE_AUTH_TYPE, userData.userName)
        saveFirebaseUser(user, null)
    }

    private suspend fun checkExistingAccount(user: User): User? {
        var resultUser: User? = null
        database.getReference(USERS_REFERENCE).get().await().children.forEach {
            resultUser = it.hasAccount(user)
            if (resultUser != null) return@forEach
        }
        return resultUser
    }

    private suspend fun saveFirebaseUser(newUser: User, existingUser: User?): User {
        return if (existingUser == null) {
            prefs.setUserReference(newUser)
            database.getReference(USERS_REFERENCE).child(newUser.id).setValue(newUser).await()
            newUser
        } else {
            existingUser
        }
    }
}