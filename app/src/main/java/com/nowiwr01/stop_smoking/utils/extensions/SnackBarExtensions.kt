package com.nowiwr01.stop_smoking.utils.extensions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.nowiwr01.stop_smoking.R
import com.nowiwr01.stop_smoking.ui.base.BaseFragment

fun BaseFragment.showSnackbar(
    message: String,
    customColor: Boolean = false,
    showCallback: () -> Unit = {},
    hideCallback: () -> Unit = {}
) {
    context.showSnackbar(requireView(), message, customColor, showCallback, hideCallback)
}

fun Context.showSnackbar(
    view: View,
    message: String,
    customColor: Boolean = false,
    showCallback: () -> Unit = {},
    hideCallback: () -> Unit = {}
) {
    val backgroundColor = if (customColor) R.drawable.bg_snackbar_green else R.drawable.bg_snackbar
    val custom = LayoutInflater.from(this).inflate(R.layout.layout_custom_snackbar, null)
    custom.apply {
        elevation = 0f
        background = ContextCompat.getDrawable(this@showSnackbar, backgroundColor)
    }
    custom.findViewById<TextView>(R.id.customText).apply {
        text = message
        setTextColor(this@showSnackbar.getColor(R.color.snackBar_color))
    }
    val snackBar = Snackbar.make(view, "", 4000).apply {
        getView().setPadding(0, 0, 0, 0)
        (getView() as Snackbar.SnackbarLayout).removeAllViews()
        (getView() as Snackbar.SnackbarLayout).addView(custom)
        addCallback(showCallback, hideCallback)
    }
    snackBar.show()
}

fun Snackbar.addCallback(
    showCallback: () -> Unit,
    hideCallback: () -> Unit
) {
    addCallback(object : BaseTransientBottomBar.BaseCallback<Snackbar>() {
        override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
            hideCallback.invoke()
        }
        override fun onShown(transientBottomBar: Snackbar?) {
            showCallback.invoke()
        }
    })
}