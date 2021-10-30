package com.nowiwr01.stop_smoking.di

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.nowiwr01.stop_smoking.Const.PREFS_NAME
import com.nowiwr01.stop_smoking.db.LocalStorageDao
import com.nowiwr01.stop_smoking.logic.ApplicationDispatchers
import com.nowiwr01.stop_smoking.logic.DispatchersProvider
import com.nowiwr01.stop_smoking.logic.interactors.AuthInteractor
import com.nowiwr01.stop_smoking.logic.interactors.UserDataInteractor
import com.nowiwr01.stop_smoking.logic.repositories.AuthRepository
import com.nowiwr01.stop_smoking.logic.repositories.UserDataRepository
import com.nowiwr01.stop_smoking.logic.repositories.VKRepository
import com.nowiwr01.stop_smoking.logic.repositories.impl.AuthRepositoryImpl
import com.nowiwr01.stop_smoking.logic.repositories.impl.UserDataRepositoryImpl
import com.nowiwr01.stop_smoking.logic.repositories.impl.VKRepositoryImpl
import com.nowiwr01.stop_smoking.ui.main.auth.fragmentAuth
import com.nowiwr01.stop_smoking.ui.main.fragmentMain
import com.nowiwr01.stop_smoking.ui.main.auth.AuthViewModel
import com.nowiwr01.stop_smoking.utils.logger.*
import org.koin.android.ext.koin.androidApplication
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

val appData = module {
    single {
        LocalStorageDao(
            androidApplication().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        )
    }
}

val firebaseData = module {
    factory { FirebaseAuth.getInstance() }
    factory { FirebaseDatabase.getInstance() }
}

val repositories = module {
    factory<VKRepository> { VKRepositoryImpl(get()) }
    factory<UserDataRepository> { UserDataRepositoryImpl(get()) }
    factory<AuthRepository> { AuthRepositoryImpl(get(), get(), get(), get()) }
}

val interactors = module {
    factory { UserDataInteractor(get()) }
    factory { AuthInteractor(get(), get()) }
}

val viewModels = module {
    viewModel { AuthViewModel(get(), get(), get()) }
}

val koinModules = listOf(
    dispatchers,
    appData,
    firebaseData,
    analytics,
    repositories,
    interactors,
    viewModels
) + scopedFragments