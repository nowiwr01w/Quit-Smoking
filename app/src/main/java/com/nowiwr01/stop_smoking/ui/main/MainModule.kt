package com.nowiwr01.stop_smoking.ui.main

import org.koin.dsl.module

val fragmentMain = module {
    scope<MainFragment> {
        scoped { params ->
            MainNavigator(params[0])
        }
    }
}