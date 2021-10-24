package com.nowiwr01.stop_smoking.di

import com.google.firebase.auth.FirebaseAuth
import com.nowiwr01.stop_smoking.logic.interactors.FirebaseInteractor
import com.nowiwr01.stop_smoking.logic.interactors.UserDataInteractor
import com.nowiwr01.stop_smoking.logic.repositories.FirebaseRepository
import com.nowiwr01.stop_smoking.logic.repositories.UserDataRepository
import com.nowiwr01.stop_smoking.logic.repositories.impl.FirebaseRepositoryImpl
import com.nowiwr01.stop_smoking.logic.repositories.impl.UserDataRepositoryImpl
import com.nowiwr01.stop_smoking.ui.main.login.fragmentAuth
import com.nowiwr01.stop_smoking.ui.main.login.sign_in.fragmentSignIn
import com.nowiwr01.stop_smoking.ui.main.login.sign_up.fragmentSignUp
import com.nowiwr01.stop_smoking.ui.main.fragmentMain
import com.nowiwr01.stop_smoking.ui.main.login.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val scopedFragments = listOf(
    fragmentMain,
    fragmentAuth,
    fragmentSignIn,
    fragmentSignUp
)

val firebaseData = module {
    factory { FirebaseAuth.getInstance() }
}

val repositories = module {
    factory<UserDataRepository> { UserDataRepositoryImpl() }
    factory<FirebaseRepository> { FirebaseRepositoryImpl(get()) }
}

val interactors = module {
    factory { FirebaseInteractor(get()) }
    factory { UserDataInteractor(get()) }
}

val viewModels = module {
    viewModel { AuthViewModel(get(), get()) }
}

val koinModules = listOf(
    firebaseData,
    repositories,
    interactors,
    viewModels
) + scopedFragments