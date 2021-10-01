package com.nowiwr01.stop_smoking.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nowiwr01.stop_smoking.Const.USER_TABLE_NAME

@Entity(tableName = USER_TABLE_NAME)
data class User(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String
)