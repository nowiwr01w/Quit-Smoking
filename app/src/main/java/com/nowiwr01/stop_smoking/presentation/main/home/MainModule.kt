package com.nowiwr01.stop_smoking.presentation.main

import com.nowiwr01.stop_smoking.presentation.main.home.MainFragment
import com.nowiwr01.stop_smoking.presentation.main.home.MainNavigator
import com.nowiwr01.stop_smoking.presentation.main.home.HomeViewsController
import org.koin.dsl.module

val fragmentMain = module {
    scope<MainFragment> {
        scoped { params ->
            HomeViewsController(params[0])
        }
        scoped { params ->
            MainNavigator(params[0])
        }
    }
}