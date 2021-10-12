package com.nowiwr01.stop_smoking.ui.main.login.sign_up

import com.nowiwr01.stop_smoking.databinding.FragmentSignUpBinding
import org.koin.dsl.module

val fragmentSignUp = module {
    scope<SignUpFragment> {
        scoped { params ->
            val binding: FragmentSignUpBinding = params[0]
            SignUpController(binding)
        }
    }
}