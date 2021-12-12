package com.nowiwr01.domain.utils.smoke_info

import com.nowiwr01.domain.model.user.User
import com.nowiwr01.domain.utils.extensions.MILLIS_IN_DAY
import com.nowiwr01.domain.utils.extensions.setScale

object UserNotSmokedPacks {

    fun getNotSmokedPacks(user: User): Double {
        val days = (System.currentTimeMillis() - user.smokeInfo.breakDate) / MILLIS_IN_DAY.toDouble()
        val result = user.smokeInfo.cigarettesPerDay * days / user.smokeInfo.cigarettesPerPack
        return result.setScale()
    }
}