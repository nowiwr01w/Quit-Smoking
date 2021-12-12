package com.nowiwr01.data.repository

import com.google.firebase.database.FirebaseDatabase
import com.nowiwr01.basecoroutines.DispatchersProvider
import com.nowiwr01.data.storage.LocalStorageDao
import com.nowiwr01.domain.model.user.User
import com.nowiwr01.domain.model.user.smoke_info.SmokeInfo
import com.nowiwr01.domain.repository.UserRepository
import com.nowiwr01.domain.utils.Const.USERS_REFERENCE
import com.nowiwr01.domain.utils.extensions.*
import com.nowiwr01.domain.utils.smoke_info.*
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val database: FirebaseDatabase,
    private val localStorageDao: LocalStorageDao,
    private val dispatchers: DispatchersProvider
): UserRepository {

    override suspend fun getStars(user: User) = withContext(dispatchers.io) {
        UserStars.getStars(user)
    }

    override suspend fun getSavedTime(user: User) = withContext(dispatchers.io) {
        UserSavedTime.getSavedTime(user)
    }

    override suspend fun getSavedMoney(user: User) = withContext(dispatchers.io) {
        UserSavedMoney.getSavedMoney(user)
    }

    override suspend fun getNotSmokedPacks(user: User) = withContext(dispatchers.io) {
        UserNotSmokedPacks.getNotSmokedPacks(user)
    }

    override suspend fun getTimerNotSmoked(user: User) = withContext(dispatchers.io) {
        UserNotSmokedTimer.getTimerNotSmoked(user)
    }

    private fun getCurrentUser() = database
        .getReference(USERS_REFERENCE)
        .child(localStorageDao.getUserReference())

    override suspend fun loadUser() = withContext(dispatchers.io) {
        val userReference = getCurrentUser().get().await()
        userReference.getUser()
    }

    override suspend fun setUser(user: User) = withContext(dispatchers.io) {
        getCurrentUser().setValue(user)
        user
    }

    override suspend fun updateSmokeInfo(info: SmokeInfo) = withContext(dispatchers.io) {
        val user = loadUser().apply {
            smokeInfo = info
        }
        localStorageDao.setUserReference(user)
        setUser(user)
    }

    override suspend fun addValueEventListener(callback: (user: User) -> Unit) = withContext(dispatchers.io) {
        val userListener = createUserEventListener(callback)
        val reference = getCurrentUser().apply {
            addValueEventListener(userListener)
        }
        reference to userListener
    }
}