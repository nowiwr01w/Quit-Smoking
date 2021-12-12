package com.nowiwr01.domain.repository

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.nowiwr01.domain.model.user.User
import com.nowiwr01.domain.model.user.smoke_info.SmokeInfo
import com.nowiwr01.domain.model.user.smoke_info.SmokeTime
import com.nowiwr01.domain.model.user.smoke_info.Star

interface UserRepository {
    /***
     * Local
     */
    suspend fun getStars(user: User): List<Star>
    suspend fun getSavedTime(user: User): Pair<String, Int>
    suspend fun getSavedMoney(user: User): Pair<String, String>
    suspend fun getNotSmokedPacks(user: User): Double
    suspend fun getTimerNotSmoked(user: User): SmokeTime

    /***
     * Firebase
     */
    suspend fun loadUser(): User
    suspend fun setUser(user: User): User

    suspend fun updateSmokeInfo(info: SmokeInfo): User

    suspend fun addValueEventListener(
        callback: (user: User) -> Unit
    ): Pair<DatabaseReference, ValueEventListener>
}