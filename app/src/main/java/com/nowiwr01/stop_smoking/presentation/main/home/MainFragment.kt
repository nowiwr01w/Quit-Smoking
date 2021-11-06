package com.nowiwr01.stop_smoking.presentation.main.home

import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nowiwr01.domain.model.user.User
import com.nowiwr01.stop_smoking.R
import com.nowiwr01.stop_smoking.databinding.FragmentMainBinding
import com.nowiwr01.stop_smoking.model.Star
import com.nowiwr01.stop_smoking.model.adapters.StarAdapter
import com.nowiwr01.stop_smoking.presentation.base.BaseFragment
import com.nowiwr01.stop_smoking.presentation.main.auth.AuthViewModel
import com.nowiwr01.stop_smoking.presentation.main.home.bottom_sheet.InfoBottomSheet.Companion.TYPE_FREE_TIME
import com.nowiwr01.stop_smoking.presentation.main.home.bottom_sheet.InfoBottomSheet.Companion.TYPE_STRONG_DESIRE
import com.nowiwr01.stop_smoking.utils.extensions.setOnSingleClickListener
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.core.parameter.parametersOf

class MainFragment : BaseFragment(R.layout.fragment_main) {

    private val vb by viewBinding<FragmentMainBinding>()

    private val viewModel by sharedViewModel<AuthViewModel>()
    private val controller by inject<HomeViewsController> { parametersOf(vb) }
    private val navigator by inject<MainNavigator> { parametersOf(this) }

    override fun setViews() {
        showBottomBar()
        setRecyclerView()
    }

    override fun setListeners() {
        vb.infoFreeTime.infoFreeTimeInfoIcon.setOnSingleClickListener {
            showInfo(TYPE_FREE_TIME)
        }
        vb.infoDesire.infoDesireInfoIcon.setOnSingleClickListener {
            showInfo(TYPE_STRONG_DESIRE)
        }
    }

    override fun setObservers() {
        viewModel.userData.observe(viewLifecycleOwner) {
            lifecycleScope.launch { repeatInfo(it.second) }
        }
    }

    private fun repeatInfo(user: User) {
        lifecycleScope.launch {
            while (true) {
                proceedUser(user)
                delay(1000L)
            }
        }
    }

    private fun proceedUser(user: User) {
        with(controller) {
            showTimer(user)
            showSavedMoney(user)
            showNotSmokedDays(user)
            showNotSmokedCigarettes(user)
        }
    }

    private fun showInfo(type: String) {
        navigator.toInfo(type)
    }

    private fun setRecyclerView() {
        vb.starsRecycler.adapter = StarAdapter(getStars())
    }

    private fun getStars() = listOf(
        Star(), Star(), Star(), Star(), Star()
    )
}