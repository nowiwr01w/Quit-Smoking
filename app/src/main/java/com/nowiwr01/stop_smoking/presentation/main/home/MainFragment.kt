package com.nowiwr01.stop_smoking.presentation.main.home

import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nowiwr01.domain.model.user.User
import com.nowiwr01.stop_smoking.R
import com.nowiwr01.stop_smoking.databinding.FragmentMainBinding
import com.nowiwr01.domain.model.user.smoke_info.Star
import com.nowiwr01.stop_smoking.model.adapters.StarAdapter
import com.nowiwr01.stop_smoking.presentation.base.BaseFragment
import com.nowiwr01.stop_smoking.presentation.main.home.bottom_sheet.InfoBottomSheet.Companion.TYPE_FREE_TIME
import com.nowiwr01.stop_smoking.presentation.main.home.bottom_sheet.InfoBottomSheet.Companion.TYPE_STRONG_DESIRE
import com.nowiwr01.stop_smoking.utils.extensions.setOnSingleClickListener
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.core.parameter.parametersOf

class MainFragment : BaseFragment(R.layout.fragment_main) {

    private val binding by viewBinding<FragmentMainBinding>()

    private val viewModel by sharedViewModel<UserViewModel>()
    private val navigator by inject<MainNavigator> { parametersOf(this) }
    private val controller by inject<HomeViewsController> { parametersOf(binding, viewModel) }

    private var repeatJob: Job? = null

    override fun setViews() {
        showBottomBar()
        controller.manageProgressBar(true)
    }

    override fun setListeners() {
        binding.healthLayout.healthContainer.setOnClickListener {
            navigator.toHealth()
        }
        binding.infoFreeTime.infoFreeTimeInfoIcon.setOnSingleClickListener {
            showInfo(TYPE_FREE_TIME)
        }
        binding.infoDesire.infoDesireInfoIcon.setOnSingleClickListener {
            showInfo(TYPE_STRONG_DESIRE)
        }
    }

    override fun setObservers() {
        viewModel.userData.observe(viewLifecycleOwner) {
            repeatInfo(it)
        }
    }

    override fun setActions() {
        viewModel.loadUserData()
        viewModel.setUserListener()
    }

    private fun repeatInfo(user: User) {
        repeatJob?.cancel()
        repeatJob = lifecycleScope.launch {
            while (true) {
                proceedUser(user)
                delay(1000L)
            }
        }
    }

    private suspend fun proceedUser(user: User) {
        with(controller) {
            showTimer(context, user)
            showStars(user)
            showSavedMoney(user)
            showCravesCount(user)
            showNotSmokedDays(context, user)
            showNotSmokedCigarettes(user)
            showLastHealthItem(context, user)
            manageProgressBar(false)
        }
    }

    private fun showInfo(type: String) {
        navigator.toInfo(type)
    }

    override fun onBackPressed() {
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                baseActivity.finish()
            }
        }
        baseActivity.onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onDestroy() {
        super.onDestroy()
        repeatJob?.cancel()
    }
}