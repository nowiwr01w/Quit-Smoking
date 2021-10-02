package com.nowiwr01.stop_smoking.utils

import android.view.View

/**
 * Используется для перехода на любой BottomSheet. При двойном/тройном клике на любую кнопку,
 * ведущую на любой BottomSheet, будет Crash, так как мы будем находиться уже на другой транзакции.
 * Почему бы не проверять, в каком месте навигации мы сейчас находимся? Пушто открывается несколько
 * BottomSheet.
 */

class OnSingleClickListener : View.OnClickListener {

    private val onClickListener: View.OnClickListener

    constructor(listener: View.OnClickListener) {
        onClickListener = listener
    }

    constructor(listener: (View) -> Unit) {
        onClickListener = View.OnClickListener { listener.invoke(it) }
    }

    override fun onClick(v: View) {
        val currentTimeMillis = System.currentTimeMillis()

        if (currentTimeMillis >= previousClickTimeMillis + DELAY_MILLIS) {
            previousClickTimeMillis = currentTimeMillis
            onClickListener.onClick(v)
        }
    }

    companion object {
        // Tweak this value as you see fit. In my personal testing this
        // seems to be good, but you may want to try on some different
        // devices and make sure you can't produce any crashes.
        private const val DELAY_MILLIS = 450L

        private var previousClickTimeMillis = 0L
    }

}