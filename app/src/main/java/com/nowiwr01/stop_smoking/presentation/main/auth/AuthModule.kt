package com.nowiwr01.stop_smoking.presentation.main.auth

import com.nowiwr01.stop_smoking.databinding.FragmentAuthBinding
import org.koin.dsl.module

val fragmentAuth = module {
    scope<AuthFragment> {
        scoped { params ->
            val binding: FragmentAuthBinding = params[0]
            val viewModel: AuthViewModel = params[1]
            AuthViewsController(binding, viewModel)
        }
    }
}