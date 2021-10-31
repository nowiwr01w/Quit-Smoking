package com.nowiwr01.basetest

import com.nowiwr01.basecoroutines.DispatchersProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class TestDispatchersProvider(
    override val default: CoroutineDispatcher = Dispatchers.Unconfined,
    override val main: CoroutineDispatcher = Dispatchers.Unconfined,
    override val io: CoroutineDispatcher = Dispatchers.Unconfined
) : DispatchersProvider

fun testDispatcherProvider(): DispatchersProvider = TestDispatchersProvider()