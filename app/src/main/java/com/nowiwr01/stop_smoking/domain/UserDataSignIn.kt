package com.nowiwr01.stop_smoking.domain

data class UserDataSignIn(
    override val email: String,
    override val password: String
): UserData