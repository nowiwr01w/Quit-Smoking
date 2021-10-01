package com.nowiwr01.stop_smoking.db

import androidx.room.Dao
import androidx.room.Query
import com.nowiwr01.stop_smoking.Const.USER_TABLE_NAME
import com.nowiwr01.stop_smoking.data.User

@Dao
interface UserDao {
    @Query("SELECT * FROM $USER_TABLE_NAME LIMIT 1")
    suspend fun getUser(): User
}