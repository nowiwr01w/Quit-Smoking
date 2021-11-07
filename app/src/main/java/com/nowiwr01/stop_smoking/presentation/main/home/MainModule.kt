package com.nowiwr01.stop_smoking.presentation.main.home

import com.nowiwr01.stop_smoking.databinding.FragmentMainBinding
import org.koin.dsl.module

val fragmentMain = module {
    scope<MainFragment> {
        scoped { params ->
            val binding: FragmentMainBinding = params[0]
            val viewModel: UserViewModel = params[1]
            HomeViewsController(binding, viewModel)
        }
        scoped { params ->
            MainNavigator(params[0])
        }
    }
}