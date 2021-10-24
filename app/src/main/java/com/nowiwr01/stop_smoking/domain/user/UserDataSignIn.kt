package com.nowiwr01.stop_smoking.domain.user

data class UserDataSignIn(
    override val email: String,
    override val password: String
): UserData