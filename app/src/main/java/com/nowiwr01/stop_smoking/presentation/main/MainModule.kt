package com.nowiwr01.stop_smoking.presentation.main

import org.koin.dsl.module

val fragmentMain = module {
    scope<MainFragment> {
        scoped { params ->
            TimerViewController(params[0])
        }
        scoped { params ->
            MainNavigator(params[0])
        }
    }
}