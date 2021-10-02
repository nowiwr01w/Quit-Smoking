package com.nowiwr01.stop_smoking.utils.extensions

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import com.nowiwr01.stop_smoking.ui.base.BaseFragment

fun <T : ViewDataBinding> BaseFragment<T>.navigate(
    resId: Int,
    bundle: Bundle? = null,
    navOptions: NavOptions? = null
) {
    NavHostFragment.findNavController(this).navigate(resId, bundle, navOptions)
}

fun <T : ViewDataBinding> BaseFragment<T>.navigate(
    navDirection: NavDirections,
    navOptions: NavOptions? = null
) {
    NavHostFragment.findNavController(this).navigate(navDirection, navOptions)
}