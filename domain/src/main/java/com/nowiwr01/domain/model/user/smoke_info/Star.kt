package com.nowiwr01.domain.model.user.smoke_info

/***
 * Цвет и размер меняется в зависимости от параметра @param[size]
 * 1 -> small & yellow
 * 2 -> medium & orange
 * 3 -> big & red
 * 4 -> big & green
 * 5 -> big & blue
 */
data class Star(
    val size: Int = 1
)