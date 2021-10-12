package com.nowiwr01.stop_smoking.utils.extensions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.nowiwr01.stop_smoking.R

fun Context.showSnackbar(view: View, message: String) {
    val custom = LayoutInflater.from(this).inflate(R.layout.layout_custom_snackbar, null)
    custom.apply {
        elevation = 0f
        background = ContextCompat.getDrawable(this@showSnackbar, R.drawable.bg_snackbar)
    }
    custom.findViewById<TextView>(R.id.customText).apply {
        text = message
        setTextColor(this@showSnackbar.getColor(R.color.stackBar_color))
    }
    val snackBar = Snackbar.make(view, "", 4000).apply {
        getView().setPadding(0, 0, 0, 0)
        (getView() as Snackbar.SnackbarLayout).removeAllViews()
        (getView() as Snackbar.SnackbarLayout).addView(custom)
    }
    snackBar.show()
}