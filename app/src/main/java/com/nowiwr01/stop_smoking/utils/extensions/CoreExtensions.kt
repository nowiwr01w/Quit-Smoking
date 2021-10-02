package com.nowiwr01.stop_smoking.utils.extensions

import android.content.Context
import android.util.TypedValue

fun Number.dpToPx(context: Context) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    toFloat(),
    context.resources.displayMetrics
).toInt()