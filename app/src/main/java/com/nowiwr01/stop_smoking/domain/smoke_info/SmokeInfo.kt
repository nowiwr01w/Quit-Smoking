package com.nowiwr01.stop_smoking.domain.smoke_info

import com.nowiwr01.stop_smoking.Const.DEFAULT_CIGARETTES_MORNING_TIME
import com.nowiwr01.stop_smoking.Const.DEFAULT_CIGARETTES_PACK_COST
import com.nowiwr01.stop_smoking.Const.DEFAULT_CIGARETTES_PER_DAY
import com.nowiwr01.stop_smoking.Const.DEFAULT_CIGARETTES_PER_PACK
import com.nowiwr01.stop_smoking.Const.DEFAULT_CURRENCY
import org.joda.time.DateTime

data class SmokeInfo(
    val breakDate: DateTime = DateTime(),
    val craves: List<Crave> = listOf(),
    val currency: String = DEFAULT_CURRENCY,
    val cigarettesPerDay: Int = DEFAULT_CIGARETTES_PER_DAY,
    val cigarettesPerPack: Int = DEFAULT_CIGARETTES_PER_PACK,
    val cigarettesPackCost: Int = DEFAULT_CIGARETTES_PACK_COST,
    val firstCigaretteTime: Int = DEFAULT_CIGARETTES_MORNING_TIME
)