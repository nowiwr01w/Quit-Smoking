package com.nowiwr01.stop_smoking.presentation.main.home

import android.content.Context
import androidx.core.view.isVisible
import com.nowiwr01.domain.model.user.User
import com.nowiwr01.domain.model.user.smoke_info.SmokeTime
import com.nowiwr01.domain.model.user.smoke_info.Star
import com.nowiwr01.stop_smoking.databinding.FragmentMainBinding
import com.nowiwr01.stop_smoking.model.adapters.StarAdapter
import com.nowiwr01.stop_smoking.model.health.HealthBuilder

class HomeViewsController(
    private val binding: FragmentMainBinding,
    private val viewModel: UserViewModel
) {

    fun showCravesCount(user: User) {
        binding.infoDesire.infoDesireValue.text = user.smokeInfo.craves.size.toString()
    }

    suspend fun showNotSmokedCigarettes(user: User) {
        val notSmokedPacks = viewModel.getNotSmokedPacks(user)
        binding.infoNotSmoked.infoNotSmokedValue.text = notSmokedPacks.toString()
    }

    suspend fun showSavedMoney(user: User) {
        val savedMoney = viewModel.getSavedMoney(user)
        binding.infoMoney.infoMoneyValue.text = savedMoney.first
        binding.infoMoney.infoMoneyValueDescription.text = savedMoney.second
    }

    suspend fun showNotSmokedDays(context: Context, user: User) {
        val savedTime = viewModel.getSavedTime(user)
        binding.infoFreeTime.infoFreeTimeValue.text = savedTime.first
        binding.infoFreeTime.infoFreeTimeValueDescription.text = context.getString(savedTime.second)
    }

    suspend fun showTimer(context: Context, user: User) {
        val time = viewModel.getTimerNotSmoked(user)
        showTimerTime(context, time)
    }

    suspend fun showStars(user: User) {
        val stars = viewModel.getStars(user)
        setRecyclerStars(stars)
    }

    private fun setRecyclerStars(stars: List<Star>) {
        with(binding) {
            starsRecycler.apply {
                isVisible = if (stars.isNotEmpty()) true else return
                adapter = StarAdapter(stars)
            }
        }
    }

    private fun showTimerTime(context: Context, time: SmokeTime) {
        with (binding) {
            timer.firstTitle.text = context.getString(time.first.first)
            timer.secondTitle.text = context.getString(time.second.first)
            timer.thirdTitle.text = context.getString(time.third.first)
            timer.fourthTitle.text = context.getString(time.fourth.first)

            timer.firstCount.text = time.first.second
            timer.secondCount.text = time.second.second
            timer.thirdCount.text = time.third.second
            timer.fourthCount.text = time.fourth.second
        }
    }

    fun manageProgressBar(showShimmer: Boolean) {
        if (viewModel.isUserDataInit) return

        with (binding) {
            name.isVisible = !showShimmer
            starsRecycler.isVisible = !showShimmer
            avatar.isVisible = !showShimmer

            timer.firstCount.isVisible = !showShimmer
            timer.secondCount.isVisible = !showShimmer
            timer.thirdCount.isVisible = !showShimmer
            timer.fourthCount.isVisible = !showShimmer
            timer.firstTitle.isVisible = !showShimmer
            timer.secondTitle.isVisible = !showShimmer
            timer.thirdTitle.isVisible = !showShimmer
            timer.fourthTitle.isVisible = !showShimmer

            infoNotSmoked.infoNotSmokedValue.isVisible = !showShimmer
            infoNotSmoked.infoNotSmokedValueDescription.isVisible = !showShimmer
            infoMoney.infoMoneyValue.isVisible = !showShimmer
            infoMoney.infoMoneyValueDescription.isVisible = !showShimmer
            infoFreeTime.infoFreeTimeValue.isVisible = !showShimmer
            infoFreeTime.infoFreeTimeValueDescription.isVisible = !showShimmer
            infoDesire.infoDesireValue.isVisible = !showShimmer
            infoDesire.infoDesireValueDescription.isVisible = !showShimmer

            healthLayout.healthText.isVisible = !showShimmer
            healthLayout.healthProgress.isVisible = !showShimmer

            nameShimmer.isVisible = showShimmer
            starsShimmer.isVisible = showShimmer
            avatarShimmer.isVisible = showShimmer
            timer.firstCountShimmer.isVisible = showShimmer
            timer.secondCountShimmer.isVisible = showShimmer
            timer.thirdCountShimmer.isVisible = showShimmer
            timer.fourthCountShimmer.isVisible = showShimmer
            timer.firstTitleShimmer.isVisible = showShimmer
            timer.secondTitleShimmer.isVisible = showShimmer
            timer.thirdTitleShimmer.isVisible = showShimmer
            timer.fourthTitleShimmer.isVisible = showShimmer

            infoNotSmoked.infoNotSmokedValueShimmer.isVisible = showShimmer
            infoNotSmoked.infoNotSmokedValueDescriptionShimmer.isVisible = showShimmer
            infoMoney.infoMoneyValueShimmer.isVisible = showShimmer
            infoMoney.infoMoneyValueDescriptionShimmer.isVisible = showShimmer
            infoFreeTime.infoFreeTimeValueShimmer.isVisible = showShimmer
            infoFreeTime.infoFreeTimeValueDescriptionShimmer.isVisible = showShimmer
            infoDesire.infoDesireValueShimmer.isVisible = showShimmer
            infoDesire.infoDesireValueDescriptionShimmer.isVisible = showShimmer
            healthLayout.healthTextShimmer.isVisible = showShimmer
            healthLayout.healthProgressShimmer.isVisible = showShimmer
        }

        viewModel.isUserDataInit = !showShimmer
    }

    fun showLastHealthItem(context: Context, user: User) {
        val healthList = HealthBuilder.getHealthList(context)
        val healthItem = healthList.find {
            it.progress >= System.currentTimeMillis() - user.smokeInfo.breakDate
        } ?: healthList.last()
        binding.healthLayout.healthText.text = healthItem.title
        binding.healthLayout.healthProgress.setProgressWithAnimation(healthItem.progress.toFloat(), 1000L)
    }
}