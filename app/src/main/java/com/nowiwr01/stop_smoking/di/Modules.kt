package com.nowiwr01.stop_smoking.di

import com.nowiwr01.stop_smoking.ui.main.login.fragmentLogin
import com.nowiwr01.stop_smoking.ui.main.login.sign_in.fragmentSignIn
import com.nowiwr01.stop_smoking.ui.main.login.sign_up.fragmentSignUp
import com.nowiwr01.stop_smoking.ui.main.fragmentMain

val scopedFragments = listOf(
    fragmentMain,
    fragmentLogin,
    fragmentSignIn,
    fragmentSignUp
)

val koinModules = scopedFragments