package com.nowiwr01.domain.model.user

data class UserDataSignIn(
    override val email: String,
    override val password: String
): UserData