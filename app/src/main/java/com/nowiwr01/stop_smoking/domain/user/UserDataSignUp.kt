package com.nowiwr01.stop_smoking.domain.user

data class UserDataSignUp(
    override val email: String,
    val userName: String,
    override val password: String,
    val passwordRepeated: String
): UserData