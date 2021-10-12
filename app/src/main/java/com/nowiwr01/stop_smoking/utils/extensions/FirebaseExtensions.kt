package com.nowiwr01.stop_smoking.utils.extensions

import com.google.firebase.auth.FirebaseUser
import com.nowiwr01.stop_smoking.domain.User

fun FirebaseUser.mapUser() = User(
    uid,
    displayName ?: "Котик, бросающий курить",
    email ?: "kotik_email@nowiwr.com"
)