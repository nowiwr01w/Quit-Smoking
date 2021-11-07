package com.nowiwr01.stop_smoking.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.nowiwr01.basecoroutines.ApplicationDispatchers
import com.nowiwr01.basecoroutines.DispatchersProvider
import com.nowiwr01.data.storage.LocalStorageDao
import com.nowiwr01.domain.usecase.AuthUseCase
import com.nowiwr01.domain.usecase.UserDataUseCase
import com.nowiwr01.domain.repository.AuthRepository
import com.nowiwr01.domain.repository.UserDataRepository
import com.nowiwr01.domain.repository.VKRepository
import com.nowiwr01.data.repository.AuthRepositoryImpl
import com.nowiwr01.data.repository.UserDataRepositoryImpl
import com.nowiwr01.data.repository.UserRepositoryImpl
import com.nowiwr01.data.repository.VKRepositoryImpl
import com.nowiwr01.domain.repository.UserRepository
import com.nowiwr01.domain.usecase.UserUseCase
import com.nowiwr01.domain.utils.Const.PREFS_NAME
import com.nowiwr01.stop_smoking.presentation.main.auth.fragmentAuth
import com.nowiwr01.stop_smoking.presentation.main.home.fragmentMain
import com.nowiwr01.stop_smoking.presentation.main.auth.AuthViewModel
import com.nowiwr01.stop_smoking.presentation.main.home.UserViewModel
import com.nowiwr01.stop_smoking.presentation.main.home.health.fragmentHealth
import com.nowiwr01.stop_smoking.presentation.main.smoke_info.SmokeInfoViewModel
import com.nowiwr01.stop_smoking.presentation.main.smoke_info.fragmentSmokeInfo
import com.nowiwr01.stop_smoking.utils.logger.*
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val scopedFragments = listOf(
    fragmentAuth,
    fragmentSmokeInfo,
    fragmentMain,
    fragmentHealth
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
    factory<VKRepository> {
        VKRepositoryImpl(get())
    }
    factory<UserDataRepository> {
        UserDataRepositoryImpl(get())
    }
    factory<UserRepository> {
        UserRepositoryImpl(get(), get(), get())
    }
    factory<AuthRepository> {
        AuthRepositoryImpl(get(), get(), get(), get())
    }
}

val interactors = module {
    factory { UserUseCase(get()) }
    factory { UserDataUseCase(get()) }
    factory { AuthUseCase(get(), get()) }
}

val viewModels = module {
    viewModel { UserViewModel(get()) }
    viewModel { SmokeInfoViewModel(get()) }
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