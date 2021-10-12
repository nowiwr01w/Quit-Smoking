package com.nowiwr01.stop_smoking.utils.extensions

import androidx.fragment.app.Fragment

fun Fragment.showSnackbar(message: String) {
    this.requireContext().showSnackbar(requireView(), message)
}