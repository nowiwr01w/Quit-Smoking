package com.nowiwr01.stop_smoking.logic

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import timber.log.Timber

class LifecycleLogger(private val fragment: LifecycleOwner): LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun logOnCreate() {
        Timber.tag("LifecycleLogger").d("%s, ON_CREATE", fragment.javaClass.simpleName)
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun logOnCStart() {
        Timber.tag("LifecycleLogger").d("%s, ON_START", fragment.javaClass.simpleName)
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun logOnResume() {
        Timber.tag("LifecycleLogger").d("%s, ON_RESUME", fragment.javaClass.simpleName)
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun logOnPause() {
        Timber.tag("LifecycleLogger").d("%s, ON_PAUSE", fragment.javaClass.simpleName)
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun logOnStop() {
        Timber.tag("LifecycleLogger").d("%s, ON_STOP", fragment.javaClass.simpleName)
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun logOnDestroy() {
        Timber.tag("LifecycleLogger").d("%s, ON_DESTROY", fragment.javaClass.simpleName)
    }
}