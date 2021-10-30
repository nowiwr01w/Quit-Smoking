package com.nowiwr01.domain.model.user.smoke_info

import com.nowiwr01.domain.utils.Const.DEFAULT_BREAK_DATE
import com.nowiwr01.domain.utils.Const.DEFAULT_CIGARETTES_MORNING_TIME
import com.nowiwr01.domain.utils.Const.DEFAULT_CIGARETTES_PACK_COST
import com.nowiwr01.domain.utils.Const.DEFAULT_CIGARETTES_PER_DAY
import com.nowiwr01.domain.utils.Const.DEFAULT_CIGARETTES_PER_PACK
import com.nowiwr01.domain.utils.Const.DEFAULT_CURRENCY

data class SmokeInfo(
    val breakDate: Int = DEFAULT_BREAK_DATE,
    val craves: List<Crave> = listOf(),
    val currency: String = DEFAULT_CURRENCY,
    val cigarettesPerDay: Int = DEFAULT_CIGARETTES_PER_DAY,
    val cigarettesPerPack: Int = DEFAULT_CIGARETTES_PER_PACK,
    val cigarettesPackCost: Int = DEFAULT_CIGARETTES_PACK_COST,
    val firstCigaretteTime: Int = DEFAULT_CIGARETTES_MORNING_TIME
)