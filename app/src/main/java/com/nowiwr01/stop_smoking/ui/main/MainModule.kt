package com.nowiwr01.stop_smoking.ui.main

import org.koin.core.qualifier.named
import org.koin.dsl.module

val moduleMain = module {
    scope(named<MainFragment>()) {
        scoped { params ->
            MainNavigator(params[0])
        }
    }
}