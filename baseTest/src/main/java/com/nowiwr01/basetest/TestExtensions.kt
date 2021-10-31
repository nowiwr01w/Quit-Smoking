package com.nowiwr01.basetest

import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert

inline fun <reified T> Any.assertThat() {
    MatcherAssert.assertThat(this, CoreMatchers.instanceOf(T::class.java))
}