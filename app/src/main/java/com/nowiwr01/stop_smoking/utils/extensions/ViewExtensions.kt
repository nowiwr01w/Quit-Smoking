package com.nowiwr01.stop_smoking.utils.extensions

import android.graphics.Rect
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.nowiwr01.stop_smoking.R
import com.nowiwr01.stop_smoking.utils.OnSingleClickListener

/**
 * View
 */
fun View?.setVisible() {
    this?.visibility = View.VISIBLE
}

fun View?.setGone() {
    this?.visibility = View.GONE
}

fun View.setOnSingleClickListener(l: (View) -> Unit) {
    setOnClickListener(OnSingleClickListener(l))
}

fun View.setKeyboardListener(editTexts: List<EditText>, callback: () -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener {
        val rect = Rect()
        getWindowVisibleDisplayFrame(rect)
        val heightDiff = rootView.height - (rect.bottom - rect.top)
        if (heightDiff < 500 && editTexts.isAnyoneHasFocus()) {
            editTexts.clearAllFocus()
            callback.invoke()
        }
    }
}

fun View.hideKeyboard() {
    val inputMethodManager = context.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}

/**
 * EditText
 */
fun EditText.setError() {
    setBackgroundResource(R.drawable.bg_edit_text_error)
}

fun EditText.setDefault() {
    setBackgroundResource(R.drawable.bg_edit_text_default)
}

fun EditText.doOnTextChanged(callback: () -> Unit) {
    this.doOnTextChanged { _, _, _, _ ->
        callback.invoke()
    }
}

fun List<EditText>.isAnyoneHasFocus(): Boolean {
    forEach {
        if (it.hasFocus()) return true
    }
    return false
}

fun List<EditText>.setAllFocusListener(callback: () -> Unit) {
    forEach {
        it.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) callback.invoke()
        }
    }
}

fun List<EditText>.clearAllFocus() {
    forEach {
        it.clearFocus()
    }
}