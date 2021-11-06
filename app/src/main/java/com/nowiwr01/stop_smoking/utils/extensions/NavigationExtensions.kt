package com.nowiwr01.stop_smoking.utils.extensions

import android.os.Bundle
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import com.nowiwr01.stop_smoking.R
import com.nowiwr01.stop_smoking.presentation.base.BaseFragment

fun BaseFragment.navigate(
    resId: Int,
    bundle: Bundle? = null,
    setOptions: Boolean = false
) {
    val navOptions = if (setOptions) {
        NavOptions.Builder().apply {
            setExitAnim(R.anim.slide_out_left)
            setEnterAnim(R.anim.slide_in_right)
            setPopExitAnim(R.anim.slide_out_right)
            setPopEnterAnim(R.anim.slide_in_left)
        }.run {
            build()
        }
    } else {
        null
    }
    NavHostFragment.findNavController(this).navigate(resId, bundle, navOptions)
}

fun BaseFragment.navigate(
    navDirection: NavDirections,
    setOptions: Boolean = false
) {
    val navOptions = if (setOptions) {
        NavOptions.Builder().apply {
            setExitAnim(R.anim.slide_out_left)
            setEnterAnim(R.anim.slide_in_right)
            setPopExitAnim(R.anim.slide_out_right)
            setPopEnterAnim(R.anim.slide_in_left)
        }.run {
            build()
        }
    } else {
        null
    }
    NavHostFragment.findNavController(this).navigate(navDirection, navOptions)
}