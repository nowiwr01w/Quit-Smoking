package com.nowiwr01.stop_smoking.utils.extensions

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

fun Lifecycle.addObservers(
    onCreateCallback: () -> Unit = {},
    onStartCallback: () -> Unit = {},
    onResumeCallback: () -> Unit = {},
    onPauseCallback: () -> Unit = {},
    onStopCallback: () -> Unit = {},
    onDestroyCallback: () -> Unit = {}
) {
    addObserver(object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        fun onCreate() {
            onCreateCallback.invoke()
        }
        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        fun onStart() {
            onStartCallback.invoke()
        }
        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        fun onResume() {
            onResumeCallback.invoke()
        }
        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        fun onPause() {
            onPauseCallback.invoke()
        }
        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        fun onStop() {
            onStopCallback.invoke()
        }
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroy() {
            onDestroyCallback.invoke()
        }
    })
}