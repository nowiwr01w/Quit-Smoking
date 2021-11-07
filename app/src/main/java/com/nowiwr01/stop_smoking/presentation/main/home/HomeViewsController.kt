package com.nowiwr01.stop_smoking.presentation.main.home

import androidx.core.view.isVisible
import com.nowiwr01.domain.model.user.User
import com.nowiwr01.domain.model.user.smoke_info.SmokeTime
import com.nowiwr01.stop_smoking.databinding.FragmentMainBinding
import com.nowiwr01.stop_smoking.utils.extensions.*

class HomeViewsController(
    private val binding: FragmentMainBinding,
    private val viewModel: UserViewModel
) {

    fun showCravesCount(user: User) {
        binding.infoDesire.infoDesireValue.text = user.smokeInfo.craves.size.toString()
    }

    fun showNotSmokedCigarettes(user: User) {
        binding.infoNotSmoked.infoNotSmokedValue.text = user.getNotSmokedPacks().toString()
    }

    fun showSavedMoney(user: User) {
        binding.infoMoney.infoMoneyValue.text = user.getSavedMoney().first
        binding.infoMoney.infoMoneyValueDescription.text = user.getSavedMoney().second
    }

    fun showNotSmokedDays(user: User) {
        binding.infoFreeTime.infoFreeTimeValue.text = user.getSavedTime().first
        binding.infoFreeTime.infoFreeTimeValueDescription.text = user.getSavedTime().second
    }

    fun showTimer(user: User) {
        val time = user.getTimerNotSmoked()
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

    fun manageProgressBar(showShimmer: Boolean) {
        if (viewModel.isUserDataInit) return

        binding.name.isVisible = !showShimmer
        binding.starsRecycler.isVisible = !showShimmer
        binding.avatar.isVisible = !showShimmer

        binding.timer.firstCount.isVisible = !showShimmer
        binding.timer.secondCount.isVisible = !showShimmer
        binding.timer.thirdCount.isVisible = !showShimmer
        binding.timer.fourthCount.isVisible = !showShimmer
        binding.timer.firstTitle.isVisible = !showShimmer
        binding.timer.secondTitle.isVisible = !showShimmer
        binding.timer.thirdTitle.isVisible = !showShimmer
        binding.timer.fourthTitle.isVisible = !showShimmer

        binding.infoNotSmoked.infoNotSmokedValue.isVisible = !showShimmer
        binding.infoNotSmoked.infoNotSmokedValueDescription.isVisible = !showShimmer
        binding.infoMoney.infoMoneyValue.isVisible = !showShimmer
        binding.infoMoney.infoMoneyValueDescription.isVisible = !showShimmer
        binding.infoFreeTime.infoFreeTimeValue.isVisible = !showShimmer
        binding.infoFreeTime.infoFreeTimeValueDescription.isVisible = !showShimmer
        binding.infoDesire.infoDesireValue.isVisible = !showShimmer
        binding.infoDesire.infoDesireValueDescription.isVisible = !showShimmer

        binding.healthLayout.healthText.isVisible = !showShimmer
        binding.healthLayout.healthProgress.isVisible = !showShimmer

        binding.nameShimmer.isVisible = showShimmer
        binding.starsShimmer.isVisible = showShimmer
        binding.avatarShimmer.isVisible = showShimmer
        binding.timer.firstCountShimmer.isVisible = showShimmer
        binding.timer.secondCountShimmer.isVisible = showShimmer
        binding.timer.thirdCountShimmer.isVisible = showShimmer
        binding.timer.fourthCountShimmer.isVisible = showShimmer
        binding.timer.firstTitleShimmer.isVisible = showShimmer
        binding.timer.secondTitleShimmer.isVisible = showShimmer
        binding.timer.thirdTitleShimmer.isVisible = showShimmer
        binding.timer.fourthTitleShimmer.isVisible = showShimmer

        binding.infoNotSmoked.infoNotSmokedValueShimmer.isVisible = showShimmer
        binding.infoNotSmoked.infoNotSmokedValueDescriptionShimmer.isVisible = showShimmer
        binding.infoMoney.infoMoneyValueShimmer.isVisible = showShimmer
        binding.infoMoney.infoMoneyValueDescriptionShimmer.isVisible = showShimmer
        binding.infoFreeTime.infoFreeTimeValueShimmer.isVisible = showShimmer
        binding.infoFreeTime.infoFreeTimeValueDescriptionShimmer.isVisible = showShimmer
        binding.infoDesire.infoDesireValueShimmer.isVisible = showShimmer
        binding.infoDesire.infoDesireValueDescriptionShimmer.isVisible = showShimmer
        binding.healthLayout.healthTextShimmer.isVisible = showShimmer
        binding.healthLayout.healthProgressShimmer.isVisible = showShimmer

        viewModel.isUserDataInit = !showShimmer
    }
}