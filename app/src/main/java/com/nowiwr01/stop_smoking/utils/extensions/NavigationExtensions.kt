package com.nowiwr01.stop_smoking.utils.extensions

import android.os.Bundle
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import com.nowiwr01.stop_smoking.presentation.base.BaseFragment

fun BaseFragment.navigate(
    resId: Int,
    bundle: Bundle? = null,
    navOptions: NavOptions? = null
) {
    NavHostFragment.findNavController(this).navigate(resId, bundle, navOptions)
}

fun BaseFragment.navigate(
    navDirection: NavDirections,
    navOptions: NavOptions? = null
) {
    NavHostFragment.findNavController(this).navigate(navDirection, navOptions)
}