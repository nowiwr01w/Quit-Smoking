package com.nowiwr01.stop_smoking.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.nowiwr01.stop_smoking.ui.MainActivity
import org.koin.androidx.scope.ScopeFragment

abstract class BaseFragment<T : ViewDataBinding> : ScopeFragment() {

    val baseActivity: MainActivity
        get() = activity as MainActivity

    protected abstract val layoutResId: Int

    /**
     * We will use DataBinding to avoid writing boilerplate code.
     */
    protected lateinit var binding: T

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
     * Current view for specific Fragment.
     */
    private var currentView: ViewDataBinding? = null

    /**
     * The official Google support says, that we should check the state of our currentView
     * to avoid Fragment re-creation.
     * Also we don't want to always write binding?.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        if (currentView == null) {
            binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
            currentView = binding
            binding.lifecycleOwner = this
            currentView!!.lifecycleOwner = this
        }
        return binding.root
    }

    /**
     * The code should be structured and understandable, therefore, all actions will be
     * performed in specific functions.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeBinding()
        setViews()
        setObservers()
        setListeners()
        setActions()
    }

    /**
     * Some functions helpers that are executed in a specific order in Fragment.
     */
    protected open fun initializeBinding() {}
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