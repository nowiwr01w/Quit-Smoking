package com.nowiwr01.stop_smoking.ui.main.login.sign_in

import com.nowiwr01.stop_smoking.databinding.FragmentSignInBinding
import org.koin.dsl.module

val fragmentSignIn = module {
    scope<SignInFragment> {
        scoped { params ->
            val binding: FragmentSignInBinding = params[0]
            SignInViewsController(binding)
        }
    }
}