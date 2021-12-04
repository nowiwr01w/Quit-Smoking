package com.nowiwr01.stop_smoking.presentation.main.auth

import androidx.lifecycle.Lifecycle
import com.facebook.login.LoginManager
import com.nowiwr01.stop_smoking.databinding.FragmentAuthBinding
import com.nowiwr01.stop_smoking.presentation.main.auth.social.FacebookAuthActivityResultHandler
import com.nowiwr01.stop_smoking.presentation.main.auth.social.GoogleAuthActivityResultHandler
import com.nowiwr01.stop_smoking.presentation.main.auth.social.VKAuthActivityResultHandler
import com.nowiwr01.stop_smoking.utils.handler.ActivityResultChain
import org.koin.dsl.module

val fragmentAuth = module {
    scope<AuthFragment> {
        scoped { params ->
            val fragment: AuthFragment = params[0]
            AuthNavigator(fragment)
        }
        scoped { params ->
            val binding: FragmentAuthBinding = params[0]
            val viewModel: AuthViewModel = params[1]
            AuthViewsController(binding, viewModel)
        }
    }

    single { params ->
        val lifecycle: Lifecycle = params[0]
        val viewModel: AuthViewModel = params[1]
        ActivityResultChain(
            listOf(
                VKAuthActivityResultHandler(viewModel),
                GoogleAuthActivityResultHandler(viewModel),
                FacebookAuthActivityResultHandler(
                    lifecycle, LoginManager.getInstance(), viewModel
                )
            )
        )
    }
}