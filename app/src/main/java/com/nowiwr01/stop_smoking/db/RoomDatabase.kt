package com.nowiwr01.stop_smoking.db

import androidx.room.RoomDatabase

abstract class RoomDatabase: RoomDatabase() {
    abstract fun userDao()
}