package com.nowiwr01.stop_smoking.presentation.main

import androidx.core.os.bundleOf
import com.nowiwr01.stop_smoking.R
import com.nowiwr01.stop_smoking.utils.extensions.navigate

class MainNavigator(private val fragment: MainFragment) {
    fun toInfo(type: String) {
        val bundle = bundleOf("type" to type)
        fragment.navigate(R.id.action_home_to_info, bundle)
    }
}