package com.nowiwr01.stop_smoking.utils.extensions

import android.view.View
import com.nowiwr01.stop_smoking.utils.OnSingleClickListener

fun View?.setVisible() {
    this?.visibility = View.VISIBLE
}

fun View?.setGone() {
    this?.visibility = View.GONE
}

fun View.setOnSingleClickListener(l: (View) -> Unit) {
    setOnClickListener(OnSingleClickListener(l))
}