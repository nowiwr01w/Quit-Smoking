package com.nowiwr01.stop_smoking.presentation.base

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

open class BaseViewModel: ViewModel(), CoroutineScope {

    protected open val listeners = mutableSetOf<Pair<DatabaseReference, ValueEventListener>>()

    override val coroutineContext = Dispatchers.Main + SupervisorJob()

    override fun onCleared() {
        coroutineContext.cancel()
        removeListeners()
        super.onCleared()
    }

    private fun removeListeners() {
        listeners.forEach {
            it.first.removeEventListener(it.second)
        }
    }
}