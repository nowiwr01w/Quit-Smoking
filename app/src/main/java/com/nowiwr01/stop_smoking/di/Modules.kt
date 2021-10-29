package com.nowiwr01.stop_smoking.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.nowiwr01.stop_smoking.logic.ApplicationDispatchers
import com.nowiwr01.stop_smoking.logic.DispatchersProvider
import com.nowiwr01.stop_smoking.logic.interactors.AuthInteractor
import com.nowiwr01.stop_smoking.logic.interactors.UserDataInteractor
import com.nowiwr01.stop_smoking.logic.repositories.FirebaseRepository
import com.nowiwr01.stop_smoking.logic.repositories.UserDataRepository
import com.nowiwr01.stop_smoking.logic.repositories.VKRepository
import com.nowiwr01.stop_smoking.logic.repositories.impl.FirebaseRepositoryImpl
import com.nowiwr01.stop_smoking.logic.repositories.impl.UserDataRepositoryImpl
import com.nowiwr01.stop_smoking.logic.repositories.impl.VKRepositoryImpl
import com.nowiwr01.stop_smoking.ui.main.auth.fragmentAuth
import com.nowiwr01.stop_smoking.ui.main.fragmentMain
import com.nowiwr01.stop_smoking.ui.main.auth.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val scopedFragments = listOf(
    fragmentMain,
    fragmentAuth,
)

val dispatchers = module {
    single { ApplicationDispatchers() } bind DispatchersProvider::class
}

val firebaseData = module {
    factory { FirebaseAuth.getInstance() }
    factory { FirebaseDatabase.getInstance() }
}

val repositories = module {
    factory<VKRepository> { VKRepositoryImpl(get()) }
    factory<UserDataRepository> { UserDataRepositoryImpl(get()) }
    factory<FirebaseRepository> { FirebaseRepositoryImpl(get(), get(), get()) }
}

val interactors = module {
    factory { UserDataInteractor(get()) }
    factory { AuthInteractor(get(), get()) }
}

val viewModels = module {
    viewModel { AuthViewModel(get(), get()) }
}

val koinModules = listOf(
    dispatchers,
    firebaseData,
    repositories,
    interactors,
    viewModels
) + scopedFragments