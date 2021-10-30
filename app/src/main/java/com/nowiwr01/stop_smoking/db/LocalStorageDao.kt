package com.nowiwr01.stop_smoking.db

import android.content.SharedPreferences
import com.google.gson.GsonBuilder
import com.nowiwr01.stop_smoking.domain.user.User

class LocalStorageDao(
    private val preferences: SharedPreferences
) {

    private var mapper = GsonBuilder().setPrettyPrinting().create()

    private fun <T> getObject(
        key: String,
        type: Class<T>,
        prefs: SharedPreferences = preferences
    ): T? {
        val value = prefs.getString(key, null)
        return if (value != null) mapper.fromJson(value, type) else null
    }

    private fun <T> putObject(
        key: String,
        savedObject: T,
        prefs: SharedPreferences = preferences
    ) {
        val value = mapper.toJson(savedObject)
        prefs.edit().putString(key, value).apply()
    }

    fun setUserReference(user: User) = putObject(USER_REFERENCE, user.id)

    fun getUserReference() = getObject(USER_REFERENCE, String::class.java) ?: ""

    private companion object {
        const val USER_REFERENCE = "USER_REFERENCE"
    }
}