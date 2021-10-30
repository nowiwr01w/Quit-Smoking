package com.nowiwr01.stop_smoking.logic

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * General [CoroutineDispatcher] provider.
 * */
interface DispatchersProvider {
    val default: CoroutineDispatcher
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
}

/**
 * Application implementation of [DispatchersProvider].
 * */
data class ApplicationDispatchers(
    override val default: CoroutineDispatcher = Dispatchers.Default,
    override val main: CoroutineDispatcher = Dispatchers.Main,
    override val io: CoroutineDispatcher = Dispatchers.IO
) : DispatchersProvider