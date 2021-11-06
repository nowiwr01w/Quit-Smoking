package com.nowiwr01.domain.model.error.auth

interface AuthError {
    val list: List<UserHighlightType>
    val message: String
}