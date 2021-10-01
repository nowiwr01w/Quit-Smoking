package com.nowiwr01.stop_smoking.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nowiwr01.stop_smoking.data.User

@Database(
    entities = [User::class],
    version = 3,
    exportSchema = false
)
abstract class RoomDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}