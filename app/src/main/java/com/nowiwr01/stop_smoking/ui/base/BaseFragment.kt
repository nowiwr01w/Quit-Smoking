package com.nowiwr01.stop_smoking.ui.base

import android.content.Context
import android.os.Bundle
import android.view.View
import com.nowiwr01.stop_smoking.ui.MainActivity
import org.koin.androidx.scope.ScopeFragment

abstract class BaseFragment(layoutResId: Int): ScopeFragment(layoutResId) {

    protected val baseActivity: MainActivity
        get() = activity as MainActivity

    /**
     * We don't want to write context?. or requireContext() all the time.
     */
    override fun getContext(): Context = super.getContext() ?: requireContext()

    /**
     * We should call these methods to get different behavior.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBackPressed()
    }

    /**
     * The code should be structured and understandable, therefore, all actions will be
     * performed in specific functions.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setViews()
        setObservers()
        setListeners()
        setActions()
    }

    /**
     * Some functions helpers that are executed in a specific order in Fragment.
     */
    protected open fun initialize() {}
    protected open fun setViews() {}
    protected open fun setObservers() {}
    protected open fun setListeners() {}
    protected open fun setActions() {}

    /**
     * We can show or hide bottom bar for specific Fragment.
     */
    protected fun showBottomBar() = baseActivity.showBottomBar()
    protected fun hideBottomBar() = baseActivity.hideBottomBar()

    /**
     * When we want to show something after clicking on the system back button,
     * we must override this method
     */
    protected open fun onBackPressed() {}
}