package com.nowiwr01.domain.utils.smoke_info

import com.nowiwr01.domain.model.user.User
import com.nowiwr01.domain.utils.extensions.*

object UserSavedMoney {

    fun getSavedMoney(user: User): Pair<String, String> {
        val cost = UserNotSmokedPacks.getNotSmokedPacks(user) * user.smokeInfo.cigarettesPackCost
        return cost.toInt().roundSavedMoney() to user.smokeInfo.currency
    }

    private fun Int.roundSavedMoney(): String {
        return if (this >= MONEY_ROUND) {
            "${(this.toDouble() / MONEY_ROUND).setScale()}K"
        } else {
            toString()
        }
    }
}