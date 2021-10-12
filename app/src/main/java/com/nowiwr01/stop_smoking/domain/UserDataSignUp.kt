package com.nowiwr01.stop_smoking.domain

data class UserDataSignUp(
    val email: String,
    val userName: String,
    val password: String,
    val passwordRepeated: String
)