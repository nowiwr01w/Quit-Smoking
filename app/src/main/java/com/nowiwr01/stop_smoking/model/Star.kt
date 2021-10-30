package com.nowiwr01.stop_smoking.model

/***
 * Цвет и размер меняется в зависимости от параметра @param[size]
 * 1 -> small & yellow
 * 2 -> medium & orange
 * 3 -> big & red
 */
data class Star(
    val size: Int = 1
)