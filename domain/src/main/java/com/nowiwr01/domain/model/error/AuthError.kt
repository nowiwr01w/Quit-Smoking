package com.nowiwr01.domain.model.error


interface AuthError {
    val list: List<UserHighlightType>
    val message: String
}