package com.nowiwr01.stop_smoking.presentation.main

import com.nowiwr01.domain.model.user.User
import com.nowiwr01.domain.model.user.smoke_info.SmokeTime
import com.nowiwr01.stop_smoking.databinding.FragmentMainBinding
import com.nowiwr01.stop_smoking.utils.extensions.*

class TimerViewController(
    private val binding: FragmentMainBinding
) {


    fun showNotSmokedDays(user: User) {
        binding.infoFreeTime.infoFreeTimeValue.text = user.getNotSmokedDays().toString()
    }

    fun showNotSmokedCigarettes(user: User) {
        binding.infoNotSmoked.infoNotSmokedValue.text = user.getNotSmokedPacks().toString()
    }

    fun showTimer(user: User) {
        val time = user.getNotSmokedTime()
        showTimerTime(time)
    }

    private fun showTimerTime(time: SmokeTime) {
        binding.timer.firstTitle.text = time.first.first
        binding.timer.secondTitle.text = time.second.first
        binding.timer.thirdTitle.text = time.third.first
        binding.timer.fourthTitle.text = time.fourth.first

        binding.timer.firstCount.text = time.first.second.round()
        binding.timer.secondCount.text = time.second.second.round()
        binding.timer.thirdCount.text = time.third.second.round()
        binding.timer.fourthCount.text = time.fourth.second.round()
    }
}