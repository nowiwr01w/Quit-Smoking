package com.nowiwr01.stop_smoking.presentation.main.smoke_info

import com.nowiwr01.stop_smoking.databinding.FragmentSmokingInfoBinding
import com.nowiwr01.stop_smoking.presentation.base.BaseFragment
import org.koin.dsl.module

val fragmentSmokeInfo = module {
    scope<SmokeInfoFragment> {
        scoped { params ->
            val fragment: BaseFragment = params[0]
            val binding: FragmentSmokingInfoBinding = params[1]
            val viewModel: SmokeInfoViewModel = params[2]
            SmokeInfoViewsController(fragment, binding, viewModel)
        }
    }
}