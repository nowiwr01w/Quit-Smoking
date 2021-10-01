package com.nowiwr01.stop_smoking.extensions

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nowiwr01.stop_smoking.Const

fun buildDatabase(context: Context) = Room.databaseBuilder(
    context,
    RoomDatabase::class.java,
    Const.NEVER_SMOKE_DATABASE_NAME
).fallbackToDestructiveMigration().build()