package com.nowiwr01.data.repository

import com.google.firebase.database.FirebaseDatabase
import com.nowiwr01.basecoroutines.DispatchersProvider
import com.nowiwr01.data.storage.LocalStorageDao
import com.nowiwr01.domain.model.user.User
import com.nowiwr01.domain.model.user.smoke_info.SmokeInfo
import com.nowiwr01.domain.repository.UserRepository
import com.nowiwr01.domain.utils.Const.USERS_REFERENCE
import com.nowiwr01.domain.utils.extensions.createUserEventListener
import com.nowiwr01.domain.utils.extensions.getUser
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val database: FirebaseDatabase,
    private val localStorageDao: LocalStorageDao,
    private val dispatchers: DispatchersProvider
): UserRepository {

    private fun getCurrentUser() = database
        .getReference(USERS_REFERENCE)
        .child(localStorageDao.getUserReference())

    override suspend fun getUser() = withContext(dispatchers.io) {
        val userReference = getCurrentUser().get().await()
        userReference.getUser()
    }

    override suspend fun setUser(user: User) = withContext(dispatchers.io) {
        getCurrentUser().setValue(user)
        user
    }

    override suspend fun updateSmokeInfo(info: SmokeInfo) = withContext(dispatchers.io) {
        val user = getUser().apply {
            smokeInfo = info
        }
        setUser(user)
    }

    override suspend fun addValueEventListener(callback: (user: User) -> Unit) = withContext(dispatchers.io) {
        val userListener = createUserEventListener(callback)
        val reference = getCurrentUser()
        reference.addValueEventListener(userListener)
        reference to userListener
    }
}